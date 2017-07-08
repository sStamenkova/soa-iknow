package soaiknowsubject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import soaiknowsubject.model.Subject;
import soaiknowsubject.service.SubjectService;

import java.util.List;

/**
 * Created by SimonaS on 30/04/2017.
 */

@RestController
@RequestMapping(value = "/")
public class SubjectController {
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = "subject", method = RequestMethod.POST)
    public Subject save(@RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("description") String description,@RequestParam("professor") String professor, @RequestParam("semesterID") Long semesterID) {
        Subject subject = null;

        Object semester = restTemplate.getForObject("http://localhost:8000/semester/" + semesterID, Object.class);
        if(semester != null) {
            subject = subjectService.save(code, name, description, professor, semesterID);
        }

        return subject;
    }

    @RequestMapping(value = "subject/{id}")
    public Subject getSubjectById(@PathVariable("id") long id){
        return subjectService.findById(id);
    }

    @RequestMapping(value = "subject")
    public List<Subject> getSubjects(){
        return subjectService.findAll();
    }

    @RequestMapping(value = "subjectsBySemester")
    public List<Subject> getSubjectsBySemester(@RequestParam("semester") Long id){
        return subjectService.findSubjectsBySemester(id);
    }
}
