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
 * Created by Manuel on 27.02.18.
 */
public class SecretMessageService extends Controller {
    public static EntityManager entityManager;


    @Transactional
    public Result checkSecretMessage() {
        final int arguments = 1;
        String message = "";
        entityManager = JPA.em();


        JsonNode data = request().body().asJson();
        if(DBHelpers.checkJsonForLength(data, arguments)) {
            message = data.get("message").asText();
            try {
                Object o = entityManager.createQuery("SELECT randomString FROM User WHERE randomString = '"+message+"'").getSingleResult();
                String result = o.toString();
                System.out.println(result);
                return ok(result);
            } catch (Exception e) {
                return status(UNAUTHORIZED);
            }
        } else {
            return status(UNAUTHORIZED);
        }



    }
}
