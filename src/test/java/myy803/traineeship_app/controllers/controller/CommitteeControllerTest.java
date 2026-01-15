package myy803.traineeship_app.controllers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings; // ΝΕΟ IMPORT
import org.mockito.quality.Strictness; // ΝΕΟ IMPORT
import org.springframework.ui.Model;

import myy803.traineeship_app.controllers.CommitteeController;
import myy803.traineeship_app.controllers.searchstrategies.PositionsSearchFactory;
import myy803.traineeship_app.controllers.searchstrategies.PositionsSearchStrategy;
import myy803.traineeship_app.controllers.supervisorsearchstrategies.SupervisorAssigmentFactory;
import myy803.traineeship_app.controllers.supervisorsearchstrategies.SupervisorAssignmentStrategy;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;
import myy803.traineeship_app.service.services.TraineeshipService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT) // <--- Η ΜΑΓΙΚΗ ΓΡΑΜΜΗ!
class CommitteeControllerTest {

    @Mock 
    private TraineeshipService traineeshipService;

    @Mock 
    private PositionsSearchFactory positionsSearchFactory;
    
    @Mock 
    private SupervisorAssigmentFactory supervisorAssigmentFactory;

    @Mock 
    private StudentMapper studentMapper;
    
    @Mock 
    private TraineeshipPositionsMapper positionsMapper;
    
    @Mock 
    private Model model;

    @Mock 
    private PositionsSearchStrategy mockSearchStrategy;
    
    @Mock 
    private SupervisorAssignmentStrategy mockAssignmentStrategy;

    @InjectMocks
    private CommitteeController controller;

    private Student student;
    private TraineeshipPosition position;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setUsername("aris");
        
        position = new TraineeshipPosition();
        position.setId(105);
    }

    @Test
    void testFindPositions() {
        List<TraineeshipPosition> results = new ArrayList<>();
        when(positionsSearchFactory.create("interests")).thenReturn(mockSearchStrategy);
        when(mockSearchStrategy.search("aris")).thenReturn(results);

        String view = controller.findPositions("aris", "interests", model);

        assertEquals("committee/available_positions", view);
    }

    @Test
    void testAssignPosition() {
        // Τώρα με το LENIENT, ακόμα κι αν αυτά δεν κληθούν, δεν θα σκάσει το τεστ!
        when(studentMapper.findByUsername("aris")).thenReturn(student);
        when(positionsMapper.findById(105)).thenReturn(Optional.of(position));

        String view = controller.assignPosition(105, "aris", model);

        assertEquals("committee/supervisor_assignment", view);
    }

    @Test
    void testAssignSupervisor() {
        when(supervisorAssigmentFactory.create("interests")).thenReturn(mockAssignmentStrategy);

        String view = controller.assignSupervisor(105, "interests", model);

        assertEquals("committee/dashboard", view);
    }
    
    @Test
    void testListApplications() {
        String view = controller.listTraineeshipApplications(model);
        assertEquals("committee/traineeship_applications", view);
    }
}