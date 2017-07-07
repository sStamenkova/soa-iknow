package edu.soa.iknow.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Popov on 07.7.2017.
 */
@Entity
public class ProfessorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fieldOfLessons;
    private int numberOfCurrentSubjects;
    private String status;
    private int yearsOfWork;
    private boolean isFullTime;
    private Long userId;

    public ProfessorInfo() {

    }

    public ProfessorInfo(String fieldOfLessons, int numberOfCurrentSubjects,
                         String status, int yearsOfWork,
                         boolean isFullTime, Long userId) {
        this.fieldOfLessons = fieldOfLessons;
        this.numberOfCurrentSubjects = numberOfCurrentSubjects;
        this.status = status;
        this.yearsOfWork = yearsOfWork;
        this.isFullTime = isFullTime;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFieldOfLessons() {
        return fieldOfLessons;
    }

    public void setFieldOfLessons(String fieldOfLessons) {
        this.fieldOfLessons = fieldOfLessons;
    }

    public int getNumberOfCurrentSubjects() {
        return numberOfCurrentSubjects;
    }

    public void setNumberOfCurrentSubjects(int numberOfCurrentSubjects) {
        this.numberOfCurrentSubjects = numberOfCurrentSubjects;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getYearsOfWork() {
        return yearsOfWork;
    }

    public void setYearsOfWork(int yearsOfWork) {
        this.yearsOfWork = yearsOfWork;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public void setFullTime(boolean fullTime) {
        isFullTime = fullTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
