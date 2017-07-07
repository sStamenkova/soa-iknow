package edu.soa.iknow.service;

import edu.soa.iknow.model.ProfessorInfo;

/**
 * Created by Popov on 07.7.2017.
 */
public interface ProfessorService {
    void changeNumberOfLessons(int number, long userId);

    void changeStatus(String status, long id);

    void incrementYears(int years, long id);

    void workTime(boolean time, long id);

    ProfessorInfo createProfInfo(String fieldOfLessons, String status,
                                 int numberOfCurrentSubjects,
                                 int yearsOfWork,
                                 boolean isFullTime, long userId);
    ProfessorInfo getProfessorInfo(long id);
}
