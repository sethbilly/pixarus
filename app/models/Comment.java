package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by billy on 2/4/15.
 */
@Entity
public class Comment extends Model {

    public String author;
    public Date commentDate;

}
