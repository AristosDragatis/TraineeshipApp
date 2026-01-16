package myy803.traineeship_app.controllers;

import myy803.traineeship_app.domain.LogBook;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.service.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;


    // GET student profile (retrieveStudentProfile)
    @GetMapping("/profile")
    public Student getProfile(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return studentService.retrieveStudentProfile(username);
    }

    // RequestBody parameter for fetching JSON
    @PostMapping("/save_profile")
    public ResponseEntity<String> saveProfile(@RequestBody Student student){
        studentService.saveStudentProfile(student);
        return ResponseEntity.ok("Profile updated successfully!");
    }


    // GET student logbook
    @GetMapping("/logbook")
    public List<LogBook> getLogbook(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return studentService.getStudentLogBook(username);
    }


    // POST add logbook entry
    @PostMapping("/logbook/add")
    public ResponseEntity<String> addLogBookEntry(@RequestParam String content) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        studentService.addEntryToLogBook(username, content);
        return ResponseEntity.ok("Log entry added.");
    }
}
