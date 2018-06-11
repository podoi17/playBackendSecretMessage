package helper;

import play.db.jpa.JPA;

import javax.persistence.EntityManager;

/**
 * Created by Manuel on 16.02.18.
 */
public class EntityHelper {
    public static EntityManager getEntityManager() {
        return JPA.em();
    }
}
