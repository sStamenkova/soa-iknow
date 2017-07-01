package soaiknowstudentservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soaiknowstudentservices.model.Announcement;
import soaiknowstudentservices.repository.AnnouncementRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Popov on 01.7.2017.
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService{

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Override
    public List<Announcement> findAllAnnouncements() {
        return (List<Announcement>) announcementRepository.findAll();
    }

    @Override
    public Announcement setNewAnnouncement(String title, String content) {
        Announcement announcement = new Announcement();
        announcement.setContent(content);
        announcement.setDate(new Date());
        announcement.setTitle(title);
      return announcementRepository.save(announcement);
    }

    @Override
    public Announcement findAnnouncement(Long id) {
        return announcementRepository.findOne(id);
    }
}
