package myy803.traineeship_app.controllers.searchstrategies;

import myy803.traineeship_app.domain.Company;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchBasedOnLocationTest {

    @Test
    void filterPositions() {
        // Setup data
        SearchBasedOnLocation strategy = new SearchBasedOnLocation();
        Student student = new Student("new_student");
        student.setPreferredLocation("Ioannina");

        // position at Ioannina that matches
        TraineeshipPosition pos1 = new TraineeshipPosition();
        pos1.setCompany(new Company("owner1", "Teamviewer", "Ioannina", new ArrayList<>()));
        pos1.setAssigned(false);

        // position that does not match
        TraineeshipPosition pos2 = new TraineeshipPosition();
        pos2.setCompany(new Company("owner2", "Teamviewer", "Athens", new ArrayList<>()));
        pos2.setAssigned(false);


        List<TraineeshipPosition> input = new ArrayList<>();
        input.add(pos1);
        input.add(pos2);

        // call the strategy method
        List<TraineeshipPosition> result = strategy.filterPositions(input, student);

        // assertion
        assertEquals(1, result.size());
        assertEquals("Ioannina", result.get(0).getCompany().getCompanyLocation());
    }
}
