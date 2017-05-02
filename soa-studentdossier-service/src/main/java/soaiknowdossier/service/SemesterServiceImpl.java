package soaiknowdossier.service;

import org.springframework.beans.factory.annotation.Autowired;
import soaiknowdossier.model.Semester;
import soaiknowdossier.repository.SemesterRepository;

import java.util.List;

/**
 * Created by SimonaS on 02/05/2017.
 */
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    SemesterRepository semesterRepository;

    @Override
    public Semester save(String name, String field, String quota, double paid, double price) {
        Semester semester = new Semester();
        semester.setName(name);
        semester.setAreTaxesPaid(false);
        semester.setField(field);
        semester.setIsRegistered(true);
        semester.setPaid(paid);
        semester.setQuota(quota);
        semester.setPrice(price);

        return semesterRepository.save(semester);
    }

    @Override
    public List<Semester> findByUserId(long id) {
        return semesterRepository.findByUserId(id);
    }
}
