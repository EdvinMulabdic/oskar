package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by User on 2/9/2016.
 */
@Entity
public class Certificate extends Model {

    @Id
    public Integer id;

    public String mark;
    public String name;
    public String duration;
    public String category;

    @ManyToOne
    Person staff;

    public Certificate(){

    }
    public Certificate(String mark, String name, String duration,String category, Person staff) {
        this.mark = mark;
        this.name = name;
        this.duration = duration;
        this.category = category;
        this.staff = staff;
    }
}
