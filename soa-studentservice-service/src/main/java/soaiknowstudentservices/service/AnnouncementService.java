package soaiknowstudentservices.service;

import soaiknowstudentservices.model.Announcement;

import java.util.List;

/**
 * Created by Popov on 01.7.2017.
 */
public interface AnnouncementService {

    List<Announcement> findAllAnnouncements();

    Announcement setNewAnnouncement(String title, String content);

    Announcement findAnnouncement(Long id);
}
