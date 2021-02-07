package com.example.smartroom.services;


import com.example.smartroom.model.BloodPressure;
import com.example.smartroom.model.BloodSugar;
import com.example.smartroom.model.Person;

import java.util.ArrayList;

public class DataService {
    
    private static DataService ourInstance = new DataService();

    public DataService() {
    }

    public static DataService getOurInstance() {
        return ourInstance;
    }

    public ArrayList<Person> getPersons () {

        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("A","Ανδριάνα",22,"avatar_one", getBloodSugarMeasurements("A"),getBloodPressureMeasurements("A")));
        list.add(new Person("B","Σπύρος",55,"avatar_two", getBloodSugarMeasurements("B"),getBloodPressureMeasurements("B")));
        list.add(new Person("C","Αιμιλία",22,"avatar_three", getBloodSugarMeasurements("C"),getBloodPressureMeasurements("C")));
        list.add(new Person("","Προσθήκη καινούργιου χρήστη",22,"add_user", getBloodSugarMeasurements(""),getBloodPressureMeasurements("")));

        return list;
    }

    public ArrayList<BloodSugar> getBloodSugarMeasurements(String id) {
        ArrayList<BloodSugar> list = new ArrayList<>();
        if(id.equals("A")){
            list.add(new BloodSugar("11/01/2021","08:03", "101"));
            list.add(new BloodSugar("12/01/2021", "08:05", "101"));
            list.add(new BloodSugar("13/01/2021", "08:10", "122"));
            list.add(new BloodSugar("14/01/2021", "08:00", "123"));
            list.add(new BloodSugar("15/01/2021", "08:30", "99"));
            list.add(new BloodSugar("16/01/2021", "08:40", "140"));
            list.add(new BloodSugar("17/01/2021", "08:20", "110"));
            list.add(new BloodSugar("18/01/2021", "08:06", "150"));
        }else if(id.equals("B")) {
            list.add(new BloodSugar("11/01/2021", "09:03", "90"));
            list.add(new BloodSugar("12/01/2021", "09:05", "80"));
            list.add(new BloodSugar("13/01/2021", "09:10", "95"));
            list.add(new BloodSugar("14/01/2021", "09:00", "10"));
            list.add(new BloodSugar("15/01/2021", "09:30", "99"));
            list.add(new BloodSugar("16/01/2021", "09:40", "04"));
        } else{
            list.add(new BloodSugar("11/01/2021","07:03", "89"));
            list.add(new BloodSugar("12/01/2021", "08:32", "90"));
            list.add(new BloodSugar("13/01/2021", "07:43", "98"));
            list.add(new BloodSugar("14/01/2021", "07:43", "97"));
            list.add(new BloodSugar("15/01/2021", "07:34", "100"));
            list.add(new BloodSugar("16/01/2021", "07:40", "102"));
            list.add(new BloodSugar("17/01/2021", "07:30", "95"));
            list.add(new BloodSugar("18/01/2021", "08:06", "110"));
        }
        return list;
    }

    public ArrayList<BloodPressure> getBloodPressureMeasurements(String id) {
        ArrayList<BloodPressure> list = new ArrayList<>();
        if(id.equals("A")){
            list.add(new BloodPressure("11/01/2021","08:03", "105","80","60"));
            list.add(new BloodPressure("12/01/2021", "08:05", "101","60","80"));
            list.add(new BloodPressure("13/01/2021", "08:10", "122","101","79"));
            list.add(new BloodPressure("14/01/2021", "08:00", "123","101","72"));
            list.add(new BloodPressure("15/01/2021", "08:30", "99","101","68"));
            list.add(new BloodPressure("16/01/2021", "08:40", "140","101","68"));
            list.add(new BloodPressure("17/01/2021", "08:20", "110","101","73"));
            list.add(new BloodPressure("18/01/2021", "08:06", "150","101","59"));
        }else if(id.equals("B")) {
            list.add(new BloodPressure("11/01/2021","08:03", "100","80","70"));
            list.add(new BloodPressure("12/01/2021", "08:05", "99","82","65"));
            list.add(new BloodPressure("13/01/2021", "08:10", "120","90","75"));
            list.add(new BloodPressure("14/01/2021", "08:00", "121","92","73"));
            list.add(new BloodPressure("15/01/2021", "08:30", "131","79","62"));
            list.add(new BloodPressure("16/01/2021", "08:40", "120","75","64"));
            list.add(new BloodPressure("17/01/2021", "08:20", "123","76","69"));
            list.add(new BloodPressure("18/01/2021", "08:06", "102","81","73"));
        } else{
            list.add(new BloodPressure("11/01/2021","08:03", "105","80","69"));
            list.add(new BloodPressure("12/01/2021", "08:05", "101","60","62"));
            list.add(new BloodPressure("13/01/2021", "08:10", "122","101","72"));
            list.add(new BloodPressure("14/01/2021", "08:00", "123","101","79"));
            list.add(new BloodPressure("15/01/2021", "08:30", "99","101","77"));
            list.add(new BloodPressure("16/01/2021", "08:40", "140","101","68"));
            list.add(new BloodPressure("17/01/2021", "08:20", "110","101","75"));
            list.add(new BloodPressure("18/01/2021", "08:06", "150","101","80"));
        }
        return list;
    }

    public void addPerson(String name,int age ,String imgUri,ArrayList<BloodSugar> bloodSugars,ArrayList<BloodPressure> bloodPressures){

    }

}
