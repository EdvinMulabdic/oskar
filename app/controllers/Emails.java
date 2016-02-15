package controllers;

        import models.Company;
        import models.Email;
        import models.Person;
        import org.springframework.util.AutoPopulatingList;
        import play.Logger;
        import play.data.DynamicForm;
        import play.data.Form;
        import play.mvc.Controller;
        import play.mvc.Http;
        import play.mvc.Result;
        import views.html.email;
        import views.html.groupEmail;

        import java.io.File;
        import java.io.IOException;
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

        List<String> filePath = new ArrayList<>();

        Http.MultipartFormData body1 = request().body().asMultipartFormData();
        List<Http.MultipartFormData.FilePart> fileParts = body1.getFiles();

        if(fileParts != null){
            for (Http.MultipartFormData.FilePart filePart : fileParts){
                File file = filePart.getFile();
                filePath.add(file.getPath());
            }
        }

        Email.sendMultipleformMail(mailTo, subject, mail, filePath);

        return redirect(routes.Persons.listOfPersons());
    }

    public Result sendGroupEmailRender(){
        return ok(groupEmail.render());
    }

    public Result sendGroupEmail(){
        DynamicForm form = Form.form().bindFromRequest();
        String mailTo = form.field("mailTo").value();
        String[] mailToList =mailTo.split(" ");
        List<String> mails = new ArrayList<>();
        for(int i = 0; i < mailToList.length; i++){
            if(mailToList[i].endsWith(".com")){
                mails.add(mailToList[i]);
            }
        }

        List<String> filePath = new ArrayList<>();

        Http.MultipartFormData body1 = request().body().asMultipartFormData();
        List<Http.MultipartFormData.FilePart> fileParts = body1.getFiles();

        if(fileParts != null){
            for (Http.MultipartFormData.FilePart filePart : fileParts){
                File file = filePart.getFile();
                filePath.add(file.getPath());
            }
        }

        String subject = form.field("subject").value();
        String mail = form.field("mail").value();

        Email.sendGroupEmail(mails, subject, mail, filePath);


        return redirect(routes.Companies.companyMain());

    }
}
