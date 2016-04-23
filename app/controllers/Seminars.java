package controllers;

import helpers.Authenticators;
import models.Certificate;
import models.Person;
import models.Seminar;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ajla on 11-Apr-16.
 */
public class Seminars extends Controller {

            /* ------------------- render main certificate page ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result seminarMain(){
        return ok(views.html.Seminars.seminarMain.render());
    }

            /* ------------------- render create certificate view ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result createSeminarRender(){
        return ok(views.html.Seminars.createSeminar.render());
    }

            /* ------------------- create certificate ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result createSeminar() {
        DynamicForm form = Form.form().bindFromRequest();

        String seminarDate = form.field("date").value();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = df.parse(seminarDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String time = form.field("time").value();
        String duration = form.field("duration").value();

        Seminar.createSeminar(date, time, duration);

        return redirect(routes.Seminars.seminarMain());
    }

    /* ------------------- list of active all seminars ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result listOfAllSeminars() {
        List<Seminar> seminars = Seminar.getAllSeminars();
        return ok(views.html.Seminars.listOfSeminars.render(seminars));
    }

    /* ------------------- render update seminar view ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result updateSeminarRender(Integer seminarId) {
        Seminar seminar = Seminar.findSeminarById(seminarId);
        return ok(views.html.Seminars.updateSeminar.render(seminar));
    }

    /* ------------------- update seminar ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)

    public Result updateSeminar(Integer seminarId) {
        DynamicForm form = Form.form().bindFromRequest();

        String seminarDate = form.field("date").value();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = df.parse(seminarDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String time = form.field("time").value();
        String duration = form.field("duration").value();

        Seminar.updateSeminar(date, time, duration, seminarId);
        return redirect(routes.Seminars.listOfSeminars());
    }
}
