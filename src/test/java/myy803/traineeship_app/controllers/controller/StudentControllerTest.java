package myy803.traineeship_app.controllers.controller;

import myy803.traineeship_app.controllers.StudentController;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.service.services.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @Mock
    private Model model;

    @InjectMocks
    private StudentController controller;

    @Test
    void testGetStudentDashboard() {
        String viewName = controller.getStudentDashboard();
        assertEquals("student/dashboard", viewName);
    }

    @Test
    void testSaveProfile() {
        // ARRANGE
        Student student = new Student();
        student.setUsername("student1");

        // ACT
        String viewName = controller.saveProfile(student, model);

        // ASSERT
        // check if controller called student service
        verify(studentService).saveStudentProfile(student);
        assertEquals("student/dashboard", viewName);
    }
}