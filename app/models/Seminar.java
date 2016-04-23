package models;

import com.avaje.ebean.Model;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by ajla on 11-Apr-16.
 */
public class Seminar extends Model {

    public static Finder<String, Seminar> finder = new Finder<>(Seminar.class);

    @Id
    public Integer id;
    public Date date;
    public String time;
    public String duration;

    @ManyToMany
    public Person person;

    public Seminar() {
    }

    public Seminar(Date date, String time, String duration) {
        this.date = date;
        this.time = time;
        this.duration = duration;
    }

                /* ------------------- create seminar ------------------ */


    public static void createSeminar(Date date, String time, String duration) {

        Seminar seminar = new Seminar();
        seminar.date = date;
        seminar.time = time;
        seminar.duration = duration;

        seminar.save();
    }


              /* ------------------- update seminar ------------------ */

    public static void updateSeminar(Date date, String time, String duration, Integer seminarId) {

        Seminar seminar = findSeminarById(seminarId);
        seminar.date = date;
        seminar.time = time;
        seminar.duration = duration;

        seminar.update();

    }


                /* ------------------- finds seminar by id ------------------ */

    public static Seminar findSeminarById (Integer seminarId){
        Seminar seminar = finder.where().eq("id", seminarId).findUnique();
        return seminar;
    }

                 /* ------------------- get all seminars ------------------ */

    public static List<Seminar> getAllSeminars() {
        List<Seminar> seminars = finder.all();
        return seminars;
    }
}
