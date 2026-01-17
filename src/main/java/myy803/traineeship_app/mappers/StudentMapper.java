package myy803.traineeship_app.mappers;

import myy803.traineeship_app.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper extends JpaRepository<Student, String> {
    Student findByUsername(String username);

    List<Student> findByLookingForTraineeshipTrue();
}
