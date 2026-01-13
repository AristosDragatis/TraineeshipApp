package myy803.traineeship_app.controllers.supervisorsearchstrategies;

import java.util.List;
import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.domain.TraineeshipPosition;

public abstract class AbstractSupervisorAssignment implements SupervisorAssignmentStrategy{
	

	@Override
	public final void performAssignment(List<Professor> professors, TraineeshipPosition position) {

		
		// step that changes for subclasses (hook)
		Professor selected = findCandidate(professors, position);
		
		// assignment 
		if(selected != null) {
			position.setSupervisor(selected);
			selected.addPosition(position);
		}
	}
	
	protected abstract Professor findCandidate(List<Professor> professors, TraineeshipPosition position);
}