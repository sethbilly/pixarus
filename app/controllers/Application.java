package controllers;



import play.mvc.*;
import views.html.*;
import java.io.File;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your First Application"));
    }

    public static Result upload(){
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        if(picture != null){
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File file = picture.getFile();
            return ok("Picture uploaded successfully");
        }else{
            flash("error", "Picture failed to upload, try again!");
            return redirect("");
        }

    }

    public static Result signIn(){
        return  redirect(" ");
    }

    public static Result login(){
        return redirect("");
    }

    public static Result signUp(){
        return redirect(" ");

    }

    public static Result register(){
        return redirect("");
    }
}
