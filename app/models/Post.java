package models;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by billy on 2/3/15.
 */
public class Post extends Model{

    public Date datePosted;
    public String title;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    public List<Comment> comments;

    public Post(String title) {
        this.datePosted = new Date();
        this.title = title;
    }
}
