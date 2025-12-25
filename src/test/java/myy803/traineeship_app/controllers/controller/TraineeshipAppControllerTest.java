package myy803.traineeship_app.controllers.controller;

import myy803.traineeship_app.controllers.TraineeshipAppController;
import myy803.traineeship_app.controllers.searchstrategies.PositionsSearchFactory;
import myy803.traineeship_app.controllers.searchstrategies.PositionsSearchStrategy;
import myy803.traineeship_app.controllers.supervisorsearchstrategies.SupervisorAssigmentFactory;
import myy803.traineeship_app.controllers.supervisorsearchstrategies.SupervisorAssignmentStrategy;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.CompanyMapper;
import myy803.traineeship_app.mappers.ProfessorMapper;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TraineeshipAppControllerTest {

    @Mock
    private PositionsSearchFactory positionsSearchFactory;

    @Mock
    private SupervisorAssignmentStrategy mockAssignmentStrategy;

    @Mock
    private PositionsSearchStrategy mockStrategy;

    @Mock
    private SupervisorAssigmentFactory supervisorAssigmentFactory;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private ProfessorMapper professorMapper;

    @Mock
    private TraineeshipPositionsMapper positionsMapper;

    @Mock
    private CompanyMapper companyMapper;

    @Mock
    private Model model;

    @InjectMocks
    private TraineeshipAppController controller;

    // declaration
    private Integer positionId;
    private String studentUsername;
    private String strategyName;
    List<TraineeshipPosition> mockPositions;
    private TraineeshipPosition mockPosition;
    private Student student;

    // setting up fields
    @BeforeEach
    void setUp(){
        studentUsername = "aris";
        strategyName = "interests";
        mockPositions = new ArrayList<>();
        student = new Student();

        positionId = 105;
        mockPosition = new TraineeshipPosition();
        mockPosition.setId(positionId);

    }

    // testing committe find position method
    @Test
    void testCommitteeFindPositions(){
        // initializing
        mockPositions.add(new TraineeshipPosition());

        // stubbing
        when(positionsSearchFactory.create(strategyName)).thenReturn(mockStrategy);
        when(mockStrategy.search(studentUsername)).thenReturn(mockPositions);

        // act
        String viewName = controller.findPositions(studentUsername, strategyName, model);

        // check
        assertEquals("committee/available_positions", viewName);

        // verification
        verify(model).addAttribute("positions", mockPositions);
        verify(model).addAttribute("student_username", studentUsername);
    }

    @Test
    void testCommitteeAssignPositions(){
        // student is looking for traineeship and position is free
        student.setLookingForTraineeship(true);
        mockPosition.setAssigned(false);

        // stubbing
        when(studentMapper.findByUsername(studentUsername)).thenReturn(student);
        when(positionsMapper.findById(positionId)).thenReturn(Optional.of(mockPosition));

        // act
        String viewName = controller.assignPosition(positionId, studentUsername, model);

        assertEquals("committee/supervisor_assignment", viewName);
        assertTrue(mockPosition.isAssigned()); // position is assigned
        assertFalse(student.isLookingForTraineeship()); // student stopped looking
        assertEquals(mockPosition, student.getAssignedTraineeship());
        assertEquals(student , mockPosition.getStudent());

        // check if the position is saved
        verify(model).addAttribute("position_id", positionId);

        verify(positionsMapper).save(mockPosition);
    }

    @Test
    void testCommitteeAssignSupervisor(){
        when(supervisorAssigmentFactory.create(strategyName)).thenReturn(mockAssignmentStrategy);


        String viewName = controller.assignSupervisor(positionId, strategyName, model);

        assertEquals("committee/dashboard", viewName);

        // verify that the Factory was called with the right strategy name
        verify(supervisorAssigmentFactory).create(strategyName);

        // verify that the strategy run the assignment with the correct positionId
        verify(mockAssignmentStrategy).assign(positionId);
    }
}
