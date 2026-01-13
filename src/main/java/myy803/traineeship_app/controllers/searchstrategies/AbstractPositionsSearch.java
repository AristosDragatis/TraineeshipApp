package myy803.traineeship_app.controllers.searchstrategies;

import java.util.List;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;


public abstract class AbstractPositionsSearch implements PositionsSearchStrategy{
	
	@Override
	public final List<TraineeshipPosition> filter(List<TraineeshipPosition> allPositions, Student student){
		
		return filterPositions(allPositions, student); 
	}
	
	protected abstract List<TraineeshipPosition> filterPositions(List<TraineeshipPosition> positions, Student student);
}