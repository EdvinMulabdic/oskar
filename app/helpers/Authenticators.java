package helpers;

import models.AppUser;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import controllers.routes;

/**
 * Created by User on 2/8/2016.
 */
public class Authenticators {
    public static class AdminFilter extends Security.Authenticator {

        @Override
        public String getUsername(Http.Context ctx) {
            if (!ctx.session().containsKey("email"))
                return null;
            String email = ctx.session().get("email");
            AppUser u = AppUser.findUserByEmail(email);
            if (u != null && u.userAccessLevel == UserAccessLevel.ADMIN)
                return u.email;
            return null;
        }

        @Override
        public Result onUnauthorized(Http.Context ctx) {
            return redirect(routes.Application.index());
        }
    }
}
