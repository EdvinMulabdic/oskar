package helpers;

import models.AppUser;
import play.mvc.Http;

/**
 * Created by User on 2/8/2016.
 */
public class UserAccessLevel {

    public static final Integer ADMIN = 1;

    public static AppUser getCurrentUser(Http.Context ctx) {
        String email = ctx.session().get("email");
        if (email == null)
            return null;
        return AppUser.findUserByEmail(email);
    }


}
