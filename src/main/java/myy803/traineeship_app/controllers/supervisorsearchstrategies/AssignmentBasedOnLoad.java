package myy803.traineeship_app.controllers.supervisorsearchstrategies;

import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.domain.TraineeshipPosition;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AssignmentBasedOnLoad extends AbstractSupervisorAssignment {

    @Override
    protected Professor findCandidate(List<Professor> professors, TraineeshipPosition position) {
        Professor candidateSupervisor = professors.get(0);
        for (Professor professor : professors) {
            if (professor.compareLoad(candidateSupervisor) >= 0) {
                candidateSupervisor = professor;
            }
        }
        return candidateSupervisor;
    }

}
