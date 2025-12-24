package myy803.traineeship_app.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest {

    @Test
    void testCompany(){


       TraineeshipPosition position = new TraineeshipPosition();
       position.setTitle("Java Developer");

       List<TraineeshipPosition> myPositions = new ArrayList<>();
        Company company = new Company("Teamviewer", "Teamviewer", "Ioannina",myPositions);

        // adds a new position
        company.addPosition(position);

        // gets available positions
       List<TraineeshipPosition> positions = company.getAvailablePositions();


       assertEquals("Teamviewer", company.getUsername() );
       assertEquals("Ioannina", company.getCompanyLocation());


       assertNotNull(positions, "List must not be null");
       assertEquals(1, positions.size(), "There must be exactly 1 available positions");

       assertEquals("Java Developer", positions.get(0).getTitle());
    }
}
