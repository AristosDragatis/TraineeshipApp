package myy803.traineeship_app.domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ProfessorTest {

    @Test
    void testCompareLoad(){
        // create the current professor
        Professor current = new Professor("current_prof");
        current.setSupervisedPositions(new ArrayList<>());

        // 2 positions = load (2)
        current.getSupervisedPositions().add(new TraineeshipPosition());
        current.getSupervisedPositions().add(new TraineeshipPosition());

        //create candidate professor
        Professor candidate = new Professor("candidate_prof");
        candidate.setSupervisedPositions(new ArrayList<>());

        candidate.getSupervisedPositions().add(new TraineeshipPosition());

        int resultLower = current.compareLoad(candidate);
        assertEquals(-1, resultLower, "-1 must be returned because candidate has lower load");

        int resultHigher = candidate.compareLoad(current);
        assertEquals(0, resultHigher, "0 must be returned because candidate (current) has bigger load");
    }
}
