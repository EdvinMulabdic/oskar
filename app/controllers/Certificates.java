package controllers;

import models.Certificate;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by User on 2/10/2016.
 */
public class Certificates extends Controller {

    public Result certificateMainRender(){
        return ok(views.html.Certificates.certificateMain.render());
    }
    public Result createCertificateRender(){
        return ok(views.html.Certificates.createCertificate.render());
    }
    public Result createCertificate(){
        DynamicForm form = Form.form().bindFromRequest();
        String mark = form.field("mark").value();
        String name = form.field("name").value();
        String duration = form.field("duration").value();
        String category = form.field("category").value();

        Certificate.createCertificate(mark, name, duration, category);

        return redirect(routes.Certificates.certificateMainRender());
    }

    public Result listOfCertificates(){
        List<Certificate> certificates = Certificate.getAllCertificates();
        return ok(views.html.Certificates.listOfCertificates.render(certificates));
    }

}
