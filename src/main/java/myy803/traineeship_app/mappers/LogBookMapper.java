package myy803.traineeship_app.mappers;

import myy803.traineeship_app.domain.LogBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogBookMapper extends JpaRepository<LogBook, String> {
    List<LogBook> findByStudent_Username(String username);

}
