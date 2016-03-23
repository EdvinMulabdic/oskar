package controllers;

import helpers.ConfigProvider;
import models.Person;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 3/2/2016.
 */
public class Files extends Controller {

                 /* -------------------  persons files render------------------ */

    public Result listOfPersonsFiles(Integer personId){
        Person person = Person.findPersonById(personId);

        List<File> results = new ArrayList<>();

        String folderName = person.name + person.id;
        String location = ConfigProvider.UPLOAD_FILES_FOLDER + folderName;

        File[] files = new File(location).listFiles();

        if (files != null) {
            for (File file : files) {
                    results.add(file);

            }
        }
        return ok(views.html.Persons.listOfPersonsFiles.render(person, results));
    }


                 /* ------------------- saving persons files render------------------ */

    public Result saveFileRender(Integer personId){
        Person person = Person.findPersonById(personId);
        return ok(views.html.Persons.personFile.render(person));
    }


                 /* ------------------- saving persons files ------------------ */

    public Result saveFile(Integer personId){
        DynamicForm  form = Form.form().bindFromRequest();

        Person person = Person.findPersonById(personId);

        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart filePart = body.getFile("files");

        String folder = person.name + person.id;

        if (filePart != null) {
            String fileName = filePart.getFilename();
            File file = filePart.getFile();

            File theDir = new File(ConfigProvider.UPLOAD_FILES_FOLDER + folder);


            // if the directory does not exist, create it

            boolean result = false;
            if (!theDir.exists()) {

                try {
                    theDir.mkdir();
                    result = true;
                }
                catch(SecurityException se){
                }
            } else {
                result = true;
            }

            file.renameTo(new File(theDir, fileName));
            return redirect(routes.Files.listOfPersonsFiles(personId));

        } else {
            flash("error", "Missing file");
            return redirect(routes.Files.listOfPersonsFiles(personId));
        }
    }

                     /* ------------------- delete file ------------------ */

    public Result deleteFile(String personIdFileName){
        Integer personId = Integer.parseInt(personIdFileName.split("-")[0]);
        String fileName = personIdFileName.split("-")[1];
        Person person = Person.findPersonById(personId);

        String folderName = person.name + person.id;
        String location = ConfigProvider.UPLOAD_FILES_FOLDER + folderName;

        File[] files = new File(location).listFiles();

        if (files != null) {
            for (File file : files) {
                if(file.getName().equals(fileName)){
                    file.delete();
                }
            }
        }
        return redirect(routes.Files.listOfPersonsFiles(personId));
    }
}
