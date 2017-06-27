package soaiknowdossier.service;

import soaiknowdossier.model.Semester;

import java.util.List;

/**
 * Created by SimonaS on 02/05/2017.
 */
public interface SemesterService {
    Semester save(String name, String field, String quota, double price, long usetID);
    List<Semester> findByUserId(long id);
    Semester findById(long id);
}
