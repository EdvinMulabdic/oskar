package controllers;

import helpers.Authenticators;
import helpers.SessionsAndCookies;
import helpers.UserAccessLevel;
import models.AppUser;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.panel;

/**
 * Created by User on 2/8/2016.
 */
public class AppUsers extends Controller {

    public Result loginRender(){
        DynamicForm form = Form.form().bindFromRequest();

        String email = form.field("email").value();
        String password = form.field("password").value();

        AppUser user = AppUser.authenticate(email, password);

        if (user == null) {
            flash("login-error", "Incorrect email or password! Try again.");
        }else if(user.userAccessLevel == UserAccessLevel.ADMIN){
            SessionsAndCookies.setCookies(user);
            SessionsAndCookies.setUserSessionData(user);
            return ok(panel.render());
        }
        flash("login-error", "Incorrect email or password! Please, try again.");
        return redirect(routes.Application.index());
    }

    @Security.Authenticated(Authenticators.AdminFilter.class)
    public Result panelRender(){
        return ok(panel.render());
    }

    public Result logout(){
        SessionsAndCookies.clearUserSessionData();
        SessionsAndCookies.clearCookies();
        return redirect(routes.Application.index());
    }

}
