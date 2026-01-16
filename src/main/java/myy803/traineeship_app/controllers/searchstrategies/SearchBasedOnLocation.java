package myy803.traineeship_app.controllers.searchstrategies;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;

@Component
public class SearchBasedOnLocation extends AbstractPositionsSearch {

	
	@Override
	protected List<TraineeshipPosition> filterPositions(List<TraineeshipPosition> positions, Student student) {

		if (student.getPreferredLocation() == null) {
			return List.of();
		}

		return positions.stream()
				.filter(pos -> {
					if (pos.getCompany() == null) return false;

					if (pos.getCompany().getCompanyLocation() == null) return false;

					return pos.getCompany().getCompanyLocation()
							.equalsIgnoreCase(student.getPreferredLocation());
				})
				.filter(pos -> !pos.isAssigned())
				.collect(Collectors.toList());
	}

}
