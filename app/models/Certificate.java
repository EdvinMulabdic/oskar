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

    @ManyToMany
    public List<Person> persons;

    public Certificate(){

    }
    public Certificate(String mark, String name, String duration, List<Person> persons) {
        this.mark = mark;
        this.name = name;
        this.duration = duration;
        this.persons = persons;
    }
            /* ------------------- create certificate ------------------ */


    public static void createCertificate(String mark, String name, String duration){

        Certificate certificate = new Certificate();
        certificate.mark = mark;
        certificate.name = name;
        certificate.duration = duration;

        certificate.save();
    }

            /* ------------------- update certificate ------------------ */

    public static void updateCertificate(String mark, String name, String duration, Integer certificateId){

        Certificate certificate = findCertificateById(certificateId);
        certificate.mark = mark;
        certificate.name = name;
        certificate.duration = duration;

        certificate.update();

    }

             /* ------------------- delete certificate ------------------ */

    public static void deleteCertificate(Integer certificateId){
        Certificate certificate = findCertificateById(certificateId);
        certificate.delete();
    }


             /* ------------------- get all certificates ------------------ */

    public static List<Certificate> getAllCertificates(){
        Model.Finder<String, Certificate> finder = new Model.Finder<>(Certificate.class);
        List<Certificate> certificates = finder.all();
        return certificates;
    }

             /* ------------------- get number of certificates in database ------------------ */

    public static Integer getAllCertificatesSize(){
        Integer numberOfCertificates= finder.all().size();
        return numberOfCertificates;
    }

            /* ------------------- finds certificate by id ------------------ */

    public static Certificate findCertificateById (Integer certificateId){
        Certificate certificate = finder.where().eq("id", certificateId).findUnique();
        return certificate;
    }

}
