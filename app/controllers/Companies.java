package controllers;

import helpers.Authenticators;
import models.Company;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

/**
 * Created by User on 2/11/2016.
 */
public class Companies extends Controller {

            /* ------------------- render main companies page ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result companyMain(){
        return ok(views.html.Companies.companyMain.render());
    }


            /* ------------------- render create company ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result createCompanyRender(){
        return ok(views.html.Companies.createCompany.render());
    }

            /* ------------------- create company ------------------ */
            @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result createCompany(){
        DynamicForm form = Form.form().bindFromRequest();

        String name = form.field("name").value();
        String email = form.field("email").value();
        String phone = form.field("phone").value();
        String pdv = form.field("pdv").value();

        Company.createCompany(name, email, phone, pdv);

        return redirect(routes.Companies.companyMain());
    }

                /* ------------------- list of companies ------------------ */
                @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result listOfCompanies(){
        List<Company> companies = Company.getAllCompanies();
        return ok(views.html.Companies.listOfCompanies.render(companies));
    }

                    /* ------------------- render update company ------------------ */
                    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result updateCompanyRender(Integer companyId){

        Company company = Company.findCompanyById(companyId);
        return ok(views.html.Companies.updateCompany.render(company));
    }

                        /* ------------------- update company ------------------ */
                        @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result updateCompany(Integer companyId){

        DynamicForm form = Form.form().bindFromRequest();

        String name = form.field("name").value();
        String email = form.field("email").value();
        String phone = form.field("phone").value();
        String pdv = form.field("pdv").value();

        Company.updateCompany(name, email, phone, pdv, companyId);
        return redirect(routes.Companies.listOfCompanies());
    }

                /* -------------------  delete company ------------------ */
                @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result deleteCompany(Integer companyId){
        Company.deleteCompany(companyId);

        return redirect(routes.Companies.listOfCompanies());
    }

}
