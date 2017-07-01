package soaiknowstudentservices.controller;

/**
 * Created by Popov on 01.7.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soaiknowstudentservices.model.Announcement;
import soaiknowstudentservices.service.AnnouncementService;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementService;

    @RequestMapping(value = "newAnnouncement", method = RequestMethod.POST)
    public Announcement save(@RequestParam("title") String title, @RequestParam("content") String content) {
        Announcement newAnnouncement = announcementService.setNewAnnouncement(title, content);
        return newAnnouncement;
    }

    @RequestMapping(value = "announcement/{id}")
    public Announcement getAnnouncementById(@PathVariable("id") long id) {
        return announcementService.findAnnouncement(id);
    }

    @RequestMapping(value = "announcements")
    public List<Announcement> getAnnouncements() {
        return announcementService.findAllAnnouncements();
    }
}
