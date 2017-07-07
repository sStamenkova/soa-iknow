package edu.soa.iknow.service;

import edu.soa.iknow.model.ProfessorInfo;
import edu.soa.iknow.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Popov on 07.7.2017.
 */
@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public void changeNumberOfLessons(int number, long id) {
        ProfessorInfo professorInfo = professorRepository.findOne(id);
        professorInfo.setNumberOfCurrentSubjects(number);
        professorRepository.save(professorInfo);
    }

    @Override
    public void changeStatus(String status, long id) {
        ProfessorInfo professorInfo = professorRepository.findOne(id);
        professorInfo.setStatus(status);
        professorRepository.save(professorInfo);
    }

    @Override
    public void incrementYears(int years, long id) {
        ProfessorInfo professorInfo = professorRepository.findOne(id);
        professorInfo.setYearsOfWork(years);
        professorRepository.save(professorInfo);

    }

    @Override
    public void workTime(boolean time, long id) {
        ProfessorInfo professorInfo = professorRepository.findOne(id);
        professorInfo.setFullTime(time);
        professorRepository.save(professorInfo);
    }

    @Override
    public ProfessorInfo createProfInfo(String fieldOfLessons, String status,
                                        int numberOfCurrentSubjects, int yearsOfWork,
                                        boolean isFullTime, long userId) {
        ProfessorInfo professorInfo = new ProfessorInfo(fieldOfLessons, numberOfCurrentSubjects,
                status, yearsOfWork, isFullTime, userId);
        return professorRepository.save(professorInfo);
    }

    @Override
    public ProfessorInfo getProfessorInfo(long id) {
        return professorRepository.findOne(id);
    }
}


