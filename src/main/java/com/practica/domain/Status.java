package com.practica.domain;

/**
 * Created by student on 2/9/2017.
 */
public enum Status {
    NONE("NONE"),
    ACTIVE("ACTIVE"),
    SUSPENDED("SUSPENDED");

    private final String code;

    Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

