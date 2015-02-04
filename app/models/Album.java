package models;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by billy on 2/4/15.
 */
public class Album extends Model {

    public String title;
    public String description;
    public Date dateCreated;
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    List<Photo> photos;
    @ManyToOne
    public User author;

    public Album(String title, User author) {
        this.author = author;
        this.title = title;
        this.dateCreated = new Date();
    }
}
