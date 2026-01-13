package myy803.traineeship_app.controllers.supervisorsearchstrategies;

import java.util.List;
import org.springframework.stereotype.Component;
import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.domain.TraineeshipPosition;


@Component("load")
public class AssignmentBasedOnLoad extends AbstractSupervisorAssignment{

	@Override
	protected Professor findCandidate(List<Professor> professors, TraineeshipPosition position) {
		Professor candidateSupervisor = professors.get(0);
		for(Professor professor : professors) {
			if(professor.compareLoad(candidateSupervisor) >= 0) {
				candidateSupervisor = professor;
			}
		}
		return candidateSupervisor;
	}

}
