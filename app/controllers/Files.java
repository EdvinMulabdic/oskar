package controllers;

import models.Person;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.io.File;
import java.util.List;

/**
 * Created by User on 3/2/2016.
 */
public class Files extends Controller {

    public Result saveFile(Integer personId){
        DynamicForm  form = Form.form().bindFromRequest();
//        String name = form.field("name").value();
        String name = "Ime";
        Person person = Person.findPersonById(personId);

        Http.MultipartFormData body1 = request().body().asMultipartFormData();
        List<Http.MultipartFormData.FilePart> fileParts = body1.getFiles();

        if(fileParts != null){
            for (Http.MultipartFormData.FilePart filePart : fileParts){
                File file = filePart.getFile();
                Logger.info("FILE  " + file);
                models.File.createFile(file, person, name);

            }
        }

       return ok();

    }
}
