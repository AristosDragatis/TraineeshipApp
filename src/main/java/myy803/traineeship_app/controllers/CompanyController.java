package myy803.traineeship_app.controllers;

import myy803.traineeship_app.domain.Company;
import myy803.traineeship_app.domain.Evaluation;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.service.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RequestMapping("/company/")
@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/dashboard")
    public String getCompanyDashboard() {

        return "company/dashboard";
    }

    @RequestMapping("/profile")
    public String retrieveCompanyProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.err.println("Logged use: " + username);

        // service call
        Company company = companyService.getCompanyProfile(username);

        model.addAttribute("company", company);
        return "company/profile";
    }

    @RequestMapping("/save_profile")
    public String saveProfile(@ModelAttribute("profile") Company company, Model theModel) {

        // service call
        companyService.companySaveProfile(company);

        return "company/dashboard";
    }

    @RequestMapping("/list_available_positions")
    public String listAvailablePositions(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.err.println("Logged use: " + username);

        List<TraineeshipPosition> positions = companyService.listAvailablePositions(username);

        model.addAttribute("positions", positions);

        return "company/available_positions";
    }

    @RequestMapping("/show_position_form")
    String showPositionForm(Model model) {

        TraineeshipPosition position = new TraineeshipPosition();

        model.addAttribute("position", position);

        return "company/position";

    }

    @RequestMapping("/save_position")
    public String savePosition(@ModelAttribute("position") TraineeshipPosition position, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // call service layer
        companyService.savePosition(position, username);

        return "redirect:/company/dashboard";
    }

    @RequestMapping("/list_assigned_positions")
    public String listAssignedPositions(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<TraineeshipPosition> assignedList = companyService.getAssignedPositions(username);
        model.addAttribute("assignedPositions", assignedList);

        return "company/assigned_positions";

    }

    @RequestMapping("/delete_position")
    public String deletePosition(@RequestParam("positionId") Integer positionId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        companyService.deletePosition(positionId, username);

        return "redirect:/company/list_available_positions";
    }

    @RequestMapping("/save_evaluation")
    public String saveEvaluation(@RequestParam("positionId") Integer positionId,
                                 @ModelAttribute("evaluation") Evaluation evaluation) {

        // call the service method
        companyService.fillEvaluation(positionId, evaluation);

        return "redirect:/company/list_assigned_positions";

    }

    @RequestMapping("/show_evaluation_form")
    public String showEvaluationForm(@RequestParam("positionId") Integer positionId, Model model) {

        Evaluation evaluation = companyService.getCompanyEvaluation(positionId);

        if (evaluation == null) {
            evaluation = new Evaluation();
        }

        model.addAttribute("positionId", positionId);
        model.addAttribute("evaluation", evaluation);

        return "company/evaluation_form";
    }

    @RequestMapping("/view_evaluation")
    public String viewEvaluationForm(@RequestParam("positionId") Integer positionId, Model model) {
        Evaluation evaluation = companyService.getCompanyEvaluation(positionId);

        if (evaluation == null) {
            return "redirect:/company/show_evaluation_form?positionId=" + positionId;
        }

        model.addAttribute("evaluation", evaluation);
        model.addAttribute("positionId", positionId);
        return "company/view_evaluation";
    }

}
