package models;

import com.avaje.ebean.Model;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by User on 2/8/2016.
 */
@Entity
public class AppUser extends Model {

    public static Finder<String, AppUser> finder = new Finder<>(AppUser.class);

    @Id
    public Integer id;

    public String email;
    public String password;
    public Integer userAccessLevel;

    public AppUser(){

    }
    public AppUser(String email, String password, Integer userAccessLevel) {
        this.email = email;
        this.password = password;
        this.userAccessLevel = userAccessLevel;
    }


    /* ------------------- finds user by email ------------------ */

    public static AppUser findUserByEmail(String email){
        AppUser user = finder.where().eq("email", email).findUnique();
        return user;
    }

    /* ------------------- authenticate user by email and password ------------------ */

    public static AppUser authenticate(String email, String password) {

        AppUser user = finder.where().eq("email", email).findUnique();

        if (user != null && BCrypt.checkpw(password, user.password)) {
            return user;
        } else {
            return null;
        }
    }
}
