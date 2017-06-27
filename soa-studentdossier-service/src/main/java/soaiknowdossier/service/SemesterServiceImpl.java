package soaiknowdossier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soaiknowdossier.model.Semester;
import soaiknowdossier.repository.SemesterRepository;

import java.util.List;

/**
 * Created by SimonaS on 02/05/2017.
 */

@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    SemesterRepository semesterRepository;

    @Override
    public Semester save(String name, String field, String quota, double price, long userID) {
        Semester semester = new Semester(name, field, quota, price);
        semester.setUserID(userID);
        return semesterRepository.save(semester);
    }

    @Override
    public List<Semester> findByUserId(long id) {
        return semesterRepository.findByUserID(id);
    }

    public Semester findById(long id){
        return semesterRepository.findOne(id);
    }
}
