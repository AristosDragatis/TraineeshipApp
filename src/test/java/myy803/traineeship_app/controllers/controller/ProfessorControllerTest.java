package myy803.traineeship_app.controllers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import myy803.traineeship_app.controllers.ProfessorController;
import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.mappers.ProfessorMapper;

@ExtendWith(MockitoExtension.class)
class ProfessorControllerTest {

    @Mock
    private ProfessorMapper professorMapper;

    @Mock
    private Model model;

    @InjectMocks
    private ProfessorController controller;

    @Test
    void testSaveProfile() {
        // ARRANGE
        Professor prof = new Professor();
        prof.setUsername("prof1");

        // ACT
        String viewName = controller.saveProfile(prof, model);

        // ASSERT
        verify(professorMapper).save(prof);
        assertEquals("professor/dashboard", viewName); // Προσαρμόζεις ανάλογα το return
    }
}