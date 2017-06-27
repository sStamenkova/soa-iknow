package soaiknowdossier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soaiknowdossier.model.RegisterSubject;
import soaiknowdossier.repository.RegisterSubjectRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by SimonaS on 02/05/2017.
 */
@Service
public class RegisterSubjectServiceImpl implements RegisterSubjectService {

    @Autowired
    RegisterSubjectRepository registerSubjectRepository;

    @Override
    public List<RegisterSubject> findByUserId(long id) {
        return registerSubjectRepository.findByUserID(id);
    }

    public RegisterSubject save(long serialNumber, boolean isConfirmed, Date date, boolean areTaxesPaid, String examSession, long subjectID, long userID){
        RegisterSubject registerSubject = new RegisterSubject(serialNumber, isConfirmed, date, areTaxesPaid, examSession);
        registerSubject.setUserID(userID);
        registerSubject.setSubjectID(subjectID);
        return registerSubjectRepository.save(registerSubject);
    }
}
