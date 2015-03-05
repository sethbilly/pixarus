package models;

import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Constraint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by billy on 2/3/15.
 */
@Entity
public class User extends Model {

    @Id
    @GeneratedValue
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
    List<Album> albums = new ArrayList<>();

    public User(String password, String email, String firstname, String lastname) {

        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.albums = new ArrayList<>();
        this.dateCreated = new Date();
    }

    public User() {}

    public static Model.Finder<Long, User> find = new Model.Finder (
            Long.class, User.class
    );

    public static User authenticate(String email, String password){
        User user =  User.find.where().eq("email", email).findUnique();
        if(user != null && BCrypt.checkpw(password, user.password)){
            return user;
        }else{
            return null;
        }

    }

    public static User create(User user){
        user.save();
        return user;
    }

    public void deleteUser(Long id){

        find.ref(id).delete();
    }

    public static void updateUser(Long id){

        find.ref(id).update();
    }
}
