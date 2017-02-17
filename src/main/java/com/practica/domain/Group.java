package com.practica.domain;

import java.util.Collection;

/**
 * Created by student on 2/7/2017.
 */
public class Group {
    private Long id;
    private String name;
    private Collection<Student> students; // ????

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }
}
