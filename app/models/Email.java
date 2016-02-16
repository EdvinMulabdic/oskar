package models;

import com.avaje.ebean.Model;
import helpers.ConfigProvider;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import play.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by User on 2/12/2016.
 */
public class Email extends Model {

             /* ------------------- sending mail to person ------------------ */

    public static void sendEmail(String emailTo, String subject, String mail){

        SimpleEmail email = new SimpleEmail();
        email.setHostName(ConfigProvider.SMTP_HOST);
        email.setSmtpPort(Integer.parseInt(ConfigProvider.SMTP_PORT));
        try {
                /*Configuring mail*/
            email.setAuthentication(ConfigProvider.MAIL_FROM, ConfigProvider.MAIL_FROM_PASS);
            email.setFrom(ConfigProvider.MAIL_FROM);
            email.setStartTLSEnabled(true);
            email.addTo(emailTo);
            email.setSubject(subject);
            email.setMsg(mail);


            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }

    public static void sendMultipleformMail(String emailTo, String subject, String mail,List<String> filePath) {

        MultiPartEmail multiPartEmail = new MultiPartEmail();
        multiPartEmail.setHostName(ConfigProvider.SMTP_HOST);
        multiPartEmail.setSmtpPort(Integer.parseInt(ConfigProvider.SMTP_PORT));
        try {
                /*Configuring mail*/
            multiPartEmail.setAuthentication(ConfigProvider.MAIL_FROM, ConfigProvider.MAIL_FROM_PASS);
            multiPartEmail.setFrom(ConfigProvider.MAIL_FROM);
            multiPartEmail.setStartTLSEnabled(true);
            multiPartEmail.addTo(emailTo);
            multiPartEmail.setSubject(subject);
            multiPartEmail.setMsg(mail);



            EmailAttachment attachment = new EmailAttachment();

            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setName("Dokument.pdf");
            for(int i = 0; i < filePath.size(); i++){
                attachment.setPath(filePath.get(i));
                multiPartEmail.attach(attachment);

            }

            multiPartEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public static void sendGroupEmail(List<String> mailTo, String subject, String mail, List<String> filePath) {


        MultiPartEmail multiPartEmail = new MultiPartEmail();
        multiPartEmail.setHostName(ConfigProvider.SMTP_HOST);
        multiPartEmail.setSmtpPort(Integer.parseInt(ConfigProvider.SMTP_PORT));

        try {
                /*Configuring mail*/
            multiPartEmail.setAuthentication(ConfigProvider.MAIL_FROM, ConfigProvider.MAIL_FROM_PASS);
            multiPartEmail.setFrom(ConfigProvider.MAIL_FROM);
            multiPartEmail.setStartTLSEnabled(true);
            for(int i=0;  i < mailTo.size(); i++){
                multiPartEmail.addBcc(mailTo.get(i));
            }

            multiPartEmail.setSubject(subject);
            multiPartEmail.setMsg(mail);

            EmailAttachment attachment = new EmailAttachment();

            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setName("Dokument.pdf");
            for(int i = 0; i < filePath.size(); i++){
                attachment.setPath(filePath.get(i));
                multiPartEmail.attach(attachment);

            }

            multiPartEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }

       public static void sendBlankEmail(){

        SimpleEmail email = new SimpleEmail();
        email.setHostName(ConfigProvider.SMTP_HOST);
        email.setSmtpPort(Integer.parseInt(ConfigProvider.SMTP_PORT));
        try {
                /*Configuring mail*/
            email.setAuthentication(ConfigProvider.MAIL_FROM, ConfigProvider.MAIL_FROM_PASS);
            email.setFrom(ConfigProvider.MAIL_FROM);
            email.setStartTLSEnabled(true);
            email.addTo("edvin.mulabdic@gmail.com");
            email.setSubject("subject");
            email.setMsg("Salje li ovaj fenomenalni kod mail??????");


            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }

    public static void checkForExpiringCertificate(){
        Logger.debug("USAOOO");

        List<Person> persons = Person.getAllPersons();
        Map<Integer, List<CertificatePerson>> personsCertificates = new HashMap<>();
        List<CertificatePerson> getAllCertificatePerson = CertificatePerson.getAllCertificatePerson();


        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00.000000");
//        String format = formatter.format(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        date = cal.getTime();


        for(int i = 0; i < persons.size(); i ++){
            for (int j = 0; j < getAllCertificatePerson.size(); j++){
                if(persons.get(i).id.equals(getAllCertificatePerson.get(j).personId)){
                    personsCertificates.put(persons.get(i).id, CertificatePerson.allPersonsCertificates(persons.get(i).id));
                }
            }
        }

        for (Integer key : personsCertificates.keySet()) {
            List<CertificatePerson> value = personsCertificates.get(key);
            for(int k = 0; k < value.size(); k++){
                if(formatter.format(value.get(k).expirationDate.getTime()).equals(formatter.format(date))){
                    Email.sendBlankEmail();
                }
                Logger.debug("VALUE K + FORMAT  " + formatter.format(value.get(k).expirationDate.getTime()).equals(formatter.format(date)));
            }
            Logger.debug("Value    " + value);

        }
    }


}
