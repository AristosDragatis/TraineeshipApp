package myy803.traineeship_app.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import myy803.traineeship_app.domain.Evaluation;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.ProfessorMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;
import myy803.traineeship_app.service.services.ProfessorService;

@ExtendWith(MockitoExtension.class)
class ProfessorServiceTest {

    @Mock private ProfessorMapper professorMapper;
    @Mock private TraineeshipPositionsMapper positionsMapper;

    @InjectMocks
    private ProfessorService professorService;

    @Test
    void testFillEvaluationNewEvaluation() {
        Integer posId = 1;
        TraineeshipPosition pos = new TraineeshipPosition();
        pos.setStudent(new Student("student1")); // must have student
        pos.setEvaluations(new ArrayList<>());

        Evaluation eval = new Evaluation();
        eval.setMotivation(5);

        when(positionsMapper.findById(posId)).thenReturn(Optional.of(pos));

        professorService.fillEvaluation(posId, eval);

        assertEquals(1, pos.getEvaluations().size());
        verify(positionsMapper).save(pos);
    }
}