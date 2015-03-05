import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import models.Album;
import models.User;
import org.junit.*;

import org.mindrot.jbcrypt.BCrypt;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test
    public void createUser(){
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            @Override
            public void run() {
                User user = new User("secret", "sirbillbones@yahoo.com", "seth", "billy");
                user.save();
                assertThat(user.firstname).isEqualTo("seth");
            }
        });

    }

    @Test
    public void authenticateUser(){
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            @Override
            public void run() {
                User user = new User();
                user.firstname = "seth";
                user.lastname  = "billy";
                user.email = "sirbillbones@yahoo.com";
                user.password = BCrypt.hashpw("secret", BCrypt.gensalt());

                user.save();

                User userLogin = User.authenticate("sirbillbones@yahoo.com", "secret");


                assertThat(userLogin.email).isEqualTo("sirbillbones@yahoo.com");
            }
        });

    }

    @Test
    public void createAlbumByUser(){
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            @Override
            public void run() {
                User user = new User();
                user.firstname = "seth";
                user.lastname  = "billy";
                user.email = "sirbillbones@yahoo.com";
                user.password = BCrypt.hashpw("secret", BCrypt.gensalt());

                user.save();

                Album album = new Album("Demo Album", user);
                album.save();

                assertThat(album.title).isEqualTo("Demo Album");
                assertThat(album.author.firstname).isEqualTo("seth");
            }
        });

    }
}
