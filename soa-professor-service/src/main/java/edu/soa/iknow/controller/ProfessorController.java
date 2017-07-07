package edu.soa.iknow.controller;

import edu.soa.iknow.model.ProfessorInfo;
import edu.soa.iknow.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @RequestMapping(value = "createProfessorInfo", method = RequestMethod.POST)
    public ProfessorInfo createProfessorInfo(@RequestParam("userId") long userId,
                                             @RequestParam("fieldOfLessons") String fieldOfLessons,
                                             @RequestParam("status") String status,
                                             @RequestParam("numberOfSubjects") int numberOfSubjects,
                                             @RequestParam("years") int years,
                                             @RequestParam("fulltime") boolean isFull
    ) {
        ProfessorInfo professorInfo = professorService.createProfInfo(fieldOfLessons, status, numberOfSubjects,
                years, isFull, userId);
        return professorInfo;
    }

    @RequestMapping(value = "professorInfo/{id}")
    public ProfessorInfo getProfessorInfoById(@PathVariable("id") long id) {
        return professorService.getProfessorInfo(id);
    }

    @RequestMapping(value = "changeProfessorWorkExperience", method = RequestMethod.POST)
    public void changeProfessorWorkExperience(@RequestParam("id") long id,
                                              @RequestParam("years") int years) {
        professorService.incrementYears(years, id);
    }

    @RequestMapping(value = "changeProfessorStatus", method = RequestMethod.POST)
    public void changeProfessorStatus(@RequestParam("id") long id,
                                      @RequestParam("status") String status) {
        professorService.changeStatus(status, id);
    }

    @RequestMapping(value = "changeProfessorWorkTime", method = RequestMethod.POST)
    public void changeProfessorWorkTime(@RequestParam("id") long id,
                                        @RequestParam("years") boolean isFull) {
        professorService.workTime(isFull, id);
    }

    @RequestMapping(value = "changeProfessorNumLessons", method = RequestMethod.POST)
    public void changeProfessorWorkTime(@RequestParam("id") long id,
                                        @RequestParam("numberOfLessons") int numLessons) {
        professorService.changeNumberOfLessons(numLessons, id);
    }


}
