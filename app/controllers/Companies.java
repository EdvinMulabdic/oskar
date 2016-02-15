package controllers;

import helpers.Authenticators;
import models.Company;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/11/2016.
 */
public class Companies extends Controller {

    /* ------------------- render main companies page ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result companyMain() {
        return ok(views.html.Companies.companyMain.render());
    }


    /* ------------------- render create company ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result createCompanyRender() {
        return ok(views.html.Companies.createCompany.render());
    }

    /* ------------------- create company ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result createCompany() {
        DynamicForm form = Form.form().bindFromRequest();

        String name = form.field("name").value();
        String email = form.field("email").value();
        String phone = form.field("phone").value();
        String pdv = form.field("pdv").value();
        String category = form.field("category").value();

        Company.createCompany(name, email, phone, pdv, category);

        return redirect(routes.Companies.companyMain());
    }

    /* ------------------- list of companies ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result listOfCompanies() {
        List<Company> companies = Company.getAllCompanies();
        return ok(views.html.Companies.listOfCompanies.render(companies));
    }

    /* ------------------- vlade ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result vlade() {
        List<Company> vlada = Company.vlada();
        return ok(views.html.Companies.vlade.render(vlada));
    }

    /* ------------------- kantoni ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result kantoni() {
        List<Company> kantoni = Company.kantoni();
        return ok(views.html.Companies.kantoni.render(kantoni));
    }

    /* ------------------- Lokalna samouprava ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result lokalnasamouprava() {
        List<Company> lsamouprava = Company.lSamouprava();
        return ok(views.html.Companies.lokalnasamouprava.render(lsamouprava));
    }

    /* ------------------- Zavodi ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result zavodi() {
        List<Company> zavodi = Company.zavodi();
        return ok(views.html.Companies.zavodi.render(zavodi));
    }

    /* ------------------- Klinicki centri ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result klinickicentri() {
        List<Company> klinickicentri = Company.klinickiCentri();

        return ok(views.html.Companies.klinickicentri.render(klinickicentri));
    }

    /* ------------------- Prehrambeni proizvodjaci ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result prehrambeniproizvodjaci() {
        List<Company> prproizvodjaci = Company.prehrambeniProizvodjaci();

        return ok(views.html.Companies.prehrambeniproizvodjaci.render(prproizvodjaci));
    }

    /* ------------------- Velika, mala i srednja preduzeca ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result velikamalaisrednjapreduzeca() {
        List<Company> vmspreduzeca = Company.vmsPreduzeca();

        return ok(views.html.Companies.velikamalaisrednjapreduzeca.render(vmspreduzeca));
    }


    /* ------------------- render update company ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result updateCompanyRender(Integer companyId) {

        Company company = Company.findCompanyById(companyId);
        return ok(views.html.Companies.updateCompany.render(company));
    }

    /* ------------------- update company ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result updateCompany(Integer companyId) {

        DynamicForm form = Form.form().bindFromRequest();

        String name = form.field("name").value();
        String email = form.field("email").value();
        String phone = form.field("phone").value();
        String pdv = form.field("pdv").value();
        String category = form.field("category").value();

        Company.updateCompany(name, email, phone, pdv, category, companyId);
        return redirect(routes.Companies.listOfCompanies());
    }

    /* -------------------  delete company ------------------ */
    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result deleteCompany(Integer companyId) {
        Company.deleteCompany(companyId);

        return redirect(routes.Companies.listOfCompanies());
    }

}
