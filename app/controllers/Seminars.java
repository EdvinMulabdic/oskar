package controllers;

import models.Person;
import models.Seminar;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by User on 4/24/2016.
 */
public class Seminars extends Controller {

    public Result seminarMain() {
        return ok(views.html.seminars.seminarMain.render());
    }

    public Result createSeminarRender() {
        return ok(views.html.seminars.createSeminar.render());
    }

    public Result createSeminar() {
        DynamicForm form = Form.form().bindFromRequest();

        String name = form.field("name").value();
        String date = form.field("seminarDate").value();
        String personsId = form.field("personsId").value();
        String[] pId = personsId.split(",");

        Seminar.createSeminar(name, date, pId);
        return redirect(routes.Seminars.listOfSeminars());
    }


    public Result deleteSeminar(Integer seminarId) {
        Seminar.deleteSeminar(seminarId);
        return redirect(routes.Seminars.listOfSeminars());
    }

    public Result listOfSeminars() {
        List<Seminar> seminars = Seminar.getAllSeminars();
        return ok(views.html.seminars.listOdSeminars.render(seminars));
    }

    public Result personsSeminarList(Integer seminarId) {
        List<Person> persons = Seminar.personsSeminarList(seminarId);
        return ok(views.html.seminars.personsSeminarList.render(persons, seminarId));
    }

    public Result updateSeminarRender(Integer seminarId) {
        Seminar seminar = Seminar.findSeminarById(seminarId);
        return ok(views.html.seminars.updateSeminar.render(seminar));
    }


    public Result updateSeminar(Integer seminarId) {
        DynamicForm form = Form.form().bindFromRequest();
        String name = form.field("name").value();
        String date = form.field("seminarDate").value();
        String personsId = form.field("personsId").value();
        String[] pId = personsId.split(",");

        Seminar.updateSeminar(name, date, pId,seminarId);
        return redirect(routes.Seminars.listOfSeminars());
    }
}
