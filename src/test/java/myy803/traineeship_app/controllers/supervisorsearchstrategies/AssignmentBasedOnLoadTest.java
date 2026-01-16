package myy803.traineeship_app.controllers.supervisorsearchstrategies;

import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.domain.TraineeshipPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// Δεν χρειάζεται MockitoExtension αν δεν χρησιμοποιείς @Mock
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentBasedOnLoadTest {

    // Fields that will be used at all test methods
    private AssignmentBasedOnLoad strategy;
    private TraineeshipPosition positionToAssign;
    private Professor busyProf;
    private Professor freeProf;
    private List<Professor> professorsList;

    @BeforeEach
    void setUp() {
        // initialize strategy
        strategy = new AssignmentBasedOnLoad();

        // initialize the position to assign
        positionToAssign = new TraineeshipPosition();
        positionToAssign.setTitle("New Position");

        // busy professor (load)
        busyProf = new Professor("busy_prof");
        busyProf.setSupervisedPositions(new ArrayList<>());
        // add two positions to this professor to increase load
        busyProf.getSupervisedPositions().add(new TraineeshipPosition());
        busyProf.getSupervisedPositions().add(new TraineeshipPosition());

        // create a free professor (load)
        freeProf = new Professor("free_prof");
        freeProf.setSupervisedPositions(new ArrayList<>());

        professorsList = new ArrayList<>();
        professorsList.add(busyProf);
        professorsList.add(freeProf);
    }

    @Test
    void testAssignmentBasedOnLoad() {
        // Act : calling the findCandidate method
        Professor result = strategy.findCandidate(professorsList, positionToAssign);

        // Assert
        assertNotNull(result, "Should return a professor");
        assertEquals("free_prof", result.getUsername(), "Should select the professor with 0 positions");
    }

    // this is based on the logic of the implemented method (findCandidate)
    @Test
    void testAssignmentWithEqualLoad() {
        // Arrange
        freeProf.getSupervisedPositions().add(new TraineeshipPosition());
        freeProf.getSupervisedPositions().add(new TraineeshipPosition());

        // Act
        Professor result = strategy.findCandidate(professorsList, positionToAssign);

        // Assert
        assertNotNull(result);
    }
}