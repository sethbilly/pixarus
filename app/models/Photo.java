package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by billy on 2/4/15.
 */
@Entity
public class Photo extends Model {

    @Id
    @GeneratedValue
    public Long id;
    public String title;
    public String description;
    public Date photoDate;
    public String photoUrl;
    @Lob
    public byte[] picture;
    @ManyToOne
    public Album album;


    public Photo(String title, String photoUrl, Album album, byte[] picture) {
        this.title = title;
        this.photoUrl = photoUrl;
        this.album = album;
        this.picture = picture;
        this.description = new String();
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
