package myy803.traineeship_app.controllers;

import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.service.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/student/")
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;


    // ---------- Student User Stories

    @RequestMapping("/dashboard")
    public String getStudentDashboard(){

        return "student/dashboard";
    }

    @RequestMapping("/profile")
    public String retrieveStudentProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String studentUsername = authentication.getName();
        System.err.println("Logged use: " + studentUsername);

        // service layer call
        Student student = studentService.retrieveStudentProfile(studentUsername);

        model.addAttribute("student", student);

        return "student/profile";
    }

    @RequestMapping("/save_profile")
    public String saveProfile(@ModelAttribute("student") Student student, Model theModel) {

        studentService.saveStudentProfile(student);

        return "student/dashboard";
    }


    // student logbook user story
    @RequestMapping("/logbook")
    public String logbook(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return "student/logbook";
    }
}