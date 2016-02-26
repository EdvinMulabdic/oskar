package controllers;

import models.Email;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by User on 2/26/2016.
 */
public class Notifications extends Controller {


                /* ------------------- list of mailed persons ------------------ */

    public Result mailedPersons(){

        return ok(views.html.Persons.notificationMails.render(Email.sentMail));
    }

    public Result not(){
        Integer numberOfNotifications = Email.numberOfSentMails();
        Logger.info("num " + numberOfNotifications);
        return ok(String.valueOf(numberOfNotifications));
    }
}
