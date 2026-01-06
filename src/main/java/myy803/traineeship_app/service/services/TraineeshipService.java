package myy803.traineeship_app.service.services;

import myy803.traineeship_app.controllers.searchstrategies.PositionsSearchFactory;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeshipService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private PositionsSearchFactory positionsSearchFactory;

    @Autowired
    private TraineeshipPositionsMapper positionsMapper;


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
}
