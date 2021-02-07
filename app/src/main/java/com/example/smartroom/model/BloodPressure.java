package com.example.smartroom.model;

public class BloodPressure {
    private String date,time,systoliki,diastoliki,pulse;

    public BloodPressure(String date, String time, String systoliki, String diastoliki, String pulse) {
        this.date = date;
        this.time = time;
        this.systoliki = systoliki;
        this.diastoliki = diastoliki;
        this.pulse = pulse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSystoliki() {
        return systoliki;
    }

    public void setSystoliki(String systoliki) {
        this.systoliki = systoliki;
    }

    public String getDiastoliki() {
        return diastoliki;
    }

    public void setDiastoliki(String diastoliki) {
        this.diastoliki = diastoliki;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }
}
