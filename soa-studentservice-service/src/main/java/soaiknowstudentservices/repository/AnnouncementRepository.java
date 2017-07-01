package soaiknowstudentservices.repository;

import org.springframework.data.repository.CrudRepository;
import soaiknowstudentservices.model.Announcement;

/**
 * Created by Popov on 01.7.2017.
 */
public interface AnnouncementRepository extends CrudRepository<Announcement,Long> {
}
