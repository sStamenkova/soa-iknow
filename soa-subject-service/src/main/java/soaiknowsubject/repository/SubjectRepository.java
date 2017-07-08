package soaiknowsubject.repository;

import org.springframework.data.repository.CrudRepository;
import soaiknowsubject.model.Subject;

import java.util.List;

/**
 * Created by SimonaS on 21/06/2017.
 */
public interface SubjectRepository extends CrudRepository<Subject, Long> {
    List<Subject> findBySemesterID(long id);
}
