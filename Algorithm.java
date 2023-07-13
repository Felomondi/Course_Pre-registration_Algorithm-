import java.util.*;
/**
 * The actual algorithm 
 *
 * @author (Felix )
 * @version (12th Feb 2023 )
 */
public class Algorithm
{
    HashMap<Integer, PriorityQueue<Student> > classOf = new HashMap<Integer, PriorityQueue<Student>>();
    HashMap<Integer, Stack<Student>> reverseClassOf = new HashMap<Integer, Stack<Student>>();
    HashMap<Course,Integer> enrollment = new HashMap<Course, Integer>();
    List <Integer> keys = new ArrayList<Integer>();
    List<Course> schedule = new ArrayList<Course>();

    public Algorithm(List<Student> mapStudents,  HashMap<Course,Integer> enrollment){

        this.classOf = new HashMap<>();
        this.reverseClassOf = new HashMap<>();
        for (Student s : mapStudents){
            int key = s.gradYear; 
            if (classOf.keySet().contains(key)){
                PriorityQueue<Student> myPQ = classOf.get(key);
                myPQ.offer(s);
                classOf.put(key, myPQ);

            }else{
                Stack<Student> myStack = new Stack<Student>();
                //myStack.push(s);

                reverseClassOf.put(key, myStack);
                classOf.put(key, new PriorityQueue<Student>());
                PriorityQueue<Student> myPQ = classOf.get(key);
                myPQ.offer(s);
                classOf.put(key, myPQ);
            }
        }

        keys.addAll(classOf.keySet());
        Collections.sort(keys);

    }

    /**
     *check to see if the student's maximum credits is 4.5 and below 
     *add a credit the the student's total credits when a class is added
     *@ return booelan: True if the student has less than 4.5 credits
     */
    // public boolean creditsRegisteredStillLess(Course other){
    // double totalCredits = 0;
    // return totalCredits + other.credits <= 4.5;

    // }

    public boolean creditsRegisteredStillLess(Course other){
        double totalCredits = 0;
        for (Course c : this.schedule) {
            totalCredits += c.credits;
        }
        return totalCredits + other.credits <= 4.5;
    }

    /**
     * checks to see whether there is still space in the class
     * @ return boolean: True if there is still space left
     */
    public boolean checkIfSpaceIsAvailable(Course c){
        return c.enrolled < c.maxEnrollment;

    }

    public void enroll(Student s){
        //Check if they are not above 4.5 
        //go through list of requests
        //using the course object check the conditions to check if they are able to add the course or not. 
        // add the course to the schedule, increase the enrollment of the class , put the course in the map.

        //checks if the credits registered for is still less than 4.5
        for(int i = 0; i< s.requests.size(); i++){
            Course c = s.requests.get(i);
            if(s.totalRegisteredCredits() + c.credits <= 4.5){// goes through the list of requests

                if((!s.isRegisteredFor(c))){
                    if(!s.hasAConflict(c)){ 
                        if(checkIfSpaceIsAvailable(c)){
                            s.schedule.add(c);
                            s.requests.remove(c);
                            int enrolled = (c.enrolled + 1);
                            enrollment.put(c, enrolled);

                        }
                    }

                }

            }
        }
    }

    public void run(){
        /* Description of the algorithm from the Registrar's website:
         * 
         * Entry into a section is determined by the combination of your class year, the priority you give each section, and your draw number.
         * Seniors’ requests are processed first followed sequentially by juniors’, sophomores’, and first-years requests.
         * Your requests are considered in the order that you list them on the registration screen, with the first item having the highest priority. If one of your requests cannot be filled, then the next item in your list will be considered instead.
         * For your class year, your draw number determines when one of your requests is considered. Your top request is considered immediately after the top requests of all of the students in your class with lower draw numbers. As mentioned above, if your top request cannot be granted you will be enrolled in the first request on your list that can be.
         * 
         * In a second pass through the requests from your class, your top request among your remaining requests will be considered immediately before all of the students in your class with lower draw numbers. That is, the draw numbers work in reverse compared to the first pass. The remaining passes through the requests from your class continue the pattern of the first two passes, switching direction through the draw numbers on each pass.
         * You may list multiple sections of the same course among your requests but you will be enrolled only in the first one on your list that is available. You will not be enrolled in multiple sections of the same course.
         * You may also list sections of different courses that meet at the same time but you will be enrolled only in the first one on your list that is available. You will not be enrolled in sections with time conflicts.
         * 
         */
        //this.keys = keys;

        for(int i =0; i < 7; i++){
            if(i % 2 == 0){
                //forward()
                for(int k : keys){
                    PriorityQueue <Student> queue = classOf.get(k);
                    while(!queue.isEmpty()){
                        Student s = queue.poll();
                        // System.out.println(s);
                        Stack<Student> stack = reverseClassOf.get(k);
                        stack.push(s);
                        reverseClassOf.put(k, stack);
                        enroll(s);

                    }
                }

            }else{
                //reverse() 
                for(int k : keys){
                    Stack <Student> stack = reverseClassOf.get(k);
                    while(!stack.isEmpty()){
                        Student s = stack.pop();

                        PriorityQueue<Student> queue = classOf.get(k);
                        queue.offer(s);
                        classOf.put(k, queue);
                        enroll(s);

                    }
                }
            }

            Collections.sort(keys);
        }
    }

    public void printEnrollment(){
        //TODO
        //Print the toString of the student, followed by their schedule (using course toString).
        /*
         * Hector Tran 2023 1
         * CMPU-145-51 Foundations/Computer Science    1.0    TRF 1200PM-0115PM
         * EDUC-361-51 Sem: Math/Science/Elem Classrm    1.0    R 0310PM-0610PM
         * ECON-235-51 Sports Economics    1.0    TR 1030AM-1145AM
         * PHED-105-51 Foundations of Wellness    0.5    TR 0900AM-1015AM
         * --------------------
         * Chace Sanford 2023 2
         * GNCS-355-51 Childhood/Childrn 19C Britain    1.0    R 0310PM-0510PM
         * ART-318-51 Building the Museum    1.0    T 0100PM-0300PM
         * CHEM-352-51 Phys Chem-Molec Structr    1.0    MW 1030AM-1145AM
         * INTL-109-51 A Lexicon of Forced Migration    1.0    TR 1030AM-1145AM
         * --------------------
         * etc...
         */

        for(int k: keys){ // goes through the keys
            //PriorityQueue <Student> queue = classOf.get(k); 
            // for(Student s1: queue){ //loops through the students in the queue
            // System.out.println(s1);
            // String ScheduleCourses = (s1.schedule.toString());
            // String [] sectionsOfCourses = ScheduleCourses.split(",");
            // for(String sections: sectionsOfCourses){
            // System.out.println(sections);
            // }
            // System.out.println("*---------------------------------------------------------------------------------------*");

            // }

            Stack <Student> stack = reverseClassOf.get(k);
            for(Student s2: stack){ // loops through the students in the stack
                System.out.println(s2);// toString() of the student

                for(Course s: s2.schedule){ //loop through the courses in the schedule and print them.
                    System.out.println(s);
                }
                
                System.out.println("*---------------------------------------------------------------------------------------*");

            }

        }
    }
}
