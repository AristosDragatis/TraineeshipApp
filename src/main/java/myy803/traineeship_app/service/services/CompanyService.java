package myy803.traineeship_app.service.services;

import myy803.traineeship_app.domain.Company;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    // retrieve company profile
    public Company getCompanyProfile(String username){
        Company company = companyMapper.findByUsername(username);
        if (company == null) {
            company = new Company(username);
        }
        return company;
    }

    // company save profile
    public void companySaveProfile(Company company){
        companyMapper.save(company);
    }

    public List<TraineeshipPosition> listAvailablePositions(String username){

            Company company = companyMapper.findByUsername(username);
            if(company == null){
                return new ArrayList<>();
            }
            return company.getAvailablePositions();
    }
}
