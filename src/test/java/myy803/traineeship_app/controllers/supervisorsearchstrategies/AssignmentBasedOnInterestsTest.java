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

        //  (Topics)
        position = new TraineeshipPosition();
        position.setTopics("Java, Spring, Backend");

        // Professor that matches (Java)
        javaProf = new Professor("java_prof");
        javaProf.setInterests("Software Engineering, Java, Cloud");
        // initialize list to avoid null pointer exception
        javaProf.setSupervisedPositions(new ArrayList<>());

        // 3. Καθηγητής που ΔΕΝ ταιριάζει (History)
        historyProf = new Professor("history_prof");
        historyProf.setInterests("History, Ancient Greece");
        historyProf.setSupervisedPositions(new ArrayList<>());

        // List with all the professors
        professorsList = Arrays.asList(historyProf, javaProf);
    }

    @Test
    void testFindCandidateMatchFound() {
        // Act
        Professor result = strategy.findCandidate(professorsList, position);

        // Assert should find java_prof
        assertNotNull(result, "Should return a professor");
        assertEquals("java_prof", result.getUsername(), "Should select the professor with matching interests");
    }

    @Test
    void testFindCandidateNoMatch() {
        // Arrange
        position.setTopics("C++, Embedded Systems");

        // Act
        Professor result = strategy.findCandidate(professorsList, position);

        // Assertion
        assertNull(result, "Should return null if no matching interests found");
    }
}