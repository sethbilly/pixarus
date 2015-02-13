package controllers;

import play.mvc.Http;
import play.mvc.Security;


/**
 * Created by billy on 2/7/15.
 */
public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context ctx){

        return ctx.session().get("email");
    }


}
