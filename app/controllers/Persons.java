package controllers;

import models.Certificate;
import models.Company;
import models.Person;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by User on 2/9/2016.
 */
public class Persons extends Controller {

            /* ------------------- staff render view ------------------ */

    public Result staffRender(){
        return ok(views.html.Persons.staff.render());
    }

            /* ------------------- create person view render ------------------ */

    public Result createPersonRender(){
        return ok(views.html.Persons.createPerson.render());
    }

            /* ------------------- create person ------------------ */

    public Result createPerson(){
        DynamicForm form = Form.form().bindFromRequest();
        String name = form.field("name").value();
        String lastname = form.field("lastname").value();
        String company = form.field("company").value();
        String phone = form.field("phone").value();
        String email = form.field("email").value();

        Person.savePerson(name, lastname, company, phone, email);

        return redirect(routes.Persons.staffRender());
    }
            /* ------------------- update person view render ------------------ */

    public Result updatePersonRender(Integer personId){
        Person person = Person.findPersonById(personId);
        return ok(views.html.Persons.updatePerson.render(person));
    }

            /* ------------------- update person ------------------ */

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

    public Result deletePerson(Integer personId){
        Person.deletePerson(personId);
        return redirect(routes.Persons.listOfPersons());
    }

            /* ------------------- list of persons view ------------------ */

    public Result listOfPersons(){
        List<Person> persons = Person.getAllPersons();
        return ok(views.html.Persons.listOfPersons.render(persons));
    }



}
