package soaiknowdossier.repository;

import org.springframework.data.repository.CrudRepository;
import soaiknowdossier.model.RegisterSubject;

import java.util.List;

/**
 * Created by SimonaS on 02/05/2017.
 */
public interface RegisterSubjectRepository extends CrudRepository<RegisterSubject, Long> {
    List<RegisterSubject> findByUserIdAndExamSession(long id, String examSession);
    List<RegisterSubject> findBySubjectId(long id);
    List<RegisterSubject> findByUserId(long id);
    RegisterSubject findByUserIdAndSubjectId(long user_id, long subject_id);
}
