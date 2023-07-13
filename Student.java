import java.util.List;
import java.util.ArrayList;

public class Student implements Comparable{
    String name;
    int idNum;
    int gradYear;
    int drawNumber;
    public List<Course> requests;
    public List<Course> schedule;

    /**
     * @param name: string the student's first and last name;
     * @param idNum: int the student's 999 number.
     * @param gradYear: 4 digit graduation year.
     * @param drawNumber: the draw number determines the student's place in the algorithm.
     */
    public Student(String name, int idNum, int gradYear,int drawNumber){
        this.name = name;
        this.idNum = idNum;
        this.gradYear = gradYear;
        this.drawNumber = drawNumber;
        this.requests = new ArrayList<Course>();
        this.schedule = new ArrayList<Course>();
    }

    /**
     * Returns true if idNumbers are the same;
     * @param Object: any possible object.  
     * 
     * @return boolean: true if idNumbers are the same.
     */
    public boolean equals(Object o){
        if((o instanceof Student)){
            return idNum == ((Student)(o)).idNum;
        }
        return false;     
    }

    /**
     * ToString returns a string representation including 
     * name, graduation year and draw number.
     */
    public String toString(){
        return name + " " + gradYear + " " + drawNumber;
    }

    /**
     * Write a compareTo that sorts the student by draw number and
     * class year.  
     * The first person should be a 4th year with draw number 1.
     * The last person should be a 1st year with a the largest draw number.
     * All 4th years come before all 3rd years, etc.
     * 
     * @return retval: 
     *    1 if the first thing comes first, 
     *    0 if they are equal
     *    -1 if the second thing comes firt.
     */
    public int compareTo(Object s){
        Student toCompare = (Student)s;
        if(this.gradYear < toCompare.gradYear){
            return -1;
        }  
        else if(this.gradYear >  toCompare.gradYear){
            return 1;
        }

        else{
            if(this.drawNumber < toCompare.drawNumber){return -1;}
            if(this.drawNumber == toCompare.drawNumber){return 0;}
            else{
                return 1;
            }

        }  

    }        

    /**
     * Check to see if the student is registered for any section of a course.
     * @param maybe: Course.  The potential course to register for.
     * 
     * @return boolean: true if the student is registered for any section of the course.
     */
    public boolean isRegisteredFor(Course maybe){
        //TODO
        // supposed to check if the dept and the section already exists in the schedule. We should not check the section because
        //a class can have different sections. example: check if CMPU 102 already exists. This will check all the sections. 
        for(Course c: this.schedule){
            if(c.dept.equals(maybe.dept) && (c.courseNum == maybe.courseNum)){
                return true; 
            }

        }
        return false;
    }

    /**
     * @return the total registered credits (limit is 4.5)
     */
    public double totalRegisteredCredits(){
        double totalCredits = 0.0;
        for(Course c : this.schedule){

            totalCredits += c.credits;

        }
        //TODO
        return totalCredits;
    }

    /**
     * @param maybe: Course the potential course
     * 
     * @return true if the student already has something at that time.
     */
    public boolean hasAConflict(Course maybe){

        for (Course c: this.schedule){
            if(c.conflictsWith(maybe)){
                return true;  
            }
        }
        //TODO
        return false;
    }
}


