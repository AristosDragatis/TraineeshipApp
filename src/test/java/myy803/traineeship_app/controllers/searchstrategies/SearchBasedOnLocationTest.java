package myy803.traineeship_app.controllers.searchstrategies;

import myy803.traineeship_app.domain.Company;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.CompanyMapper;
import myy803.traineeship_app.mappers.StudentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SearchBasedOnLocationTest {

    @Mock
    private CompanyMapper companyMapper;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private SearchBasedOnLocation strategy;

    @Test
    void testSearchLocationAndFindPositions(){
        String username = "aris";
        String location = "Ioannina";

        Student mockStudent = new Student(username);
        mockStudent.setPreferredLocation(location);

        // initialize an empty list to avoid errors
        List<TraineeshipPosition> positionsList = new ArrayList<>();

        // create a company at this preferred location
        Company mockCompany = new Company("TeamViewer", "Teamviewer", location, positionsList);

        // create a new position at the company
        TraineeshipPosition  position = new TraineeshipPosition();
        position.setTitle("Backend Dev");
        position.setAssigned(false); // getAvailablePositions() filters the assigned to false
        mockCompany.addPosition(position);


        // mocking behaviour
        when(studentMapper.findByUsername(username)).thenReturn(mockStudent);

        when(companyMapper.findByCompanyLocation(location)).thenReturn(Arrays.asList(mockCompany));

        // execute the search method by passing the username
        List<TraineeshipPosition> results = strategy.search(username);


        // check
        assertNotNull(results);
        assertEquals(1, results.size(), "1 position must be found");
        assertEquals("Backend Dev", results.get(0).getTitle());


        // verify company
        verify(companyMapper).findByCompanyLocation(location);


    }
}
