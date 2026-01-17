package myy803.traineeship_app.controllers.searchstrategies;

import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;

import java.util.List;

public interface PositionsSearchStrategy {
    List<TraineeshipPosition> filter(List<TraineeshipPosition> allPositions, Student student);
}
