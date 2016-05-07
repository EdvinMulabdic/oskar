package models;

import com.avaje.ebean.Model;
import play.Logger;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 4/24/2016.
 */
@Entity
public class Seminar extends Model {

    public static Finder<String, Seminar> finder = new Finder<>(Seminar.class);

    @Id
    public Integer id;
    public String name;
    public Date date;
    public Boolean isVisible;
    public Boolean isPersonVisible;

    @ManyToMany
    public List<Person> persons;

    public Seminar() {
    }

    public Seminar(String name, Date date) {
        this.name = name;
        this.date = date;
    }

                /* ------------------- create seminar ------------------ */


    public static void createSeminar(String name, String seminarDate, String[] personsId) {
        List<Person> persons = new ArrayList<>();
        Seminar s = new Seminar();
        s.name = name;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = df.parse(seminarDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        s.date = date;
        for(int i = 0; i < personsId.length; i++) {
            s.persons.add(Person.findPersonById(Integer.parseInt(personsId[i])));
        }
        s.isVisible = true;
        s.isPersonVisible = true;
        s.save();


    }




    /* ------------------- update seminar ------------------ */

    public static void updateSeminar(String name,String seminarDate, String[] personsId, Integer seminarId) {

        Seminar seminar = findSeminarById(seminarId);
        seminar.name = name;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = df.parse(seminarDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        seminar.date = date;
        seminar.persons.clear();
        for(int i = 0; i < personsId.length; i++) {
            seminar.persons.add(Person.findPersonById(Integer.parseInt(personsId[i])));

        }
        for(int j = 0; j < seminar.persons.size(); j++) {
            seminar.persons.get(j).isVisibleInSeminar = true;
            seminar.persons.get(j).update();
        }
        seminar.update();

    }


    /* ------------------- finds seminar by id ------------------ */

    public static Seminar findSeminarById (Integer seminarId){
        return finder.where().eq("id", seminarId).findUnique();
    }

    /* ------------------- get all seminars ------------------ */

    public static List<Seminar> getAllSeminars() {
        return finder.where().eq("is_visible", 1).findList();
    }


    /* ------------------- delete seminar ------------------ */

    public static void deleteSeminar(Integer seminarId) {
        Seminar seminar = findSeminarById(seminarId);
        seminar.isVisible = false;
        seminar.update();
    }

    public static List<Person> personsSeminarList(Integer seminarId) {
        List<Person> persons = new ArrayList<>();
        Seminar seminar = finder.where().eq("id", seminarId).findUnique();

        for(int i =0; i < seminar.persons.size(); i ++) {
            if(seminar.persons.get(i).isVisibleInSeminar = true){
                persons.add(seminar.persons.get(i));
            }

        }
        return persons;
    }


    public static void deletePersonFromSeminarList(Integer seminarId, Integer personId) {
        Seminar seminar = finder.where().eq("id", seminarId).findUnique();
        for(int i =0; i < seminar.persons.size(); i ++) {
            if(seminar.persons.get(i).id == personId){
                seminar.persons.get(i).isVisibleInSeminar = false;
                seminar.persons.get(i).update();
            }
        }

    }



    public static Integer getNumberOfSeminars(){
        return finder.findRowCount();
    }
}