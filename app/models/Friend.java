package models;

import play.db.ebean.Model;

import javax.persistence.Id;

/**
 * Created by billy on 2/13/15.
 */
public class Friend extends Model{

    @Id
    public Long id;
    public User friend1;
    public User friend2;



    public static Model.Finder<Long, Friend> find = new Finder<Long, Friend>(
            Long.class, Friend.class
    );
}
