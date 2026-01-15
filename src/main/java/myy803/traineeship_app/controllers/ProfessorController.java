package myy803.traineeship_app.controllers;

import myy803.traineeship_app.domain.Evaluation;
import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.ProfessorMapper;
import myy803.traineeship_app.service.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorMapper professorMapper;

    @Autowired
    private ProfessorService  professorService;

    @RequestMapping("/professor/dashboard")
    public String getProfessorDashboard(){

        return "professor/dashboard";
    }

    @RequestMapping("/professor/profile")
    public String retrieveProfessorProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.err.println("Logged use: " + username);

        Professor professor = professorService.retrieveProfessorProfile(username);

        model.addAttribute("professor", professor);

        return "professor/profile";
    }

    @RequestMapping("/professor/save_profile")
    public String saveProfile(@ModelAttribute("profile") Professor professor, Model theModel) {

        professorService.saveProfessorProfile(professor);

        return "professor/dashboard";
    }


    @RequestMapping("professor/list_traineeships")
    public String listTraineeships(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<TraineeshipPosition> positions = professorService.listSupervisedPositions(username);

        model.addAttribute("positions", positions); // send the list to Thymeleaf

        return  "professor/list_traineeships";
    }


    @RequestMapping("professor/show_evaluation_form")
    public String showEvaluationForm(@RequestParam("positionId") Integer positionId, Model model){

        // try to get an existing professors evaluation for this position
        // this ensures that if the professor wants to edit their evaluations, they see the old ones
        Evaluation evaluation = professorService.getProfessorEvaluation(positionId);


        if(evaluation == null){
            evaluation = new Evaluation();
        }


        model.addAttribute("positionId", positionId);
        model.addAttribute("evaluation", evaluation);

        return  "professor/evaluation_form";
    }

    @RequestMapping("professor/save_evaluation")
    public String saveEvaluation(@RequestParam("positionId") Integer positionId,
                                 @ModelAttribute("evaluation") Evaluation evaluation){

        // call the service to save/update the evaluation
        professorService.fillEvaluation(positionId, evaluation);

        return "redirect:/professor/list_traineeships";
    }
}
