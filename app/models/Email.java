package models;

import com.avaje.ebean.Model;
import helpers.ConfigProvider;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import play.Logger;

import java.util.List;

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

    public static void sendMultipleformMail(String emailTo, String subject, String mail,List<String> filePath){

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


        Logger.info("PATH   " + filePath);
    }

    public static void sendGroupEmail(List<String> mailTo, String subject, String mail, List<String> filePath ){


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


}
