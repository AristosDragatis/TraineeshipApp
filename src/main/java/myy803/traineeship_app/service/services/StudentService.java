package myy803.traineeship_app.service.services;

import myy803.traineeship_app.domain.LogBook;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.mappers.LogBookMapper;
import myy803.traineeship_app.mappers.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private LogBookMapper logbookMapper;

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


    // add entry to logbook for students
    public void addEntryToLogBook(String username, String content){
        Student student = studentMapper.findByUsername(username);

        LogBook entry = new LogBook();
        entry.setStudent(student);
        entry.setContent(content);
        entry.setDate(LocalDate.now().toString());

        logbookMapper.save(entry);
    }

    public List<LogBook> getStudentLogBook(String username){
        return logbookMapper.findByStudent_Username(username);
    }
}
