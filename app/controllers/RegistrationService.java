package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import helper.DBHelpers;
import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.File;

/**
 * Created by Manuel on 19.02.18.
 */
public class RegistrationService extends Controller {
    private static EntityManager entityManager;
    private  User user;



    @Transactional
    public Result save() {
        final int registrationLenth = 3;
        String email = "";
        String username = "";
        String password = "";

        JsonNode userData = request().body().asJson();


        if(!DBHelpers.checkJsonForData(userData) || !DBHelpers.checkJsonForLength(userData, registrationLenth)) {
            return badRequest("nicht alle nötigen daten");
        }
        else {
            email = userData.get("email").asText();
            username = userData.get("username").asText();
            password = userData.findPath("password").asText();
            System.out.println(userData.size());
        }

        if(!email.contains("@")) {
             return badRequest("falsche email");
        } else {
             entityManager = JPA.em();
             user = new User(email, username, password);
             System.out.println(email);
             if(DBHelpers.findUserByEmail(email)) {
                 return status(CONFLICT, "User existiert bereits");
             }

             try {
                 entityManager.persist(user);

             } catch(PersistenceException pe) {
                 System.out.println(pe.getMessage());
             }
             return ok("User gespeichert");
         }

    }


}
