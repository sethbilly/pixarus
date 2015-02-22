package models;

import play.db.ebean.Model;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by billy on 2/4/15.
 */
public class Photo extends Model {

    @Id
    public Long id;
    public String title;
    public String description;
    public Date photoDate;
    public String photoUrl;
    @ManyToOne
    public Album album;


    public Photo(String title, String photoUrl, Album album) {
        this.title = title;
        this.photoUrl = photoUrl;
        this.album = album;
        this.photoDate = new Date();
    }

    public static Finder<Long, Photo> find = new Model.Finder (
            Long.class, Photo.class
    );

    public static Photo create(Photo photo, Long album){
        photo.album = Album.find.ref(album);
        photo.save();
        return photo;
    }

    public static void deletePhoto(Long id){
        find.ref(id).delete();
    }

}
