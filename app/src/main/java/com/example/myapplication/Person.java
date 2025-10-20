package com.example.myapplication;

public class Person {
    private String name;
    private String family;
    private String study;

    public Person(String name, String family, String study) {
        this.name = name;
        this.family = family;
        this.study = study;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getStudy() {
        return study;
    }
}
