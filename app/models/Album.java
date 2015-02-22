package models;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
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
    List<Photo> photos = new ArrayList<>();
    @ManyToOne
    public User author;

    public Album() {
    }

    public Album(String title, User author) {
        this.author = author;
        this.title = title;
        this.dateCreated = new Date();
    }

    public static Model.Finder<Long, Album> find = new Model.Finder(
      Long.class, Album.class
    );

    public static Album create(Album album){
        album.save();
        return album;
    }

    public static void deleteAlbum(Long album){

        find.ref(album).delete();
    }

    public static List<Album> fetchAlbums(String email){
        return Album.find.fetch("photos")
                .where().eq("author.email", email).findList();
    }
}
