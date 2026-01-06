package myy803.traineeship_app.service.services;

import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.mappers.ProfessorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorMapper professorMapper;

    public Professor retrieveProfessorProfile(String username){

        Professor professor = professorMapper.findByUsername(username);
        if(professor == null){
            professor = new Professor(username);
        }

        return professor;
    }

    // save professor
    public void saveProfessorProfile(Professor professor){

        professorMapper.save(professor);
    }
}
