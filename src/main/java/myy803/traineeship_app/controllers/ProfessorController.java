package myy803.traineeship_app.controllers;

import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.mappers.ProfessorMapper;
import myy803.traineeship_app.service.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorMapper professorMapper;

    @Autowired
    private ProfessorService  professorService;


    // ---------- Professor User Stories

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
}
