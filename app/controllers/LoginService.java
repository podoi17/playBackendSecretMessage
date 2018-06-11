package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import helper.DBHelpers;
import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.EntityManager;



/**
 * Created by Manuel on 20.02.18.
 */
public class LoginService extends Controller {
    public static EntityManager entityManager;

    @Transactional
    public Result doLogin() {
        final int loginLength = 2;
        JsonNode data = request().body().asJson();

        if(!DBHelpers.checkJsonForLength(data, loginLength)) {
            return badRequest("nicht genug argumente");
        }

        String email = data.get("email").asText();
        String password = data.get("password").asText();

        if(!email.contains("@")) {
            return badRequest();
        }

        if(!DBHelpers.findUserByEmail(email)) {
            return status(NOT_FOUND, "User existert nicht");
        } else {
            entityManager = JPA.em();
            User user = DBHelpers.getUser(email);

            if(!password.equals(user.getPassword())) {
                return status(FORBIDDEN, "falsches Passwort");
            }

            user.setRandomString();
            String random = user.getRandomString();
            entityManager.createQuery("UPDATE User SET randomString ='"+random+"' WHERE email ='"+user.getEmail()+"'").executeUpdate();
            session("connected", user.getEmail());
            System.out.println(session("connected"));
            String sessionName = session("connected");
            //return ok(login.render, random);
            return ok(user.getEmail());
        }

    }

}
