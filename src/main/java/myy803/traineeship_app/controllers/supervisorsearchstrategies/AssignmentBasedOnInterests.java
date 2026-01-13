package myy803.traineeship_app.controllers.supervisorsearchstrategies;

import java.util.List;
import org.springframework.stereotype.Component;
import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.domain.TraineeshipPosition;

@Component
public class AssignmentBasedOnInterests extends AbstractSupervisorAssignment{
	
	@Override
	protected Professor findCandidate(List<Professor> professors, TraineeshipPosition position) {
		// splits the topics if it finds ( , )
		String[] topics = position.getTopics().split("[,\\s+\\.]");
		Professor candidateSupervisor = null;
		for(Professor professor : professors) {
			if(professor.match(topics) == true) {
				candidateSupervisor = professor;
		}
	}
	return candidateSupervisor;
	}
}
