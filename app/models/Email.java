package models;

import com.avaje.ebean.Model;
import helpers.ConfigProvider;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import play.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.IntegerComparisonTerm;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by User on 2/12/2016.
 */
public class Email extends Model {

             /* ------------------- sending mail to person ------------------ */

    public static void sendEmail(String emailTo, String subject, String mail){

//        SimpleEmail email = new SimpleEmail();
//        email.setHostName(ConfigProvider.SMTP_HOST);
//        email.setSmtpPort(Integer.parseInt(ConfigProvider.SMTP_PORT));
//        try {
//                /*Configuring mail*/
//            email.setAuthentication(ConfigProvider.MAIL_FROM, ConfigProvider.MAIL_FROM_PASS);
//            email.setFrom("edvin.mulabdic@outlook.com", "Edvin");
//            email.setStartTLSEnabled(true);
//            email.addTo(emailTo);
//            email.setSubject(subject);
//            email.setMsg(mail);
//
//
//            email.send();
//        } catch (EmailException e) {
//            e.printStackTrace();
//        }

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("edvin.bh@gmail.com","ed01061989");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("edvin.mulabdic@outlook.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo));
            message.setSubject(subject);
            message.setText(mail);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void sendMultipleformMail(List<String> emailTo, String subject, String mail,List<String> filePath) {

        MultiPartEmail multiPartEmail = new MultiPartEmail();
        multiPartEmail.setHostName(ConfigProvider.SMTP_HOST);
        multiPartEmail.setSmtpPort(Integer.parseInt(ConfigProvider.SMTP_PORT));
        try {
                /*Configuring mail*/
            multiPartEmail.setAuthentication(ConfigProvider.MAIL_FROM, ConfigProvider.MAIL_FROM_PASS);
            multiPartEmail.setFrom("edvin.mulabdic@outlook.com");
            multiPartEmail.setStartTLSEnabled(true);

            for(int i = 0; i < emailTo.size(); i++){
                multiPartEmail.addBcc(emailTo.get(i));
            }
            multiPartEmail.setSubject(subject);
            multiPartEmail.setMsg(mail);
            multiPartEmail.addReplyTo("edvin.mulabdic@outlook.com");



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

    public static void sendEmailOneYear(List<Integer> personsId, Integer certificateId){
        Certificate certificate = Certificate.findCertificateById(certificateId);
        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < personsId.size(); i++) {
            persons.add(Person.findPersonById(personsId.get(i)));
            sentMail.add(Person.findPersonById(personsId.get(i)));
        }

        MultiPartEmail multiPartEmail = new MultiPartEmail();
        multiPartEmail.setHostName(ConfigProvider.SMTP_HOST);
        multiPartEmail.setSmtpPort(Integer.parseInt(ConfigProvider.SMTP_PORT));

        try {
                /*Configuring mail*/
            multiPartEmail.setAuthentication(ConfigProvider.MAIL_FROM, ConfigProvider.MAIL_FROM_PASS);
            multiPartEmail.setFrom(ConfigProvider.MAIL_FROM);
            multiPartEmail.setStartTLSEnabled(true);

            for (int j = 0; j < persons.size(); j++){
                multiPartEmail.addBcc(persons.get(j).mail);
            }
            multiPartEmail.setSubject("subject");
            multiPartEmail.setMsg("Postovani, Vas certifikat " + certificate.name + " istice za 1 godinu");

            EmailAttachment attachment = new EmailAttachment();

            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setPath("E:/oskar/public/oskarFiles/Zahtjev za izdavanje certifikata.pdf");
            attachment.setName("Zahtjev za izdavanje certifikata.pdf");
            multiPartEmail.attach(attachment);



            multiPartEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public static void sendEmailSixMonths(List<Integer> personsId, Integer certificateId){
        Certificate certificate = Certificate.findCertificateById(certificateId);
        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < personsId.size(); i++) {
            persons.add(Person.findPersonById(personsId.get(i)));
            sentMail.add(Person.findPersonById(personsId.get(i)));
        }

        MultiPartEmail multiPartEmail = new MultiPartEmail();
        multiPartEmail.setHostName(ConfigProvider.SMTP_HOST);
        multiPartEmail.setSmtpPort(Integer.parseInt(ConfigProvider.SMTP_PORT));

        try {
                /*Configuring mail*/
            multiPartEmail.setAuthentication(ConfigProvider.MAIL_FROM, ConfigProvider.MAIL_FROM_PASS);
            multiPartEmail.setFrom(ConfigProvider.MAIL_FROM);
            multiPartEmail.setStartTLSEnabled(true);

            for (int j = 0; j < persons.size(); j++){
                multiPartEmail.addBcc(persons.get(j).mail);
            }
            multiPartEmail.setSubject("subject");
            multiPartEmail.setMsg("Postovani, Vas certifikat " + certificate.name + " istice za 6 mjeseci");

            EmailAttachment attachment = new EmailAttachment();

            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setPath("../public/oskarFiles/Zahtjev za izdavanje certifikata.pdf");
            attachment.setName("Zahtjev za izdavanje certifikata.pdf");
            multiPartEmail.attach(attachment);



            multiPartEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }

    public static void checkForExpiringCertificate(){

        List<Person> persons = Person.getAllPersons();
        Map<Integer, List<CertificatePerson>> personsCertificates = new HashMap<>();
        List<CertificatePerson> getAllCertificatePerson = CertificatePerson.getAllCertificatePerson();
        List<Integer> personsId = new ArrayList<>();



        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00.000000");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        date = cal.getTime();

        Long oneYear= 31556952000l - 86400000;
        Long sixMonths = 15778476000l + 86400000;

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

                if(formatter.format(value.get(k).expirationDate.getTime() - oneYear).equals(formatter.format(date)))  {
                    personsId.add(value.get(k).personId);
                    Email.sendEmailOneYear(personsId, value.get(k).certificateId);
                }else if (formatter.format(value.get(k).expirationDate.getTime() - sixMonths ).equals(formatter.format(date))){
                            personsId.add(value.get(k).personId);
                    Email.sendEmailSixMonths(personsId, value.get(k).certificateId);
                }

            }
        }
    }

    public static List<Person> sentMail = new ArrayList<>();

    public static Integer numberOfSentMails(){
        return  sentMail.size();
    }


}
