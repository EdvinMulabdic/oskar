package controllers;

import helpers.Authenticators;
import models.Certificate;
import models.Person;
import play.Logger;
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

            /* ------------------- list of active all certificates ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result listOfActiveCertificates() {
        List<Certificate> certificates = Certificate.getActiveCertificates();
        return ok(views.html.Certificates.listOfCertificates.render(certificates));
    }

    /* ------------------- list of archived certificates ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result listOfArchivedCertificates() {
        List<Certificate> certificates = Certificate.getArchivedCertificates();
        return ok(views.html.Certificates.listOfArchivedCertificates.render(certificates));
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
        return redirect(routes.Certificates.listOfActiveCertificates());
    }

     /* ------------------- archive certificate ------------------ */
     @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result archiveCertificate(Integer certificateId) {
        Certificate.archiveCertificate(certificateId);
        return redirect(routes.Certificates.listOfActiveCertificates());
    }

    /* ------------------- activate certificate ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result activateCertificate(Integer certificateId) {
        Certificate.activateCertificate(certificateId);
        return redirect(routes.Certificates.listOfArchivedCertificates());
    }

    /* ------------------- menadzer kvalitete ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result mKvalitete() {
        List<Person> persons = Certificate.mkvalitete();
        return ok(views.html.Manager.menadzerKvalitete.render(persons));
    }

    /* ------------------- menadzer okolisa ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result mOkolisa() {
        List<Person> persons = Certificate.mOkolisa();
        return ok(views.html.Manager.menadzerOkolisa.render(persons));
    }

    /* ------------------- menadzer rizika ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result mRizika(){
        List<Person> persons = Certificate.mRizika();
        return ok(views.html.Manager.menadzerRizika.render(persons));
    }

    /* ------------------- menadzer sigurnosti hrane ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result mSH() {
        List<Person> persons = Certificate.mSH();
        return ok(views.html.Manager.menadzerSH.render(persons));
    }

    /* ------------------- menadzer zastite zdravlja i sigurosti ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result mZZS() {
        List<Person> persons = Certificate.mZZS();
        return ok(views.html.Manager.menadzerZZS.render(persons));
    }

    /* ------------------- auditor ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result auditor() {
        List<Person> persons = Certificate.auditor();
        return ok(views.html.Manager.auditor.render(persons));
    }

    /* ------------------- interni auditor ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result interniAuditor() {
        List<Person> persons = Certificate.interniAuditor();
        return ok(views.html.Manager.interniAuditor.render(persons));
    }

    /* ------------------- procesni menadzer ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result procesniMenadzer() {
        List<Person> persons = Certificate.procesniMenadzer();
        return ok(views.html.Manager.procesniMenadzer.render(persons));
    }
}
