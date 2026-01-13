package myy803.traineeship_app.controllers.supervisorsearchstrategies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.ProfessorMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;


public abstract class AbstractSupervisorAssignment implements SupervisorAssignmentStrategy{
	@Autowired protected TraineeshipPositionsMapper positionsMapper;
	@Autowired protected ProfessorMapper professorMapper;
	
	
	@Override
	public final void assign(Integer positionId) {
		// same steps of the algorithm
		TraineeshipPosition position = positionsMapper.findById(positionId).get();
		List<Professor> professors = professorMapper.findAll();
		
		// step that changes 
		Professor selected = findCandidate(professors, position);
		
		if(selected != null) {
			position.setSupervisor(selected);
			selected.addPosition(position);
			positionsMapper.save(position);
		}
		
		
	}
	
	protected abstract Professor findCandidate(List<Professor> professors, TraineeshipPosition position);
	
	
}