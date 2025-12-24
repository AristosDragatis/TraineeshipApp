package myy803.traineeship_app.controllers.supervisorsearchstrategies;

import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.ProfessorMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;
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


    @Test
    void testAssignmentBasedOnLoad(){
        // create the position to be assigned
        int positionId = 100;
        TraineeshipPosition mockPosition = new TraineeshipPosition();
        mockPosition.setId(positionId);


        Professor busyProf = new Professor("busy_prof");
        busyProf.setSupervisedPositions(new ArrayList<>());

        // add 3 positions to busyProf to increase load
        busyProf.getSupervisedPositions().add(new TraineeshipPosition());
        busyProf.getSupervisedPositions().add(new TraineeshipPosition());
        busyProf.getSupervisedPositions().add(new TraineeshipPosition());

        // the other professor that has less amount of load ( or not at all )
        Professor freeProf = new Professor("free_prof");
        freeProf.setSupervisedPositions(new ArrayList<>());

        List<Professor> professorsList = Arrays.asList(busyProf, freeProf);

        // stubbing mocks
        // we use Optional.of because that is what is returned by findById()
        when(positionsMapper.findById(positionId)).thenReturn(Optional.of(mockPosition));
        when(professorMapper.findAll()).thenReturn(professorsList);

        // execute assign method
        strategy.assign(positionId);

        // check
        assertNotNull(mockPosition.getSupervisor());
        assertEquals(freeProf, mockPosition.getSupervisor(), "professor with less amount of load should be selected");

        // check if position is added to the professor's list
        assertEquals(1, freeProf.getSupervisedPositions().size());

        // check to see if save is called in the database
        verify(positionsMapper).save(mockPosition);
    }

    // TODO add a new test for equal load of professors
}
