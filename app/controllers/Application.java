package controllers;



import models.Album;
import models.User;
import play.data.*;
import play.mvc.*;
import views.html.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    static Form<Album> albumForm = Form.form(Album.class).bindFromRequest();


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
                signup.render(Form.form(User.class))
        );

    }

    public static Result signin(){
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if(loginForm.hasErrors()){
            return badRequest(index.render(loginForm));
        }else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    routes.Application.home()
            );
        }
    }

    public static Result home(){
        String username = request().username();
        List<Album> listOfAlbums = new ArrayList<>(Album.fetchAlbums(username));
        return ok(
            views.html.home.render(albumForm, listOfAlbums)
        );
    }

    public static Result register(){
        Form<User> regForm = Form.form(User.class).bindFromRequest();
        if(regForm.hasErrors()){
            return badRequest(signup.render(regForm));
        }else{
            User regUser = regForm.get();
            regUser.save();
            flash("Registration successful! Please log in");
            return redirect(
                    routes.Application.index()
            );
        }

    }

    public static Result logout(){
        session().clear();
        return redirect(
                routes.Application.index()
        );
    }


    public static Result albums(){

        String username = request().username();

        List<Album> listOfAlbums = new ArrayList<>(Album.fetchAlbums(username));
        return ok(
                views.html.ablums.albumlist.render(listOfAlbums)
        );
    }

    public static Result createAlbum(){
        String username = request().username();
        if(albumForm.hasErrors()){
            return badRequest();
        }else{
            Album album = albumForm.get();
            album.author = User.find.where().eq("email", username).findUnique();
            album.save();
            flash("New album created");
            return redirect(
                    routes.Application.home()
            );
        }

    }

    public static Result deleteAlbum(Long id){
        Album.deleteAlbum(id);
        flash("Album deleted successfully!");
        return redirect(
                routes.Application.home()
        );
    }

    public static Result updateAlbum(Long id){
        return redirect(
                routes.Application.home()
        );
    }

}
