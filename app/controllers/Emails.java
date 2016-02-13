package controllers;

import models.Email;
import models.Person;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.email;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/12/2016.
 */
public class Emails extends Controller {

    public Result sendEmailRender(Integer personId){
        Person person = Person.findPersonById(personId);
        return ok(email.render(person));
    }

    public Result sendEmail(Integer personId){
        DynamicForm form = Form.form().bindFromRequest();

        String mailTo = form.field("mailTo").value();
        String subject = form.field("subject").value();
        String mail = form.field("mail").value();

        List<File> path = new ArrayList<>();

        Http.MultipartFormData body1 = request().body().asMultipartFormData();

        List<Http.MultipartFormData.FilePart> fileParts = body1.getFiles();

        if(fileParts != null){
            for (Http.MultipartFormData.FilePart filePart : fileParts){
                File file = filePart.getFile();
                path.add(file);
            }
        }
        Email.sendMultipleformMail(mailTo, subject, mail, path);

        return redirect(routes.Persons.listOfPersons());
    }
}
