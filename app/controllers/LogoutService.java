package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class LogoutService extends Controller {

    public Result logout() {
        session().remove("connected");
        System.out.println(session().values());
        return ok("bye");

    }
}
