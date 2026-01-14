package controllers;

import database.Users;
import models.Response;
import models.User;
import utilities.JCrypt;

public class LoginController{
        public Response login(String username, String password) {
                Users users = new Users();
                
                User user = users.getOne(username);
                if (user == null) {
                        return new Response<>(400, "Incorrect username!");
                }
                
                if (!(new JCrypt().isMatch(password, user.getPassword()))) {
                        return new Response<>(400, "Incorrect password!");
                }

                return new Response<>(200, user);
        }
}