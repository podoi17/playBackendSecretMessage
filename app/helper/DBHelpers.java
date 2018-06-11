package helper;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;

/**
 * Created by Manuel on 20.02.18.
 */
public class DBHelpers {

    public static EntityManager entityManager;

    public static boolean checkLoginStatus(User user) {
        return false;
    }

    public static boolean findUser(User user) {
        entityManager = JPA.em();
        User u = entityManager.find(User.class, user.getEmail());
        return entityManager.contains(u);
    }

    public static boolean findUserByEmail(String email) {
        entityManager = JPA.em();
        String foundEmail="";
        try {
            foundEmail = entityManager.find(User.class, email).getEmail();
            return true;
        } catch (NullPointerException e) {
            return false;
        }

    }

    public static User getUser(String email) {
        entityManager = JPA.em();
        User user = entityManager.find(User.class, email);
        return user;
    }

    public static boolean checkJsonForData(JsonNode data) {
        return data.has("email") && data.has("username") && data.has("password");
    }

    public static boolean checkJsonForLength(JsonNode data, int l) {
        int length = data.size();
        return (length == l);

    }


}
