package controllers;

import models.Company;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by User on 2/11/2016.
 */
public class Companies extends Controller {

            /* ------------------- render main companies page ------------------ */

    public Result companyMain(){
        return ok(views.html.Companies.companyMain.render());
    }


            /* ------------------- render create company ------------------ */

    public Result createCompanyRender(){
        return ok(views.html.Companies.createCompany.render());
    }

            /* ------------------- create company ------------------ */

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

    public Result listOfCompanies(){
        List<Company> companies = Company.getAllCompanies();
        return ok(views.html.Companies.listOfCompanies.render(companies));
    }

                    /* ------------------- render update company ------------------ */

    public Result updateCompanyRender(Integer companyId){

        Company company = Company.findCompanyById(companyId);
        return ok(views.html.Companies.updateCompany.render(company));
    }

                        /* ------------------- update company ------------------ */

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

    public Result deleteCompany(Integer companyId){
        Company.deleteCompany(companyId);

        return redirect(routes.Companies.listOfCompanies());
    }

}
