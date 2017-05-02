package soaiknowdossier.service;

import soaiknowdossier.model.RegisterSubject;

import java.util.List;

/**
 * Created by SimonaS on 02/05/2017.
 */
public interface RegisterSubjectService {
    List<RegisterSubject> findByUserId(long id);
}
