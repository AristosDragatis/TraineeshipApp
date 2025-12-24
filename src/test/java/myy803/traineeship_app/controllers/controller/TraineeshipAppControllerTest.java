package myy803.traineeship_app.controllers.controller;

import myy803.traineeship_app.controllers.searchstrategies.PositionsSearchFactory;
import myy803.traineeship_app.controllers.supervisorsearchstrategies.SupervisorAssigmentFactory;
import myy803.traineeship_app.mappers.CompanyMapper;
import myy803.traineeship_app.mappers.ProfessorMapper;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TraineeshipAppControllerTest {

    @Mock
    private PositionsSearchFactory searchFactory;

    @Mock
    private SupervisorAssigmentFactory assigmentFactory;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private ProfessorMapper professorMapper;

    @Mock
    private TraineeshipPositionsMapper positionsMapper;

    @Mock
    private CompanyMapper companyMapper;

    @Mock
    private Model model;


    @Test
    void testCommitteeFindPositions(){

    }

}
