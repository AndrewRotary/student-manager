package com.practica.domain;

import java.util.Collection;

/**
 * Created by student on 2/7/2017.
 */
public class Student extends Person {

    private Long id;
    private Collection<Mark> marks;
    private Collection<Discipline> disciplines;
    private Group group;
    private Double calculateScholarship;
    private boolean status;
    private String imageAddress;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Collection<Mark> marks) {
        this.marks = marks;
    }

    public Collection<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(Collection<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Double getCalculateScholarship() {
        return calculateScholarship;
    }

    public void setCalculateScholarship(Double calculateScholarship) {
        this.calculateScholarship = calculateScholarship;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }
}
