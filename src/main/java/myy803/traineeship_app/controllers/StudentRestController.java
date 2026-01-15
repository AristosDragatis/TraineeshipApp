package myy803.traineeship_app.controllers;

import myy803.traineeship_app.domain.LogBook;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.service.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    // GET
    @GetMapping("/logbook")
    public List<LogBook> getLogbook(@RequestParam String username){
        return studentService.getStudentLogBook(username);
    }

    // GET student profile (retrieveStudentProfile)
    @GetMapping("profile")
    public Student getProfile(@RequestParam String username){
        return studentService.retrieveStudentProfile(username);
    }


}
