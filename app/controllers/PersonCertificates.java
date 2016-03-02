package controllers;

import models.CertificatePerson;
import models.Person;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;


/**
 * Created by ajla on 15-Feb-16.
 */
public class PersonCertificates extends Controller {

    /* ------------------- add ce certificate ------------------ */
    public Result addPersonsCertificateRender(Integer personId) {
        Person person = Person.findPersonById(personId);
        return ok(views.html.Certificates.certificatePerson.render(person));
    }

    public Result addPersonsCertificate(Integer pId) {

        DynamicForm form = Form.form().bindFromRequest();
        Integer personId = Integer.parseInt(form.field("personId").value());
        Integer certificateId = Integer.parseInt(form.field("certificateId").value());
        String certificateDate = form.field("certificateDate").value();

        CertificatePerson.createPersonCertificate(personId, certificateId, certificateDate);

        return redirect(routes.Persons.listOfPersons());
    }

    public Result deleteCertificate(String personCertificateId){
        String[] pcId = personCertificateId.split("-");
        Integer personId = Integer.parseInt(pcId[0]);
        Integer certificateId = Integer.parseInt(pcId[1]);
        CertificatePerson.deletePersonsCertificate(personId, certificateId);
        return redirect(routes.Persons.listOfPersons());
    }
}
