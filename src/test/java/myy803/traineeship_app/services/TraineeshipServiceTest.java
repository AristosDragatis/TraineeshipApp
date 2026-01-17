package myy803.traineeship_app.services;

import myy803.traineeship_app.controllers.searchstrategies.AbstractPositionsSearch;
import myy803.traineeship_app.controllers.searchstrategies.PositionsSearchFactory;
import myy803.traineeship_app.controllers.supervisorsearchstrategies.SupervisorAssigmentFactory;
import myy803.traineeship_app.controllers.supervisorsearchstrategies.SupervisorAssignmentStrategy;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.ProfessorMapper;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;
import myy803.traineeship_app.service.services.TraineeshipService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TraineeshipServiceTest {

    @Mock
    private StudentMapper studentMapper;
    @Mock
    private PositionsSearchFactory positionsSearchFactory;
    @Mock
    private TraineeshipPositionsMapper positionsMapper;
    @Mock
    private ProfessorMapper professorMapper;
    @Mock
    private SupervisorAssigmentFactory assigmentFactory;

    @Mock
    private AbstractPositionsSearch mockSearchStrategy;
    @Mock
    private SupervisorAssignmentStrategy mockAssignStrategy;

    @InjectMocks
    private TraineeshipService service;

    @Test
    void testFindPositionsForStudent() {
        String username = "aris";
        String strategyType = "interests";
        Student student = new Student(username);

        when(studentMapper.findByUsername(username)).thenReturn(student);
        when(positionsMapper.findAll()).thenReturn(new ArrayList<>());
        when(positionsSearchFactory.create(strategyType)).thenReturn(mockSearchStrategy);
        when(mockSearchStrategy.filter(anyList(), eq(student))).thenReturn(new ArrayList<>());

        service.findPositionsForStudent(username, strategyType);

        verify(positionsSearchFactory).create(strategyType);
        verify(mockSearchStrategy).filter(anyList(), eq(student));
    }

    @Test
    void testAssignPositionToStudent() {
        Integer posId = 100;
        String username = "aris";

        Student student = new Student(username);
        student.setLookingForTraineeship(true);

        TraineeshipPosition position = new TraineeshipPosition();
        position.setAssigned(false);

        when(studentMapper.findByUsername(username)).thenReturn(student);
        when(positionsMapper.findById(posId)).thenReturn(Optional.of(position));

        service.assignPositionToStudent(posId, username);

        assertTrue(position.isAssigned());
        assertFalse(student.isLookingForTraineeship());
        assertEquals(student, position.getStudent());
        verify(positionsMapper).save(position);
    }

    @Test
    void testAssignSupervisor() {
        Integer posId = 100;
        String strategyType = "load";
        TraineeshipPosition position = new TraineeshipPosition();

        when(positionsMapper.findById(posId)).thenReturn(Optional.of(position));
        when(professorMapper.findAll()).thenReturn(new ArrayList<>());
        when(assigmentFactory.create(strategyType)).thenReturn(mockAssignStrategy);

        service.assignSupervisor(posId, strategyType);

        verify(mockAssignStrategy).performAssignment(anyList(), eq(position));
        verify(positionsMapper).save(position);
    }
}