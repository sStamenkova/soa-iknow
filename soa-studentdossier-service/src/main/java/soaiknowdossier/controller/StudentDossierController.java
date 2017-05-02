package soaiknowdossier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soaiknowdossier.model.RegisterSubject;
import soaiknowdossier.model.Semester;
import soaiknowdossier.service.RegisterSubjectService;
import soaiknowdossier.service.SemesterService;

import java.util.List;

/**
 * Created by SimonaS on 02/05/2017.
 */

@RestController
@RequestMapping(value = "/")
public class StudentDossierController {

    @Autowired
    RegisterSubjectService registerSubjectService;

    @Autowired
    SemesterService semesterService;

    @RequestMapping(value = "studentRegistry")
    public List<RegisterSubject> listStudentRegisters(@RequestParam(value = "student") long id){
        return registerSubjectService.findByUserId(id);
    }

    @RequestMapping(value = "insertSemester", method = RequestMethod.POST)
    public Semester registration(@RequestParam("name") String name,
                                 @RequestParam("field") String field,
                                 @RequestParam("quota") String quota,
                                 @RequestParam("paid") double paid,
                                 @RequestParam("price") double price){

        return semesterService.save(name, field, quota, paid, price);
    }

    @RequestMapping(value = "semesters")
    public List<Semester> getUserSemesters(@RequestParam(value = "student") long id){
        return semesterService.findByUserId(id);
    }
}
