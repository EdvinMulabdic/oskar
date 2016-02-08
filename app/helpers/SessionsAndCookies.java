package helpers;

import models.AppUser;
import play.mvc.Security;

import static play.mvc.Controller.response;
import static play.mvc.Controller.session;

/**
 * Created by User on 11/26/2015.
 */
public class SessionsAndCookies extends Security.Authenticator {

    public static void setCookies(AppUser user){
        response().setCookie("email", user.email);
        response().setCookie("userAccessLevel", user.userAccessLevel.toString());
        response().setCookie("id", user.id.toString());
    }

    /**
     * Clears user data from the cookies.
     * Should be used within logout function.
     */
    public static void clearCookies() {
        response().discardCookie("email");
        response().discardCookie("userAccessLevel");
        response().discardCookie("id");
    }

    public static void setUserSessionData(AppUser user) {
        session("email", user.email);
        session("userAccessLevel", user.userAccessLevel.toString());
        session("userId", user.id.toString());
    }

    /**
     * Clears user data from the session.
     * Should be used within logout function.
     */
    public static void clearUserSessionData() {
        session().clear();
    }
}
