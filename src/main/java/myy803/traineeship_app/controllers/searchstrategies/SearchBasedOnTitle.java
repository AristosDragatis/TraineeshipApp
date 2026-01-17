package myy803.traineeship_app.controllers.searchstrategies;

import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchBasedOnTitle extends AbstractPositionsSearch{

    @Override
    protected List<TraineeshipPosition> filterPositions(List<TraineeshipPosition> positions, Student student){
        List<TraineeshipPosition> matchingPositions = new ArrayList<>();

        // split student interests
        String[] interests = student.getInterests().split("[,\\s\\.]+");
        for (TraineeshipPosition pos : positions) {
            for (String interest : interests) {
                if (interest.trim().isEmpty()) continue;

                // check if title of position contains the interest
                // (Case insensitive)
                if (pos.getTitle().toLowerCase().contains(interest.trim().toLowerCase())
                        && !pos.isAssigned()) {
                    matchingPositions.add(pos);
                    break; // if a match is found , we keep it and we go to the next one
                }
            }
        }
        return matchingPositions;
    }

}
