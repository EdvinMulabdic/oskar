package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by ajla on 15-Feb-16.
 */
@Entity
public class CertificatePerson extends Model {

    public static Finder<String, CertificatePerson> finder = new Finder<>(CertificatePerson.class);

    @Id
    public Integer id;
    public Integer certificateId;
    public Integer personId;
    public Date certificateDate;

    public Date expirationDate;

    public CertificatePerson() {
    }

    public CertificatePerson(Integer id, Integer certificateId, Integer personId, Date certificateDate, Date expirationDate) {
        this.id = id;
        this.certificateId = certificateId;
        this.personId = personId;
        this.certificateDate = certificateDate;
        this.expirationDate = expirationDate;
    }

    public static void createPersonCertificate(Integer personId, Integer certificateId, String certificateDate) {

        CertificatePerson certificatePerson = new CertificatePerson();
        certificatePerson.personId = personId;
        certificatePerson.certificateId = certificateId;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = df.parse(certificateDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        certificatePerson.certificateDate = date;

        Date expirationDate = date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(expirationDate);
        cal.add(Calendar.YEAR, 3);
        expirationDate = cal.getTime();
        df.format(expirationDate);
        certificatePerson.expirationDate = expirationDate;

        certificatePerson.save();
    }

    public static List<Certificate> getAllPersonsCertificates(Integer personId) {
        List<CertificatePerson> certificatePersons = finder.where().eq("personId", personId).findList();
        List<Certificate> certificates = new ArrayList<>();

        for (CertificatePerson cp : certificatePersons) {
            Certificate cert = Certificate.findCertificateById(cp.certificateId);
            certificates.add(cert);
        }
        return certificates;
    }

    public static List<CertificatePerson> getAllCertificatePerson(){
        return finder.all();
    }

    public static List<CertificatePerson> getPersonsByCertificateId(Integer certificateId){

        return finder.where().eq("certificate_id", certificateId).findList();
    }

    public static List<CertificatePerson> allPersonsCertificates(Integer personId) {
        List<CertificatePerson> certificatePersons = finder.where().eq("personId", personId).findList();

        return certificatePersons;
    }
}
