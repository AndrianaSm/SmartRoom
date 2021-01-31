package com.example.smartroom.model;

import java.util.ArrayList;

public class Person {

    final String DRAWABLE ="drawable/";
    private String name;
    private int age;
    private ArrayList<BloodSugar> measurements;
    private String imageUri;
    private String id;

    public Person(String id, String name, int age, String imageUri, ArrayList<BloodSugar> measurements) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.imageUri = imageUri;
        this.measurements = measurements;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public String getImageUri() {
        return DRAWABLE+imageUri;
    }

    public ArrayList<BloodSugar> getMeasurements() {
        return measurements;
    }
}
