package myy803.traineeship_app.controllers.searchstrategies;

import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class SearchBasedOnInterestsTest {


    @Test
    void testFilterPositions(){
        // Setup
        SearchBasedOnInterests strategy = new SearchBasedOnInterests();

        Student student = new Student("new_student");
        student.setInterests("Java, Spring");


        TraineeshipPosition pos1 = new TraineeshipPosition();
        pos1.setTopics("Java Developer"); // matches
        pos1.setAssigned(false);

        TraineeshipPosition pos2 = new TraineeshipPosition();
        pos2.setTopics("Python Data Science"); // doesn't match
        pos2.setAssigned(false);


        List<TraineeshipPosition> inputList = Arrays.asList(pos1, pos2);

        // Execution: call filterPositions method
        List<TraineeshipPosition> result = strategy.filterPositions(inputList, student);

        // Assertion
        assertEquals(1, result.size());
        assertEquals("Java Developer", result.get(0).getTopics());

    }
}
