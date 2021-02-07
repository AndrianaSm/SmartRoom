package com.example.smartroom.model;

import java.util.ArrayList;

public class Person {

    final String DRAWABLE ="drawable/";
    private String name;
    private int age;
    private ArrayList<BloodSugar> sugar_measurements;
    private String imageUri;
    private String id;
    private ArrayList<BloodPressure> pressures_measurements;
    public Person(String id, String name, int age, String imageUri, ArrayList<BloodSugar> sugar_measurements,ArrayList<BloodPressure> blood_measurements) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.imageUri = imageUri;
        this.sugar_measurements = sugar_measurements;
        this.pressures_measurements = pressures_measurements;
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

    public ArrayList<BloodSugar> getSugar_measurements() {
        return sugar_measurements;
    }

    public ArrayList<BloodPressure> getPressures_measurements() {
        return pressures_measurements;
    }
}
