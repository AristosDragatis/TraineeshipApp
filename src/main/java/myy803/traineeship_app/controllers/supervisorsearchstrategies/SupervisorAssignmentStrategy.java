package myy803.traineeship_app.controllers.supervisorsearchstrategies;

import java.util.List;

import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.domain.TraineeshipPosition;

public interface SupervisorAssignmentStrategy {
	void performAssignment(List<Professor> professors, TraineeshipPosition position);
}
