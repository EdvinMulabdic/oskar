package models;

import com.avaje.ebean.Model;
import helpers.ManagerHelper;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
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
    public Boolean isActive;

    public Certificate() {

    }
    public Certificate(String mark, String name, String duration, List<Person> persons) {
        this.mark = mark;
        this.name = name;
        this.duration = duration;
        isActive = true;
    }
            /* ------------------- create certificate ------------------ */


    public static void createCertificate(String mark, String name, String duration) {

        Certificate certificate = new Certificate();
        certificate.mark = mark;
        certificate.name = name;
        certificate.duration = duration;
        certificate.isActive = true;

        certificate.save();
    }

            /* ------------------- update certificate ------------------ */

    public static void updateCertificate(String mark, String name, String duration, Integer certificateId) {

        Certificate certificate = findCertificateById(certificateId);
        certificate.mark = mark;
        certificate.name = name;
        certificate.duration = duration;

        certificate.update();

    }

             /* ------------------- archive certificate ------------------ */

    public static void archiveCertificate(Integer certificateId) {
        Certificate certificate = findCertificateById(certificateId);
        certificate.isActive = false;
        certificate.save();
    }

                 /* ------------------- activate certificate ------------------ */

    public static void activateCertificate(Integer certificateId) {
        Certificate certificate = findCertificateById(certificateId);
        certificate.isActive = true;
        certificate.save();
    }


             /* ------------------- get active certificates ------------------ */

    public static List<Certificate> getActiveCertificates() {
        Model.Finder<String, Certificate> finder = new Model.Finder<>(Certificate.class);
        List<Certificate> certificates = finder.where().eq("is_active", true).findList();
        return certificates;
    }

                 /* ------------------- get archived certificates ------------------ */

    public static List<Certificate> getArchivedCertificates() {
        Model.Finder<String, Certificate> finder = new Model.Finder<>(Certificate.class);
        List<Certificate> certificates = finder.where().eq("is_active", false).findList();
        return certificates;
    }

             /* ------------------- get number of certificates in database ------------------ */

    public static Integer getAllCertificatesSize() {
       return finder.findRowCount();
    }

            /* ------------------- finds certificate by id ------------------ */

    public static Certificate findCertificateById (Integer certificateId){
        Certificate certificate = finder.where().eq("id", certificateId).findUnique();
        return certificate;
    }



    public static List<Person> mkvalitete() {
        List<CertificatePerson> certificatePerson = CertificatePerson.getPersonsByCertificateId(ManagerHelper.MKVALITETE);
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < certificatePerson.size(); i++) {
            Person person = Person.findPersonById(certificatePerson.get(i).personId);
            persons.add(person);
        }
        return persons;
    }

    public static List<Person> mOkolisa() {
        List<CertificatePerson> certificatePerson = CertificatePerson.getPersonsByCertificateId(ManagerHelper.MOKOLISA);
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < certificatePerson.size(); i++) {
            Person person = Person.findPersonById(certificatePerson.get(i).personId);
            persons.add(person);
        }
        return persons;
    }

    public static List<Person> mRizika() {
        List<CertificatePerson> certificatePerson = CertificatePerson.getPersonsByCertificateId(ManagerHelper.MRIZIKA);
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < certificatePerson.size(); i++) {
            Person person = Person.findPersonById(certificatePerson.get(i).personId);
            persons.add(person);
        }
        return persons;
    }

    public static List<Person> mSH() {
        List<CertificatePerson> certificatePerson = CertificatePerson.getPersonsByCertificateId(ManagerHelper.MSH);
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < certificatePerson.size(); i++) {
            Person person = Person.findPersonById(certificatePerson.get(i).personId);
            persons.add(person);
        }
        return persons;
    }

    public static List<Person> mZZS() {
        List<CertificatePerson> certificatePerson = CertificatePerson.getPersonsByCertificateId(ManagerHelper.MZZS);
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < certificatePerson.size(); i++) {
            Person person = Person.findPersonById(certificatePerson.get(i).personId);
            persons.add(person);
        }
        return persons;
    }

    public static List<Person> auditor() {
        List<CertificatePerson> certificatePerson = CertificatePerson.getPersonsByCertificateId(ManagerHelper.AUDITOR);
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < certificatePerson.size(); i++) {
            Person person = Person.findPersonById(certificatePerson.get(i).personId);
            persons.add(person);
        }
        return persons;
    }

    public static List<Person> interniAuditor() {
        List<CertificatePerson> certificatePerson = CertificatePerson.getPersonsByCertificateId(ManagerHelper.IAUDITOR);
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < certificatePerson.size(); i++) {
            Person person = Person.findPersonById(certificatePerson.get(i).personId);
            persons.add(person);
        }
        return persons;
    }

    public static List<Person> procesniMenadzer() {
        List<CertificatePerson> certificatePerson = CertificatePerson.getPersonsByCertificateId(ManagerHelper.PROCESNIMENADZER);
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < certificatePerson.size(); i++) {
            Person person = Person.findPersonById(certificatePerson.get(i).personId);
            persons.add(person);
        }
        return persons;
    }
}
