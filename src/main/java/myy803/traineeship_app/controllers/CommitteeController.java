package myy803.traineeship_app.controllers;

import myy803.traineeship_app.domain.Evaluation;
import myy803.traineeship_app.domain.EvaluationType;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.service.services.TraineeshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/committee/")
@Controller
public class CommitteeController {

    @Autowired
    private TraineeshipService  traineeshipService;

    @RequestMapping("/dashboard")
    public String getCommitteeDashboard(){

        return "committee/dashboard";
    }

    @RequestMapping("/list_traineeship_applications")
    public String listTraineeshipApplications(Model model) {
        List<Student> traineeshipApplications = traineeshipService.listTraineeshipApplications();

        model.addAttribute("traineeship_applications", traineeshipApplications);
        return "committee/traineeship_applications";
    }

    @RequestMapping("/find_positions")
    public String findPositions(
            @RequestParam("selected_student_id") String studentUsername,
            @RequestParam("strategy") String strategy, Model model) {

        List<TraineeshipPosition> positions =  traineeshipService.findPositionsForStudent(studentUsername, strategy);

        model.addAttribute("positions", positions);
        model.addAttribute("student_username", studentUsername);

        return "committee/available_positions";
    }

    @RequestMapping("/assign_position")
    public String assignPosition(
            @RequestParam("selected_position_id") Integer positionId,
            @RequestParam("applicant_username") String studentUsername,
            Model model) {

        // call service layer
        traineeshipService.assignPositionToStudent(positionId, studentUsername);

        model.addAttribute("position_id", positionId);

        return "committee/supervisor_assignment";
    }

    @RequestMapping("/assign_supervisor")
    public String assignSupervisor(
            @RequestParam("selected_position_id") Integer positionId,
            @RequestParam("strategy") String strategy,
            Model model) {

        // call the service layer to assign supervisor
        traineeshipService.assignSupervisor(positionId, strategy);

        return "committee/dashboard";
    }

    @RequestMapping("/list_assigned_traineeships")
    public String listAssignedTraineeships(Model model){

        List<TraineeshipPosition> inProgress = traineeshipService.listProgressTraineeships();
        model.addAttribute("positions", inProgress);
        return "committee/list_assigned_traineeships";
    }

    @RequestMapping("/view_details")
    public String viewDetails(@RequestParam("id") Integer id, Model model) {
        TraineeshipPosition position = traineeshipService.getPositionDetails(id);

        // company evaluation
        Evaluation companyEval = position.getEvaluations().stream()
                .filter(e -> e.getEvaluationType() == EvaluationType.COMPANY_EVALUATION)
                .findFirst().orElse(null);

        // professor evaluation
        Evaluation professorEval = position.getEvaluations().stream()
                .filter(e -> e.getEvaluationType() == EvaluationType.PROFESSOR_EVALUATION)
                .findFirst().orElse(null);

        model.addAttribute("position", position);
        model.addAttribute("companyEval", companyEval);
        model.addAttribute("professorEval", professorEval);

        return "committee/view_details";
    }

    @PostMapping("/complete_process")
    public String completeProcess(@RequestParam("id") Integer id,
                                  @RequestParam("passFail") boolean passFail) {
        traineeshipService.finalizeTraineeship(id, passFail);
        return "redirect:/committee/list_assigned_traineeships";
    }
}
