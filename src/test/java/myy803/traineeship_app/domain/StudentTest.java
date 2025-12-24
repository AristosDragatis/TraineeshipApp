package myy803.traineeship_app.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {


    @Test
    void testStudent(){
        Student student = new Student("Aristos Dragatis");
        student.setAM("534");

        String username = student.getUsername();
        String am = student.getAM();

        assertEquals("Aristos Dragatis", username, "Username must be Aristos Dragatis");
        assertEquals("534", am, "AM must be 534");


        assertNotNull(student, "student object must not be null");

    }


}
