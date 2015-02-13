package models;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by billy on 2/4/15.
 */
public class Album extends Model {

    @Id
    public Long id;
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

    public static Model.Finder<Long, Album> find = new Model.Finder(
      Long.class, Album.class
    );

    public static Album create(Album album,String title, Long user){
        album.author = User.find.ref(user);
        album.title = title;
        album.save();
        return album;
    }
}
