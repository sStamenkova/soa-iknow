package soaiknowdossier.service;

import soaiknowdossier.model.RegisterSubject;

import java.util.Date;
import java.util.List;

/**
 * Created by SimonaS on 02/05/2017.
 */
public interface RegisterSubjectService {
    List<RegisterSubject> findByUserId(long id);
    RegisterSubject save(long serialNumber, boolean isConfirmed, Date date, boolean areTaxesPaid, String examSession, long subjectID, long userID);
}
