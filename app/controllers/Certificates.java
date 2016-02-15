package controllers;

import helpers.Authenticators;
import models.Certificate;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

/**
 * Created by User on 2/10/2016.
 */
public class Certificates extends Controller {

            /* ------------------- render main certificate page ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result certificateMain(){
        return ok(views.html.Certificates.certificateMain.render());
    }

            /* ------------------- render create certificate view ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result createCertificateRender(){
        return ok(views.html.Certificates.createCertificate.render());
    }

            /* ------------------- create certificate ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result createCertificate() {
        DynamicForm form = Form.form().bindFromRequest();
        String mark = form.field("mark").value();
        String name = form.field("name").value();
        String duration = form.field("duration").value();

        Certificate.createCertificate(mark, name, duration);

        return redirect(routes.Certificates.certificateMain());
    }

            /* ------------------- list of all certificates ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result listOfCertificates(){
        List<Certificate> certificates = Certificate.getAllCertificates();
        return ok(views.html.Certificates.listOfCertificates.render(certificates));
    }

            /* ------------------- render update certificate view ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result updateCertificateRender(Integer certificateId) {
        Certificate certificate = Certificate.findCertificateById(certificateId);
        return ok(views.html.Certificates.updateCertificate.render(certificate));
    }

            /* ------------------- update certificate ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result updateCertificate(Integer certificateId) {
        DynamicForm form = Form.form().bindFromRequest();

        String mark = form.field("mark").value();
        String name = form.field("name").value();
        String duration = form.field("duration").value();

        Certificate.updateCertificate(mark, name, duration, certificateId);
        return redirect(routes.Certificates.listOfCertificates());
    }

             /* ------------------- delete certificate ------------------ */
             @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result deleteCertificate(Integer certificateId) {
        Certificate.deleteCertificate(certificateId);
        return redirect(routes.Certificates.listOfCertificates());
    }
}
