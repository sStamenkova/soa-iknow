package soaiknowdossier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import soaiknowdossier.model.RegisterSubject;
import soaiknowdossier.model.Semester;
import soaiknowdossier.service.RegisterSubjectService;
import soaiknowdossier.service.SemesterService;

import java.util.Date;
import java.util.List;

/**
 * Created by SimonaS on 02/05/2017.
 */

@RestController
@RequestMapping(value = "/")
public class StudentDossierController {
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RegisterSubjectService registerSubjectService;

    @Autowired
    SemesterService semesterService;

    @RequestMapping(value = "studentRegistry")
    public List<RegisterSubject> listStudentRegisters(@RequestParam(value = "student") long id){
        return registerSubjectService.findByUserId(id);
    }


    @RequestMapping(value = "semesters")
    public List<Semester> getUserSemesters(@RequestParam(value = "student") long id){
        return semesterService.findByUserId(id);
    }

    @RequestMapping(value = "registerSubject", method = RequestMethod.POST)
    public RegisterSubject registerSubject(@RequestParam("serialNumber") long serialNumber,
                                           @RequestParam("isConfirmed") boolean isConfirmed,
                                           @RequestParam("date") Date date,
                                           @RequestParam("areTaxesPaid") boolean areTaxesPaid,
                                           @RequestParam("examSession") String examSession,
                                           @RequestParam("subjectID") long subjectID,
                                           @RequestParam("userID") long userID){

        RegisterSubject registerSubject = null;

        Object user = restTemplate.getForObject("http://localhost:8000/user/" + userID, Object.class);
        Object subject = restTemplate.getForObject("http://localhost:8000/subject/" + subjectID, Object.class);

        if(user != null && subject != null) {
            registerSubject = registerSubjectService.save(serialNumber, isConfirmed, date, areTaxesPaid, examSession, subjectID, userID);
        }

        return registerSubject;
    }

    @RequestMapping(value = "semester", method = RequestMethod.POST)
    public Semester addSemester(@RequestParam("name") String name,
                                @RequestParam("field") String field,
                                @RequestParam("quota") String quota,
                                @RequestParam("price") double price,
                                @RequestParam("userID") long userID) {

        Semester semester = null;
        Object user = restTemplate.getForObject("http://localhost:8000/user/" + userID, Object.class);

        if(user != null) {
            semester = semesterService.save(name, field, quota, price, userID);
        }

        return semester;
    }

    @RequestMapping(value = "semester/{id}")
    public Object getSemester(@PathVariable(value = "id") long id) {
        return semesterService.findById(id);
    }
}
