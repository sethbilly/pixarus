package models;

import play.db.ebean.Model;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by billy on 2/9/15.
 */
public class Tag extends Model {

    @Id
    public Long id;
    public String title;
    @ManyToMany
    public List<Photo> photos = new ArrayList<>();

    public Tag(String title, Photo photo) {
        this.title = title;
        this.photos.add(photo);
    }

    public static Model.Finder<Long, Tag> find = new Model.Finder<>(
            Long.class, Tag.class
    );


}
