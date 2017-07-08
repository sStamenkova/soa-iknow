package soaiknowsubject.service;

import soaiknowsubject.model.Subject;

import java.util.List;

/**
 * Created by SimonaS on 26/06/2017.
 */
public interface SubjectService {
    Subject save(String code, String name, String description, String professor, Long semesterID);
    Subject findById(Long id);
    List<Subject> findAll();
    List<Subject> findSubjectsBySemester(long id);
}
