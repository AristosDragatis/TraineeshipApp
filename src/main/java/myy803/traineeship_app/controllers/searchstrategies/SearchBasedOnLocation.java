package myy803.traineeship_app.controllers.searchstrategies;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;

@Component("location")
public class SearchBasedOnLocation extends AbstractPositionsSearch {

	
	@Override
	protected List<TraineeshipPosition> filterPositions(List<TraineeshipPosition> positions, Student student) {
		
		return positions.stream()
	            .filter(pos -> pos.getCompany().getCompanyLocation()
	                .equalsIgnoreCase(student.getPreferredLocation()))
	            .filter(pos -> !pos.isAssigned()) 
	            .collect(Collectors.toList());
	}

}
