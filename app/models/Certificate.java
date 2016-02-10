package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User on 2/9/2016.
 */
@Entity
public class Certificate extends Model {
    public static Finder<String, Certificate> finder = new Finder<>(Certificate.class);

    @Id
    public Integer id;

    public String mark;
    public String name;
    public String duration;
    public String category;

    @ManyToMany
    public List<Person> persons;

    public Certificate(){

    }
    public Certificate(String mark, String name, String duration,String category, List<Person> persons) {
        this.mark = mark;
        this.name = name;
        this.duration = duration;
        this.category = category;
        this.persons = persons;
    }

    public static void createCertificate(String mark, String name, String duration, String category){

        Certificate certificate = new Certificate();
        certificate.mark = mark;
        certificate.name = name;
        certificate.duration = duration;
        certificate.category = category;

        certificate.save();
    }

    /* ------------------- get all certificates ------------------ */

    public static List<Certificate> getAllCertificates(){
        Model.Finder<String, Certificate> finder = new Model.Finder<>(Certificate.class);
        List<Certificate> certificates = finder.all();
        return certificates;
    }
}
