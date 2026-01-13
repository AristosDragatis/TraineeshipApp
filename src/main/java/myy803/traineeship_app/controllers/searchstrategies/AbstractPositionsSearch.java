package myy803.traineeship_app.controllers.searchstrategies;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;

public abstract class AbstractPositionsSearch implements PositionsSearchStrategy{
	@Autowired
	protected StudentMapper studentMapper;
	@Autowired
	protected TraineeshipPositionsMapper positionsMapper;
	
	
	@Override
	public final List<TraineeshipPosition> search(String applicantUsername){
		
		Student applicant = studentMapper.findByUsername(applicantUsername);
		List<TraineeshipPosition> allPositions = positionsMapper.findAll();
		
		return filterPositions(allPositions, applicant); 
	}
	
	protected abstract List<TraineeshipPosition> filterPositions(List<TraineeshipPosition> positions, Student student);
}