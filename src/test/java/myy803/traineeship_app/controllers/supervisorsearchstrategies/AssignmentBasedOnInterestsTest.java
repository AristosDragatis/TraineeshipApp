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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AssignmentBasedOnInterestsTest {

    @Mock
    private TraineeshipPositionsMapper positionsMapper;

    @Mock
    private ProfessorMapper professorMapper;

    @InjectMocks
    private AssignmentBasedOnInterests strategy;


    @Test
    void testAssignmentBasedOnMatchingInterests(){
        // initialize position
        int positionId = 101;
        TraineeshipPosition mockPosition = new TraineeshipPosition();
        mockPosition.setId(positionId);

        // this will split based on the regex (Java and Backend)
        mockPosition.setTopics("Java,Backend");

        Professor historyProf = new Professor("history_prof");
        historyProf.setInterests("History,Ancient Greece");
        historyProf.setSupervisedPositions(new ArrayList<>());

        Professor javaProf = new Professor("java_prof");
        javaProf.setInterests("Software Engineering,Java,Spring");
        javaProf.setSupervisedPositions(new ArrayList<>());

        List<Professor> professorsList = Arrays.asList(historyProf, javaProf);

        // stubbing
        when(positionsMapper.findById(positionId)).thenReturn(Optional.of(mockPosition));
        when(professorMapper.findAll()).thenReturn(professorsList);

        // assign the position
        strategy.assign(positionId);

        assertEquals(javaProf, mockPosition.getSupervisor(), "professor with 'Java' interest must be selected");

        // verify save
        verify(positionsMapper).save(mockPosition);
    }
}
