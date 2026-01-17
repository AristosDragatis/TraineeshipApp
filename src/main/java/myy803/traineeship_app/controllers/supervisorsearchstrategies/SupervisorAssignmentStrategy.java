package myy803.traineeship_app.controllers.supervisorsearchstrategies;

import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.domain.TraineeshipPosition;

import java.util.List;

public interface SupervisorAssignmentStrategy {
    void performAssignment(List<Professor> professors, TraineeshipPosition position);
}
