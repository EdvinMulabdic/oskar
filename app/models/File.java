package models;

import com.avaje.ebean.Model;
import com.cloudinary.Cloudinary;
import play.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.IOException;
import java.util.Map;

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

    public static Cloudinary cloudinary;

    public File(){

    }

    public File(String name, String public_id, String file_url, String secret_file_url, Person person, Cloudinary cloudinary) {
        this.name = name;
        this.public_id = public_id;
        this.file_url = file_url;
        this.secret_file_url = secret_file_url;
        this.person = person;
        this.cloudinary = cloudinary;
    }

    public static File createFile(java.io.File file, Person person, String name){
        Map result;
        try {
            result = cloudinary.uploader().upload(file, null);
            return create(result,person, name);
        } catch (IOException e) {
            Logger.debug("Nisam uspio snimiti file!!!");
        }
        return  null;
    }

    public static File create(Map uploadResult,Person person, String name){
        File file = new File();

        file.public_id = (String) uploadResult.get("public_id");
        file.secret_file_url = (String)uploadResult.get("secure_url");
        file.file_url = (String)uploadResult.get("url");
        file.name = name;
        file.save();
        return file;
    }
}
