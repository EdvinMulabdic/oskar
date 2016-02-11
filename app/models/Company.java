package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by User on 2/9/2016.
 */
@Entity
public class Company extends Model {
    public static Finder<String, Company> finder = new Finder<>(Company.class);

    @Id
    public Integer id;

    public String name;
    public String email;
    public String phone;
    public String pdv;

    public Company(){

    }

    public Company(String name, String email, String phone, String pdv) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pdv = pdv;
    }

            /* ------------------- create company ------------------ */

    public static void createCompany(String name, String email, String phone, String pdv){
        Company company = new Company();
        company.name = name;
        company.email = email;
        company.phone = phone;
        company.pdv = pdv;

        company.save();

    }

                /* ------------------- get all companies ------------------ */

    public static List<Company> getAllCompanies(){
        List<Company> companies = finder.all();
        return companies;
    }


                /* ------------------- get number of companies in database ------------------ */

    public static Integer getAllCompaniesSize(){
        Integer numberOfCompanies = finder.all().size();
        return numberOfCompanies;
    }
                    /* ------------------- find company by id ------------------ */

    public static Company findCompanyById (Integer companyId){
        Company company = finder.where().eq("id", companyId).findUnique();
        return company;
    }

            /* -------------------  update company ------------------ */

    public static void updateCompany(String name, String email, String phone, String pdv, Integer companyId){

        Company company = findCompanyById(companyId);

        company.name = name;
        company.email = email;
        company.phone = phone;
        company.pdv = pdv;

        company.update();

    }

            /* -------------------  delete company ------------------ */

    public static void deleteCompany(Integer companyId){

        Company company = findCompanyById(companyId);
        company.delete();
    }
}
