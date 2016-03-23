package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by User on 3/2/2016.
 */
@Entity
public class File extends Model {

    @Id
    public Integer id;
    public String name;
    public String public_id;
    public String file_url;
    public String secret_file_url;
    @ManyToOne
    public Person person;


    public File(){

    }

    public File(String name, String public_id, String file_url, String secret_file_url, Person person) {
        this.name = name;
        this.public_id = public_id;
        this.file_url = file_url;
        this.secret_file_url = secret_file_url;
        this.person = person;
    }

}
