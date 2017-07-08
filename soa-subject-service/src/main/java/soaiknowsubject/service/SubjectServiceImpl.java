package soaiknowsubject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soaiknowsubject.model.Subject;
import soaiknowsubject.repository.SubjectRepository;

import java.util.List;

/**
 * Created by SimonaS on 26/06/2017.
 */

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public Subject save(String code, String name, String description, String professor, Long semesterID) {
        Subject subject = new Subject(code, name, description, professor);
        subject.setSemesterID(semesterID);
        return subjectRepository.save(subject);
    }

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findOne(id);
    }

    @Override
    public List<Subject> findAll() {
        return (List<Subject>) subjectRepository.findAll();
    }

    @Override
    public List<Subject> findSubjectsBySemester(long id) {
        return subjectRepository.findBySemesterID(id);
    }
}
