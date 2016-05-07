package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
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
    public String category;

    public Company() {

    }

    public Company(String name, String email, String phone, String pdv, String category) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pdv = pdv;
        this.category = category;
    }

            /* ------------------- create company ------------------ */

    public static void createCompany(String name, String email, String phone, String pdv, String category) {
        Company company = new Company();
        company.name = name;
        company.email = email;
        company.phone = phone;
        company.pdv = pdv;
        company.category = category;

        company.save();

        for(int i = 0; i < 510; i ++){
            Company company1 = new Company();
            company1.name = "Edvin";
            company1.email = "edvin_bh@hotmail.com";
            company1.phone = "000";
            company1.pdv = "000";
            company1.category = "JFL";

            company1.save();
        }

    }

                /* ------------------- get all companies ------------------ */

    public static List<Company> getAllCompanies() {
        List<Company> companies = finder.all();
        return companies;
    }


                /* ------------------- get number of companies in database ------------------ */

    public static Integer getAllCompaniesSize() {
        Integer numberOfCompanies = finder.all().size();
        return numberOfCompanies;
    }
                    /* ------------------- find company by id ------------------ */

    public static Company findCompanyById(Integer companyId) {
        Company company = finder.where().eq("id", companyId).findUnique();
        return company;
    }

            /* -------------------  update company ------------------ */

    public static void updateCompany(String name, String email, String phone, String pdv, String category, Integer companyId) {

        Company company = findCompanyById(companyId);

        company.name = name;
        company.email = email;
        company.phone = phone;
        company.pdv = pdv;
        company.category = category;

        company.update();

    }

            /* -------------------  delete company ------------------ */

    public static void deleteCompany(Integer companyId) {

        Company company = findCompanyById(companyId);
        company.delete();
    }

                /* -------------------  vlada company ------------------ */

    public static List<Company> vlada() {

        List<Company> companies = Company.getAllCompanies();
        List<Company> vlada = new ArrayList<>();
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).category.equals("Vlade")) {
                vlada.add(companies.get(i));
            }
        }
        return vlada;
    }

                    /* -------------------  kantoni company ------------------ */

    public static List<Company> kantoni() {
        List<Company> companies = Company.getAllCompanies();
        List<Company> kantoni = new ArrayList<>();
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).category.equals("Kantoni")) {
                kantoni.add(companies.get(i));
            }
        }
        return kantoni;
    }

                    /* -------------------  lokalna samouprava company ------------------ */

    public static List<Company> lSamouprava() {
        List<Company> companies = Company.getAllCompanies();
        List<Company> lsamouprava = new ArrayList<>();
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).category.equals("Lokalna samouprava")) {
                lsamouprava.add(companies.get(i));
            }
        }
        return lsamouprava;
    }

                    /* -------------------  zavodi company ------------------ */

    public static List<Company> zavodi() {
        List<Company> companies = Company.getAllCompanies();
        List<Company> zavodi = new ArrayList<>();
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).category.equals("Zavodi")) {
                zavodi.add(companies.get(i));
            }
        }
        return zavodi;
    }

                    /* -------------------  klinicki centri company ------------------ */

    public static List<Company> klinickiCentri() {
        List<Company> companies = Company.getAllCompanies();
        List<Company> klinickicentri = new ArrayList<>();
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).category.equals("Klinicki centri")) {
                klinickicentri.add(companies.get(i));
            }
        }
        return klinickicentri;
    }

                    /* -------------------  prehrambeni proizvodjaci company ------------------ */

    public static List<Company> prehrambeniProizvodjaci() {
        List<Company> companies = Company.getAllCompanies();
        List<Company> prproizvodjaci = new ArrayList<>();
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).category.equals("Prehrambeni proizvodjaci")) {
                prproizvodjaci.add(companies.get(i));
            }
        }
        return prproizvodjaci;
    }

                    /* -------------------  velika mala i srendnja preduzeca  company ------------------ */

    public static List<Company> vmsPreduzeca() {
        List<Company> companies = Company.getAllCompanies();
        List<Company> vmspreduzeca = new ArrayList<>();
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).category.equals("Velika, mala i srednja preduzeca")) {
                vmspreduzeca.add(companies.get(i));
            }
        }
        return vmspreduzeca;
    }

}
