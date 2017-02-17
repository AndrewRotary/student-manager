package com.practica.domain;

import java.util.Collection;
import java.util.Date;

/**
 * Created by student on 2/7/2017.
 */
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private Address address;
    private LibrarySubscription librarySubscription;
    private Student student;
    private Professor professor;
    private Collection<Phone> phones;
    private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LibrarySubscription getLibrarySubscription() {
        return librarySubscription;
    }

    public void setLibrarySubscription(LibrarySubscription librarySubscription) {
        this.librarySubscription = librarySubscription;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Collection<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Collection<Phone> phones) {
        this.phones = phones;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
