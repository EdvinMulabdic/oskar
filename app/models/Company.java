package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by User on 2/9/2016.
 */
@Entity
public class Company extends Model {

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
}
