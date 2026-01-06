package myy803.traineeship_app.service.services;

import myy803.traineeship_app.domain.Company;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.CompanyMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private TraineeshipPositionsMapper traineeshipPositionsMapper;

    // retrieve company profile
    public Company getCompanyProfile(String username) {
        Company company = companyMapper.findByUsername(username);
        if (company == null) {
            company = new Company(username);
        }
        return company;
    }

    // company save profile
    public void companySaveProfile(Company company) {
        companyMapper.save(company);
    }

    public List<TraineeshipPosition> listAvailablePositions(String username) {

        Company company = companyMapper.findByUsername(username);
        if (company == null) {
            return new ArrayList<>();
        }
        return company.getAvailablePositions();
    }

    // save position
    public void savePosition(TraineeshipPosition position, String username) {

        Company company = companyMapper.findByUsername(username);
        position.setCompany(company);
        company.addPosition(position);
        companyMapper.save(company);
    }


    public List<TraineeshipPosition> getAssignedPositions(String companyUsername) {
        Company company = companyMapper.findByUsername(companyUsername);


        return company.getPositions().stream()
                .filter(pos -> pos.getStudent() != null)
                .collect(Collectors.toList());
    }


    // delete a position
    public void deletePosition(Integer positionId, String username) {
        Company company = companyMapper.findByUsername(username);

        // find the position from the companys list
        TraineeshipPosition positionToDelete = company.getPositions()
                .stream()
                .filter(p -> p.getId().equals(positionId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Position not found or not owned by the company") );


        // we dont delete a position that has a student
        if(positionToDelete.getStudent() != null) {
            throw new RuntimeException("Cannot delete an assigned position");
        }

        company.getPositions().remove(positionToDelete);
        traineeshipPositionsMapper.deleteById(positionId);
        companyMapper.save(company);

    }
}
