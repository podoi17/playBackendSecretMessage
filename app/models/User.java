package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by Manuel on 19.02.18.
 */

@Entity
public class User {

    @Id
    private String email;
    private String username;
    private String password;
    private String randomString;

    public User(String email, String username, String password) {

        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User() {

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRandomString() {
        return randomString;
    }

    public void setRandomString() {
        this.randomString = UUID.randomUUID().toString();
    }

}
