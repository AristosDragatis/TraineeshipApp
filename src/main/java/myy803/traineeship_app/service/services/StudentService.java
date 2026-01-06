package myy803.traineeship_app.service.services;

import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.mappers.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;


    public Student retrieveStudentProfile(String studentUsername){
        Student student = studentMapper.findByUsername(studentUsername);
        if(student == null){
            student = new Student(studentUsername);
        }

        return student;
    }

    public void saveStudentProfile(Student student){

        student.setLookingForTraineeship(true);
        studentMapper.save(student);
    }
}
