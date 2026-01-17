package myy803.traineeship_app.controllers.supervisorsearchstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SupervisorAssigmentFactory {

    @Autowired
    private AssignmentBasedOnLoad assignmentBasedOnLoad;

    @Autowired
    private AssignmentBasedOnInterests assignmentBasedOnInterests;

    public SupervisorAssignmentStrategy create(String type) {
        if (type.equals("load")) {
            return assignmentBasedOnLoad;
        } else if (type.equals("interests")) {
            return assignmentBasedOnInterests;
        } else {
            // default
            return assignmentBasedOnLoad;
        }
    }

}
