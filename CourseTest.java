import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


/**
 * Write a description of class CourseTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CourseTest
{
    @Test
    public void testValidCourse(){
        Course Cmpu = new Course("CMPU",102,51,"Foundations/Computer Science",1,24,"TRF",810,885,75,"0130PM-0245PM","SP 309","Hunsberger Luke");
        Course Cogs  = new Course("COGS",311,51,"Seminar in Cognitive Science",1,15,"R",910,1090,180,"0310PM-0610PM","OH A62","Andrews Jan");
        Course CMPU = new Course("CMPU",145,52,"Foundations/Computer Science",1,24,"TRF",1010,1085,75,"0130PM-0245PM","SP 309","Hunsberger Luke");
        
        Course Art = new Course("ART",106,56,"Intro to Hist of Art/Architect",1,15,"MWF",720,770,50,"1200PM-1250PM","T 102","Culp Caroline");
        Course anotherArt = new Course("ART",106,58,"Intro to Hist of Art/Architect",1,15,"MWF",720,770,50,"1200PM-1250PM","T 102","Lu Haohao");
        List<Course> courses = new ArrayList<Course> ();
        
        
        courses.add(Cmpu);
        courses.add(Cogs);
        courses.add(CMPU);
        courses.add(Art);
        courses.add(anotherArt);
        Collections.sort(courses);
        for(Course o : courses){
            System.out.println(o);
        }
        
        assertEquals(Cogs.compareTo(Cmpu), 2);        
        //assertEquals(Cmpu.compareTo(CMPU), 0);
        assertEquals(Cmpu.compareTo(CMPU), -1);
        assertEquals(Art.compareTo(anotherArt), -1);
        assertEquals(Cogs.compareTo(CMPU), 2);
        
        assertEquals(Cogs.conflictsWith(Cmpu), false);
        assertEquals(Art.conflictsWith(anotherArt), true);
        
        
    }
}

