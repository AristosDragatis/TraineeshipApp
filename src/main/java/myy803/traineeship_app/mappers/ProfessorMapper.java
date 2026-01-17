package myy803.traineeship_app.mappers;

import myy803.traineeship_app.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorMapper extends JpaRepository<Professor, String> {

    Professor findByUsername(String username);

}
