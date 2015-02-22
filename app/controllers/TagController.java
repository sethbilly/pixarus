package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by billy on 2/7/15.
 */

@Security.Authenticated(Secured.class)
public class TagController extends Controller {


}
