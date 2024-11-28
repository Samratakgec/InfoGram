package org.example.dao;

import org.example.entity.User;

public interface UserDAO {
    boolean signUp(User user) ;
    boolean logIn(String userId, String password) ;

}
