package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by billy on 2/7/15.
 */

@Security.Authenticated(Secured.class)

public class PhotoController extends Controller {

    public static Result createPhoto(Long id){
        return TODO;
    }

    public static Result deletePhoto(Long phootoId){
        return TODO;
    }


}
