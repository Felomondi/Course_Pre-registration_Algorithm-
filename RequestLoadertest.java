ximport static org.junit.Assert.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class RequestLoaderTest here.
 *
 * @author (Felix)
 * @version (a version number or a date)
 */

public class RequestLoaderTest {
    @Test
    public void testParseAndLoadLine_validInput() {
        //StudentLoader stu = new StudentLoader("../data/shortRoster.csv");
        CourseLoader courses = new CourseLoader("../data/course_shortRequests.csv");
        StudentLoader stu = new StudentLoader("../data/shortRoster.csv");
        RequestLoader  request = new RequestLoader("../data/shortRequests.csv", stu.getStudents(), courses.getCourses());
        String line = "999248624,CMPU-145-51,CMPU-145-52,EDUC-361-51,ECON-235-51,PHED-105-51,ECON-235-51, COGS-311-51";
        request.parseAndLoadLine(line);

        Course req1 = request.mapStudents.get(999248624).requests.get(0);
        Course req2 = request.mapCourses.get("CMPU-145-51");
        
        assertEquals(req1,req2);
    }
}

