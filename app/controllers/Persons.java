package controllers;

import helpers.Authenticators;
import models.Certificate;
import models.Company;
import models.Person;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/9/2016.
 */
public class Persons extends Controller {


            /* ------------------- personsMain render view ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result personsMain(){
        return ok(views.html.Persons.personsMain.render());
    }

            /* ------------------- create person view render ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result createPersonRender(){
        List<Certificate> certificates = Certificate.getAllCertificates();
        return ok(views.html.Persons.createPerson.render(certificates));
    }

            /* ------------------- create person ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result createPerson(){
        List<Certificate> certificatesList = Certificate.getAllCertificates();

        DynamicForm form = Form.form().bindFromRequest();

        String name = form.field("name").value();
        String lastname = form.field("lastname").value();
        String company = form.field("company").value();
        String phone = form.field("phone").value();
        String email = form.field("email").value();

        String certifikat = form.field("certificateId").value();

        List<String> certificates = new ArrayList<>();
        String[] strings = (certifikat.split(","));
        for(int i=0; i < strings.length; i++){
            certificates.add(strings[i]);
        }

        List<Certificate>certificatesId = new ArrayList<>();
        for (int i = 0; i < certificates.size(); i++) {
            for (int j = 0; j < certificatesList.size(); j++) {
                if (certificatesList.get(j).id.toString().equals(certificates.get(i))) {
                    certificatesId.add(certificatesList.get(j));
                }
            }
        }
        Person.savePerson(name, lastname, company, phone, email, certificatesId);

        return redirect(routes.Persons.personsMain());
    }
            /* ------------------- update person view render ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result updatePersonRender(Integer personId){
        Person person = Person.findPersonById(personId);
        return ok(views.html.Persons.updatePerson.render(person));
    }

            /* ------------------- update person ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result updatePerson(Integer personid){
        DynamicForm form = Form.form().bindFromRequest();
        String name = form.field("name").value();
        String lastname = form.field("lastname").value();
        String company = form.field("company").value();
        String phone = form.field("phone").value();
        String email = form.field("email").value();

        Person.updatePerson(name, lastname, company, phone, email, personid);

        return redirect(routes.Persons.listOfPersons());

    }

            /* ------------------- delete person ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result deletePerson(Integer personId){
        Person.deletePerson(personId);
        return redirect(routes.Persons.listOfPersons());
    }

            /* ------------------- list of persons view ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result listOfPersons(){
        List<Person> persons = Person.getAllPersons();
        return ok(views.html.Persons.listOfPersons.render(persons));
    }



}
