import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.io.ArrayIndexOutOfBoundsException;

/**
 * Write a description of class StudentLoader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StudentLoader extends DataLoader
{
    public List<Student> students = new ArrayList<Student>();
    /**
     * Constructor calls the load(file) method in abstract parent class.
     * 
     * @param file: the path to the file.
     */

    // public StudentLoader(String name, int idNum, int gradYear, int drawNum){
    // this.students = students;
    // }
    public StudentLoader(String file){
        load(file);
    }

    /**
     * Parse a single line of CSV in the form:
     * Name, idNum, gradYear, drawNummber
     * Hector Tran,999248624,2023,1
     * 
     * Method should create a new Student Object and add it to 
     * the students instance variable.
     * 
     * @param data: a single line from the csv file.
     */

    public void parseAndLoadLine(String data){
        
        try {
            String[] fields = data.split(",");
            String name = fields[0];
            int idNum = Integer.parseInt(fields[1]);
            int gradYear = Integer.parseInt(fields[2]);
            int drawNum = Integer.parseInt(fields[3]);
            students.add(new Student(name,idNum, gradYear, drawNum));
            //System.out.print("Felix");

        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Array Index Out of bounds");
        }catch (NumberFormatException e) {
            System.out.println("Number format exception");
        }

    }

    /**
     * Easy getter method to return the completed student roster.
     * @return students: the roster in the form of a List<Student>
     */
    public List<Student> getStudents(){
        return students;
    }

}
