package controllers;

import database.Config;
import database.Users;
import models.User;
import utilities.JCrypt;

public class SetupController{
        public User setup(String storeName, String storeOwnerName, String password, String confirmPassword) {
                if (!password.equals(confirmPassword)) {
                        return null;
                }
                new Config().setStoreTitle(storeName);
                Users users = new Users(); 
                int userId = users.insert("owner", storeOwnerName, new JCrypt().hash(password));
                return users.getOne(userId);
        }
}