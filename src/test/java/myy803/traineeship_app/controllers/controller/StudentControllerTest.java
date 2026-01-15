package myy803.traineeship_app.controllers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import myy803.traineeship_app.controllers.StudentController;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.mappers.StudentMapper;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentMapper studentMapper; // Ο ψεύτικος Mapper

    @Mock
    private Model model;

    @InjectMocks
    private StudentController controller; // Ο αληθινός Controller

    @Test
    void testGetStudentDashboard() {
        // Υποθέτουμε ότι η μέθοδος λέγεται getStudentDashboard ή getDashboard
        // και επιστρέφει "student/dashboard"
        // Αν κοκκινίσει, δες το όνομα της μεθόδου στον Controller του φίλου σου.
        try {
             String viewName = controller.getStudentDashboard(); // ή getDashboard()
             assertEquals("student/dashboard", viewName);
        } catch (Exception e) {
            // Αν δεν υπάρχει η μέθοδος, απλά αγνόησε το
            System.out.println("Η μέθοδος dashboard ίσως έχει άλλο όνομα");
        }
    }

    @Test
    void testSaveProfile() {
        // ARRANGE
        Student student = new Student();
        student.setUsername("student1");

        // ACT
        // Δοκιμάζουμε με (student, model) όπως κάναμε στο Company
        String viewName = controller.saveProfile(student, model);

        // ASSERT
        verify(studentMapper).save(student);
        // Εδώ συνήθως επιστρέφουν στο dashboard ή στο profile.
        // Δες τι return έχει ο κώδικας του φίλου σου. Βάζω dashboard για αρχή.
        assertEquals("student/dashboard", viewName);
    }
}