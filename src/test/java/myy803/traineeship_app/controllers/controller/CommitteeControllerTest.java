package myy803.traineeship_app.controllers.controller;

import myy803.traineeship_app.controllers.CommitteeController;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.service.services.TraineeshipService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommitteeControllerTest {

    @Mock
    private TraineeshipService traineeshipService;

    @Mock
    private Model model;

    @InjectMocks
    private CommitteeController controller;

    @Test
    void testFindPositions() {
        // Arrange
        String username = "aris";
        String strategy = "interests";
        List<TraineeshipPosition> mockPositions = new ArrayList<>();

        when(traineeshipService.findPositionsForStudent(username, strategy)).thenReturn(mockPositions);

        // Act
        String view = controller.findPositions(username, strategy, model);

        // Assert
        assertEquals("committee/available_positions", view);
        verify(traineeshipService).findPositionsForStudent(username, strategy);
    }

    @Test
    void testAssignPosition() {
        // Arrange
        Integer posId = 105;
        String username = "aris";

        // Act
        String view = controller.assignPosition(posId, username, model);

        // Assert
        assertEquals("committee/supervisor_assignment", view);
        verify(traineeshipService).assignPositionToStudent(posId, username);
    }

    @Test
    void testAssignSupervisor() {
        // Arrange
        Integer posId = 105;
        String strategy = "load";

        // Act
        String view = controller.assignSupervisor(posId, strategy, model);

        // Assert
        assertEquals("committee/dashboard", view);
        verify(traineeshipService).assignSupervisor(posId, strategy);
    }

    @Test
    void testListApplications() {
        // Act
        String view = controller.listTraineeshipApplications(model);

        // Assert
        assertEquals("committee/traineeship_applications", view);
        verify(traineeshipService).listTraineeshipApplications();
    }
}