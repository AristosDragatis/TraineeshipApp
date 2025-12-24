package myy803.traineeship_app.controllers.searchstrategies;

import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.annotation.MergedAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchBasedOnInterestsTest {
    @Mock
    private TraineeshipPositionsMapper positionsMapper;

    @Mock
    private StudentMapper studentMapper;

    // η κλάση που τεστάρουμε ( βάζει τους mocks μεσα της )
    @InjectMocks
    private SearchBasedOnInterests strategy;


    @Test
    void testSearchInterestsAndFindPositions(){
        String username = "aris";

        // create the student with multiple interests
        Student mockStudent = new Student(username);
        mockStudent.setInterests("Java,Python");

        TraineeshipPosition javaPos = new TraineeshipPosition();
        javaPos.setTitle("Java Developer");

        TraineeshipPosition pythonPos = new TraineeshipPosition();
        pythonPos.setTitle("Python Developer");

        // Mocks training
        // when a student named "aris" is asked - give the one we just created
        when(studentMapper.findByUsername(username)).thenReturn(mockStudent);


        // split is happening so we must diverse the two scenarios

        // stubbing
        // java position
        when(positionsMapper.findByTopicsContainingAndIsAssignedFalse("Java")).thenReturn(Arrays.asList(javaPos));

        // python position
        when(positionsMapper.findByTopicsContainingAndIsAssignedFalse("Python")).thenReturn(Arrays.asList(pythonPos));

        List<TraineeshipPosition> results = strategy.search(username);

        assertEquals(2, results.size(), "2 positions must be found");

        // we verify that the mapper was called 2 times
        verify(positionsMapper).findByTopicsContainingAndIsAssignedFalse("Java");
        verify(positionsMapper).findByTopicsContainingAndIsAssignedFalse("Python");

        verify(studentMapper).findByUsername(username);
    }
}
