import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Loads teh file containi9ng the courses 
 *
 * @author (Felix )
 */
public class CourseLoader extends DataLoader
{
    public List<Course> courses = new ArrayList<Course>();

    /**
     * Constructor calls the load(file) method in abstract parent class.
     * 
     * @param file: the path to the file.
     */
    CourseLoader(String file){
        load(file);
    }
    
    public CourseLoader (String dept,int courseNum,int section, String title,double credits, int maxEnrollment,String daysOfTheWeek, int startTime,int endTime, int duration, String loc, String instructor){
        
    }

    /**
     * Parse a single line of CSV in the form:
     * dept, courseNum, section, name, credits, maxEnrol, days, startTime, EndTime, Duration, String time, room, professor
     * CMPU,145,51,Foundations/Computer Science,1,24,TRF,720,795,75,1200PM-0115PM,SP 309,Gomerschdat Anna
     * 
     * Method should create a new Course Object and add it to 
     * the students instance variable.
     * 
     * @param data: a single line from the csv file.
     */
    public void parseAndLoadLine(String data){
        //List <Course> courses = new ArrayList<>();
        try {

            String[] fields = data.split(",");
            String dept = fields[0];
            int courseNum = Integer.parseInt(fields[1]);
            int section = Integer.parseInt(fields[2]);
            String title = fields[3];
            double credits = Double.parseDouble(fields[4]);
            int maxEnrollment = Integer.parseInt(fields[5]);
            String daysOfTheWeek = fields[6];
            int startTime = Integer.parseInt(fields[7]);
            int endTime = Integer.parseInt(fields[8]);
            int duration = Integer.parseInt(fields[9]);
            String timeString = fields[10];
            String loc = fields[11];
            String instructor = fields[12];
            courses.add(new Course(dept,courseNum,section,title,credits,maxEnrollment,daysOfTheWeek, startTime,endTime,duration,timeString,loc,instructor));
        }catch (NumberFormatException e) {
            System.out.println("Invalid line");
        }catch(IndexOutOfBoundsException e){
            System.out.println("Invalid line");
        }


        //TODO
    }

    /**
     * Easy getter method to return the completed student roster.
     * @return students: the roster in the form of a List<Course>
     */
    public List<Course> getCourses(){
        return courses;
    }
}
