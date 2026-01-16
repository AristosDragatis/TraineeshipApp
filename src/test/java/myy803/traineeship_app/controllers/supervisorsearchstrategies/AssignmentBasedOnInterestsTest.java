package myy803.traineeship_app.controllers.supervisorsearchstrategies;

import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.domain.TraineeshipPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentBasedOnInterestsTest {

    private AssignmentBasedOnInterests strategy;
    private TraineeshipPosition position;
    private Professor javaProf;
    private Professor historyProf;
    private List<Professor> professorsList;

    @BeforeEach
    void setUp() {
        strategy = new AssignmentBasedOnInterests();
        position = new TraineeshipPosition();

        // without spaces
        position.setTopics("Java,Spring,Backend");

        // professor that matches
        javaProf = new Professor("java_prof");
        // without spaces at interests
        javaProf.setInterests("SoftwareEngineering,Java,Cloud");
        javaProf.setSupervisedPositions(new ArrayList<>());

        // professor that does not match
        historyProf = new Professor("history_prof");
        // without spaces
        historyProf.setInterests("History,AncientGreece");
        historyProf.setSupervisedPositions(new ArrayList<>());

        professorsList = Arrays.asList(historyProf, javaProf);
    }

    @Test
    void testFindCandidate_MatchFound() {
        // Act
        Professor result = strategy.findCandidate(professorsList, position);

        // Assert
        assertNotNull(result, "Should return a professor");
        assertEquals("java_prof", result.getUsername(), "Should select the professor with matching interests");
    }

    @Test
    void testFindCandidate_NoMatch() {
        position.setTopics("Python,Ruby");

        // Act
        Professor result = strategy.findCandidate(professorsList, position);

        // Assert
        assertNull(result, "Should return null because no professor knows Python or Ruby");
    }
}