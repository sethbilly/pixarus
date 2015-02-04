package models;

import play.db.ebean.Model;

import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by billy on 2/4/15.
 */
public class Photo extends Model {

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
}
