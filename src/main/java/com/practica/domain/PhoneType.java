package com.practica.domain;

/**
 * Created by student on 2/7/2017.
 */
public enum PhoneType {
    Home("Home"),
    Mobile("Mobile"),
    Other("Other");

    private final String code;

    PhoneType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
