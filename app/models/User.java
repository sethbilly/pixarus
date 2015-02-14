package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Constraint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by billy on 2/3/15.
 */
public class User extends Model {

    @Id
    public Long id;
    @Constraints.Required
    public String firstname;
    @Constraints.Required
    public String lastname;
    @Constraints.Required
    public String password;
    @Constraints.Email
    public String email;
    public Date dateCreated;
    @OneToMany(mappedBy = "author")
    List<Album> albums;

    public User(String password, String email, String firstname, String lastname) {

        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.albums = new ArrayList<>();
        this.dateCreated = new Date();
    }

    public static Model.Finder<Long, User> find = new Model.Finder (
            Long.class, User.class
    );

    public static User authenticate(String email, String password){
        return User.find.where().eq("email", email)
                .eq("password", password)
                .findUnique();
    }
}
