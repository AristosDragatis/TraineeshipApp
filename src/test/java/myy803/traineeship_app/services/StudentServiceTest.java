package myy803.traineeship_app.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import myy803.traineeship_app.domain.LogBook;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.mappers.LogBookMapper;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.service.services.StudentService;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock private StudentMapper studentMapper;
    @Mock private LogBookMapper logbookMapper;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testRetrieveStudentProfile_NewStudent() {
        // Arrange
        String username = "new_student";
        when(studentMapper.findByUsername(username)).thenReturn(null);

        // Act
        Student result = studentService.retrieveStudentProfile(username);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    void testSaveStudentProfile() {
        // Arrange
        Student student = new Student("new_student");

        // Act
        studentService.saveStudentProfile(student);

        // Assert
        assertTrue(student.isLookingForTraineeship());
        verify(studentMapper).save(student);
    }

    @Test
    void testAddEntryToLogBook() {
        // Arrange
        String username = "new_student";
        String content = "this is a test";
        Student student = new Student(username);

        when(studentMapper.findByUsername(username)).thenReturn(student);

        // Act
        studentService.addEntryToLogBook(username, content);

        // Assert
        verify(logbookMapper).save(any(LogBook.class));
    }
}