package myy803.traineeship_app.controllers;

import myy803.traineeship_app.service.services.TraineeshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/committee")
public class CommitteeRestController {
    @Autowired
    private TraineeshipService traineeshipService;

    // POST
    @PostMapping("assign")
    public ResponseEntity<String> assignSupervisor(
            @RequestParam("positionId") Integer positionId,
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
}
