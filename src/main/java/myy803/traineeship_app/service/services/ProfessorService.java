package myy803.traineeship_app.service.services;

import myy803.traineeship_app.domain.Evaluation;
import myy803.traineeship_app.domain.EvaluationType;
import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.ProfessorMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorMapper professorMapper;
    @Autowired
    private TraineeshipPositionsMapper traineeshipPositionsMapper;

    public Professor retrieveProfessorProfile(String username){

        Professor professor = professorMapper.findByUsername(username);
        if(professor == null){
            professor = new Professor(username);
        }

        return professor;
    }

    // save professor
    public void saveProfessorProfile(Professor professor){

        professorMapper.save(professor);
    }


    public List<TraineeshipPosition> listSupervisedPositions(String username){
        Professor professor = professorMapper.findByUsername(username);

        if(professor.getSupervisedPositions() == null){
            return new ArrayList<>();
        }

        return professor.getSupervisedPositions();
    }



    public void fillEvaluation(Integer positionId, Evaluation evaluation){

        // find position from the database
        TraineeshipPosition position = traineeshipPositionsMapper.findById(positionId)
                .orElseThrow(() -> new RuntimeException("Position not found"));

        if(position.getStudent() == null){
            throw new RuntimeException("Cannot evaluate unassigned position");
        }

        // set professor evaluation
        evaluation.setEvaluationType(EvaluationType.PROFESSOR_EVALUATION);

        Evaluation existing = getProfessorEvaluation(positionId);
        if(existing != null){
            existing.setMotivation(evaluation.getMotivation());
            existing.setEffectiveness(evaluation.getEffectiveness());
            existing.setEfficiency(evaluation.getEfficiency());
            existing.setFacilities(evaluation.getFacilities());
            existing.setGuidance(evaluation.getGuidance());
        }else {
            position.getEvaluations().add(evaluation);
        }

        traineeshipPositionsMapper.save(position);
    }

    public Evaluation getProfessorEvaluation(Integer positionId){

        TraineeshipPosition position = traineeshipPositionsMapper.findById(positionId)
                .orElseThrow(() -> new RuntimeException("Position not found"));


        return position.getEvaluations().stream()
                .filter(e -> e.getEvaluationType() == EvaluationType.PROFESSOR_EVALUATION)
                .findFirst()
                .orElse(null);
    }
}
