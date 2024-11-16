package org.example.dataAccessObject;

import org.example.entity.User;

import java.sql.SQLException;

public interface UserDAO {
    void signUp(User user) throws SQLException;
    void login(String user_id, String Password) throws SQLException;
}
