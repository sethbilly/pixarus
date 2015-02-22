package controllers;

import play.mvc.Controller;
import play.mvc.Security;

/**
 * Created by billy on 2/10/15.
 */

@Security.Authenticated(Secured.class)
public class CommentController extends Controller {
}
