package com.example.smartroom.model;

public class BloodSugar {

    private String date;
    private String measurements;
    private String time;

    public BloodSugar(String date, String time, String measurements) {
        this.date = date;
        this.measurements = measurements;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getMeasurements() {
        return measurements;
    }

    public String getTime() {
        return time;
    }
}
