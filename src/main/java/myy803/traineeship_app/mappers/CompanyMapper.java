package myy803.traineeship_app.mappers;

import myy803.traineeship_app.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyMapper extends JpaRepository<Company, String> {
    Company findByUsername(String username);

    List<Company> findByCompanyLocation(String location);
}
