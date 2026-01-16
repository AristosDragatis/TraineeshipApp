package myy803.traineeship_app.controllers;

import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.service.services.TraineeshipService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/committee")
public class CommitteeRestController {

    @Autowired
    private TraineeshipService traineeshipService;

    // fetch the list with in progress traineeships
    @GetMapping("/list_traineeship_applications")
    public List<Student> listApplications(){
        return traineeshipService.listTraineeshipApplications();
    }

    // assign supervisor
    @PostMapping("assign_supervisor")
    public ResponseEntity<String> assignSupervisor(
            @RequestParam("selected_position_id") Integer positionId,
            @RequestParam("strategy") String strategy){

        try{
            System.out.println("REST Request: Assign supervisor via strategy: " + strategy);

            // call the service layer to make the assignment
            traineeshipService.assignSupervisor(positionId, strategy);

            return ResponseEntity.ok("Success! Supervisor assigned to position" + positionId + "using " + strategy);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error during assignment: " + e.getMessage());
        }
    }

    // assign position to student
    @PostMapping("assign_position")
    public ResponseEntity<String> assignPosition(
            @RequestParam("selected_position_id") Integer positionId,
            @RequestParam("applicant_username") String studentUsername){

            try{
                traineeshipService.assignPositionToStudent(positionId, studentUsername);

                return ResponseEntity.ok("Success! Student: " + studentUsername + " assigned to position " + positionId);
            } catch(Exception e) {
                return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    @GetMapping("view_details")
    public TraineeshipPosition viewDetails(@RequestParam("id") Integer id){
        return traineeshipService.getPositionDetails(id);
    }

    // passfail grading
    @PostMapping("/complete_process")
    public ResponseEntity<String> completeProcess(
            @RequestParam("id") Integer id,
            @RequestParam("passFail") boolean passFail){

        traineeshipService.finalizeTraineeship(id, passFail);
        return ResponseEntity.ok("Process completed. Pass " + passFail);
    }
}
