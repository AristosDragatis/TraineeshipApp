package myy803.traineeship_app.mappers;

import myy803.traineeship_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends JpaRepository<User, String> {

    User findByUsername(String username);

}
