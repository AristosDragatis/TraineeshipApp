package myy803.traineeship_app.controllers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import myy803.traineeship_app.controllers.CompanyController;
import myy803.traineeship_app.domain.Company;
import myy803.traineeship_app.mappers.CompanyMapper;

@ExtendWith(MockitoExtension.class)
class CompanyControllerTest {

    @Mock
    private CompanyMapper companyMapper; 

    @Mock
    private Model model; 

    @InjectMocks
    private CompanyController controller; 

    
    @Test
    void testGetCompanyDashboard() {
        // ACT
        String viewName = controller.getCompanyDashboard();
        
        // ASSERT
        assertEquals("company/dashboard", viewName);
    }

    
    @Test
    void testSaveProfile() {
        // ARRANGE
        Company company = new Company();
        company.setUsername("my_company");
        
        // ACT
                String viewName = controller.saveProfile(company, model);

        // ASSERT
                verify(companyMapper).save(company);
        
                assertEquals("company/dashboard", viewName); 
    }
}