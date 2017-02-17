package com.practica.domain;

/**
 * Created by student on 2/7/2017.
 */
public class Professor extends Person {
    private Long id;
    private Double salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
