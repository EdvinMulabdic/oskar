package models;

import com.avaje.ebean.Model;
import helpers.ConfigProvider;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import java.io.File;
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

    public static void sendMultipleformMail(String emailTo, String subject, String mail, List<File> files){

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

            for(int i = 0; i < files.size(); i++){
                multiPartEmail.attach(files.get(i));
            }

            multiPartEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }



    }

}
