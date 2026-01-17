package myy803.traineeship_app.services;

import myy803.traineeship_app.domain.Company;
import myy803.traineeship_app.domain.Evaluation;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.CompanyMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;
import myy803.traineeship_app.service.services.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyMapper companyMapper;
    @Mock
    private TraineeshipPositionsMapper positionsMapper;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void testSavePosition() {
        // Arrange
        String username = "comp1";
        Company company = new Company(username);
        company.setPositions(new ArrayList<>());
        TraineeshipPosition newPos = new TraineeshipPosition();

        when(companyMapper.findByUsername(username)).thenReturn(company);

        // Act
        companyService.savePosition(newPos, username);

        // Assert
        assertEquals(company, newPos.getCompany());
        assertEquals(1, company.getPositions().size());
        verify(companyMapper).save(company);
    }

    @Test
    void testDeletePosition_Success() {
        // Arrange
        String username = "comp1";
        Integer posId = 10;

        Company company = new Company(username);
        company.setPositions(new ArrayList<>());

        TraineeshipPosition pos = new TraineeshipPosition();
        pos.setId(posId);
        pos.setStudent(null); // has no student, so it can be deleted
        company.getPositions().add(pos);

        when(companyMapper.findByUsername(username)).thenReturn(company);

        companyService.deletePosition(posId, username);

        assertTrue(company.getPositions().isEmpty());
        verify(positionsMapper).deleteById(posId);
    }

    @Test
    void testFillEvaluation_ThrowsExceptionIfNoStudent() {
        // arrange
        Integer posId = 10;
        TraineeshipPosition pos = new TraineeshipPosition();
        pos.setStudent(null); // no student

        when(positionsMapper.findById(posId)).thenReturn(Optional.of(pos));

        // act and assert
        assertThrows(RuntimeException.class, () -> {
            companyService.fillEvaluation(posId, new Evaluation());
        });
    }
}