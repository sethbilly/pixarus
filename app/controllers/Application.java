package controllers;



import models.User;
import play.data.*;
import play.mvc.*;
import views.html.*;

import java.io.File;

public class Application extends Controller {

    public  static class Login {
        public String email;
        public String password;

        public String validate(){
            if(User.authenticate(email, password) == null){
                return "Invalid user or password";
            }
            return null;
        }
    }

    public static Result index() {
        return ok(
                index.render(Form.form(Login.class))
        );
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



    public static Result login(){
        return ok(
                login.render(Form.form(Login.class))
        );
    }

    public static Result signup(){
        return ok(
                signup.render(Form.form(SignUp.class))
        );

    }

    public static Result signin(){
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if(loginForm.hasErrors()){
            return badRequest();
        }else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    ""
            );
        }
    }

    public static Result home(){
        return ok(
                views.html.pages.home.render()
        );
    }

    public static Result register(){

        return redirect("");
    }



    public static class SignUp {
        public String firstname;
        public String lastname;
        public String email;
        public String password;
        public String password2;
    }


}
