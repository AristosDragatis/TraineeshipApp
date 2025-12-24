package myy803.traineeship_app.controllers.supervisorsearchstrategies;

import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.ProfessorMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssignmentBasedOnLoadTest {
    @Mock
    private TraineeshipPositionsMapper positionsMapper;

    @Mock
    private ProfessorMapper professorMapper;

    @InjectMocks
    private AssignmentBasedOnLoad strategy;

    // initialize fields (fixture pattern)
    private TraineeshipPosition mockPosition;
    private Professor prof1;
    private Professor prof2;
    private final int positionId = 100;

    // runs before each test (fixture pattern)
    @BeforeEach
    void setUp(){
        mockPosition = new TraineeshipPosition();
        mockPosition.setId(positionId);

        prof1 = new Professor("prof1");
        prof1.setSupervisedPositions(new ArrayList<>());

        prof2 = new Professor("prof2");
        prof2.setSupervisedPositions(new ArrayList<>());

    }

    @Test
    void testAssignmentBasedOnLoad(){

        // add 3 positions to prof1 to increase load
        prof1.getSupervisedPositions().add(new TraineeshipPosition());
        prof1.getSupervisedPositions().add(new TraineeshipPosition());
        prof1.getSupervisedPositions().add(new TraineeshipPosition());


        List<Professor> professorsList = Arrays.asList(prof1,prof2);

        // stubbing
        // we use Optional.of because that is what is returned by findById()
        when(positionsMapper.findById(positionId)).thenReturn(Optional.of(mockPosition));
        when(professorMapper.findAll()).thenReturn(professorsList);

        // execute assign method
        strategy.assign(positionId);

        // check
        assertNotNull(mockPosition.getSupervisor());
        assertEquals(prof2, mockPosition.getSupervisor(), "professor with less amount of load should be selected");

        // check if position is added to the professor's list
        assertEquals(1, prof2.getSupervisedPositions().size());

        // check to see if save is called in the database
        verify(positionsMapper).save(mockPosition);
    }

    // equal loads
    @Test
    void testAssignmentBasedOnLoad_EqualLoads(){
        prof1.getSupervisedPositions().add(new TraineeshipPosition());
        prof2.getSupervisedPositions().add(new TraineeshipPosition());

        List<Professor> professorsList = Arrays.asList(prof1, prof2);

        when(positionsMapper.findById(positionId)).thenReturn(Optional.of(mockPosition));
        when(professorMapper.findAll()).thenReturn(professorsList);

        strategy.assign(positionId);

        // >= 0 in the algorithm , it will get the last element (prof2)
        assertEquals(prof2, mockPosition.getSupervisor());
        verify(positionsMapper).save(mockPosition);
    }
}
