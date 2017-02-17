package com.practica.domain;

/**
 * Created by student on 2/7/2017.
 */
public class Discipline {
    private Long id;
    private String title;
    private Double scholarshipThreshold;
    private Professor professor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getScholarshipThreshold() {
        return scholarshipThreshold;
    }

    public void setScholarshipThreshold(Double scholarshipThreshold) {
        this.scholarshipThreshold = scholarshipThreshold;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
