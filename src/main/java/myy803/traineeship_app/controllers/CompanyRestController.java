package myy803.traineeship_app.controllers;

import myy803.traineeship_app.domain.Company;
import myy803.traineeship_app.domain.Evaluation;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.service.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyRestController {


    @Autowired
    private CompanyService companyService;

    // get company profile
    @GetMapping("/profile")
    public Company getProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return companyService.getCompanyProfile(username);
    }


    // save company profile
    @PostMapping("/save_profile")
    public ResponseEntity<String> saveProfile(@RequestBody Company company) {
        companyService.companySaveProfile(company);
        return ResponseEntity.ok("Profile saved!");
    }


    // list with available positions
    @GetMapping("/list_available_positions")
    public List<TraineeshipPosition> listAvailable() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return companyService.listAvailablePositions(username);
    }


    // save position
    @PostMapping("/save_position")
    public ResponseEntity<String> savePosition(@RequestBody TraineeshipPosition position) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        companyService.savePosition(position, username);
        return ResponseEntity.ok("Position saved");
    }


    // delete a position
    @DeleteMapping("/delete_position")
    public ResponseEntity<String> deletePosition(@RequestParam("positionId") Integer positionId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        companyService.deletePosition(positionId, username);
        return ResponseEntity.ok("Position deleted");
    }


    // list assigned positions for evaluation
    @GetMapping("/list_assigned_positions")
    public List<TraineeshipPosition> listAssigned() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return companyService.getAssignedPositions(username);
    }


    // get company evaluation
    @GetMapping("/show_evaluation")
    public Evaluation getEvaluation(@RequestParam("positionId") Integer positionId) {
        return companyService.getCompanyEvaluation(positionId);
    }

    // save evaluation
    @PostMapping("save_evaluation")
    public ResponseEntity<String> saveEvaluation(
            @RequestParam("positionId") Integer positionId,
            @RequestBody Evaluation evaluation) {
        companyService.fillEvaluation(positionId, evaluation);
        return ResponseEntity.ok("Evaluation saved.");
    }
}
