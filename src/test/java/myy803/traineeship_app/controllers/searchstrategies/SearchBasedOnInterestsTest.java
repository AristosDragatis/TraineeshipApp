package myy803.traineeship_app.controllers.searchstrategies;

import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SearchBasedOnInterestsTest {

    @Test
    void testFilterPositions() {
        SearchBasedOnInterests strategy = new SearchBasedOnInterests();

        Student student = new Student("aris");
        student.setInterests("Java,Spring");

        TraineeshipPosition pos1 = new TraineeshipPosition();
        pos1.setTopics("Java Developer");
        pos1.setAssigned(false);

        TraineeshipPosition pos2 = new TraineeshipPosition();
        pos2.setTopics("Python Data Science");
        pos2.setAssigned(false);

        List<TraineeshipPosition> input = Arrays.asList(pos1, pos2);

        // Act
        List<TraineeshipPosition> result = strategy.filterPositions(input, student);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Java Developer", result.get(0).getTopics());
    }
}