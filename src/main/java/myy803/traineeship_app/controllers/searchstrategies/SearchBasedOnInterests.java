package myy803.traineeship_app.controllers.searchstrategies;

import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SearchBasedOnInterests extends AbstractPositionsSearch {

    @Override
    protected List<TraineeshipPosition> filterPositions(List<TraineeshipPosition> positions, Student student) {

        Set<TraineeshipPosition> matchingPositionsSet = new HashSet<TraineeshipPosition>();
        String[] interests = student.getInterests().split("[,\\s+\\.]");

        for (TraineeshipPosition pos : positions) {
            for (String interest : interests) {
                if (pos.getTopics().contains(interest) && !pos.isAssigned()) {
                    matchingPositionsSet.add(pos);
                }
            }
        }
        return new ArrayList<>(matchingPositionsSet);
    }
}
