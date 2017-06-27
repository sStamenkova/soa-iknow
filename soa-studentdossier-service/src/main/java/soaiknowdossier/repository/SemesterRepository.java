package soaiknowdossier.repository;

import org.springframework.data.repository.CrudRepository;
import soaiknowdossier.model.Semester;

import java.util.List;

/**
 * Created by SimonaS on 02/05/2017.
 */
public interface SemesterRepository extends CrudRepository<Semester, Long> {
    List<Semester> findByUserID(long id);
}
