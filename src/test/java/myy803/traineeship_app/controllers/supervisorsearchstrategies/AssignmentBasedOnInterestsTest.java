package myy803.traineeship_app.controllers.supervisorsearchstrategies;

import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.ProfessorMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AssignmentBasedOnInterestsTest {

    @Mock
    private TraineeshipPositionsMapper positionsMapper;

    @Mock
    private ProfessorMapper professorMapper;

    @InjectMocks
    private AssignmentBasedOnInterests interests;


}
