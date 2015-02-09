package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.OneToMany;
import javax.validation.Constraint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by billy on 2/3/15.
 */
public class User extends Model {

    @Constraints.Required
    public String username;
    @Constraints.Required
    public String password;
    @Constraints.Email
    public String email;
    public Date dateCreated;
    @OneToMany(mappedBy = "author")
    List<Album> albums;

    public User(String password, String email) {

        this.password = password;
        this.email = email;
        this.albums = new ArrayList<>();
        this.dateCreated = new Date();
    }
}
