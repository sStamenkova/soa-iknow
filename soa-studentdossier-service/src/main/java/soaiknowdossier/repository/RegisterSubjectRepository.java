package soaiknowdossier.repository;

import org.springframework.data.repository.CrudRepository;
import soaiknowdossier.model.RegisterSubject;

import java.util.List;

/**
 * Created by SimonaS on 02/05/2017.
 */
public interface RegisterSubjectRepository extends CrudRepository<RegisterSubject, Long> {
    List<RegisterSubject> findByUserIDAndExamSession(long id, String examSession);
    List<RegisterSubject> findBySubjectID(long id);
    List<RegisterSubject> findByUserID(long id);
    RegisterSubject findByUserIDAndSubjectID(long user_id, long subject_id);
}
