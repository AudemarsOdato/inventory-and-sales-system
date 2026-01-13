package controllers;

import context.StoreContext;
import context.UserContext;
import database.Config;
import database.Users;
import models.Response;
import utilities.JCrypt;

public class SetupController{
        public Response setup(String storeName, String storeOwnerName, String password, String confirmPassword) {
                if (!password.equals(confirmPassword)) {
                        return new Response(400, "Password does not match.");
                }
                new Config().setStoreTitle(storeName);

                Users users = new Users(); 
                int userId = users.insert("owner", storeOwnerName, new JCrypt().hash(password));

                // set user and store context once stored in database

                new UserContext(users.getOne(userId).getId(), users.getOne(userId).getName());
                new StoreContext(new Config().getStoreTitle());

                return new Response<>(200, users.getOne(userId));
        }
}