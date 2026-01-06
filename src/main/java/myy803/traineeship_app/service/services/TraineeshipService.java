package myy803.traineeship_app.service.services;

import myy803.traineeship_app.controllers.searchstrategies.PositionsSearchFactory;
import myy803.traineeship_app.controllers.searchstrategies.PositionsSearchStrategy;
import myy803.traineeship_app.controllers.supervisorsearchstrategies.SupervisorAssigmentFactory;
import myy803.traineeship_app.controllers.supervisorsearchstrategies.SupervisorAssignmentStrategy;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeshipService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private PositionsSearchFactory positionsSearchFactory;
    @Autowired
    private TraineeshipPositionsMapper positionsMapper;
    @Autowired
    private SupervisorAssigmentFactory supervisorAssigmentFactory;

    public List<Student> listTraineeshipApplications(){
        return studentMapper.findByLookingForTraineeshipTrue();
    }

    public List<TraineeshipPosition> findPositionsForStudent(String username, String strategy){
        PositionsSearchStrategy searchStrategy = positionsSearchFactory.create(strategy);
        return searchStrategy.search(username);
    }

    // assign position to student
    public void assignPositionToStudent(Integer positionId, String studentUsername){

        Student student = studentMapper.findByUsername(studentUsername);
        TraineeshipPosition position = positionsMapper.findById(positionId).get();

        position.setAssigned(true);
        position.setStudent(student);

        student.setAssignedTraineeship(position);
        student.setLookingForTraineeship(false);

        positionsMapper.save(position);
    }

    // assign supervised position to professor
    public void assignSupervisor(Integer positionId, String strategy){
        SupervisorAssignmentStrategy assignmentStrategy = supervisorAssigmentFactory.create(strategy);
        assignmentStrategy.assign(positionId);
    }
}
