package myy803.traineeship_app.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import myy803.traineeship_app.domain.Evaluation;
import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.service.services.ProfessorService;

@RestController
@RequestMapping("/api/professor")
public class ProfessorRestController {

    @Autowired
    private ProfessorService professorService;

    // get professor profile
    @GetMapping("/profile")
    public Professor getProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return professorService.retrieveProfessorProfile(username);
    }

    // save profile (JSON body)
    @PostMapping("/save_profile")
    public ResponseEntity<String> saveProfile(@RequestBody Professor professor) {
        professorService.saveProfessorProfile(professor);
        return ResponseEntity.ok("Profile saved");
    }

    // list supervised traineeship positions
    @GetMapping("/list_traineeships")
    public List<TraineeshipPosition> listTraineeships() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return professorService.listSupervisedPositions(username);
    }

    // show evaluation if it exists
    @GetMapping("/show_evaluation")
    public Evaluation getEvaluation(@RequestParam("positionId") Integer positionId) {
        return professorService.getProfessorEvaluation(positionId);
    }

    // save evaluation (JSON)
    @PostMapping("/save_evaluation")
    public ResponseEntity<String> saveEvaluation(
            @RequestParam("positionId") Integer positionId,
            @RequestBody Evaluation evaluation) {
        professorService.fillEvaluation(positionId, evaluation);
        return ResponseEntity.ok("Evaluation saved");
    }
}