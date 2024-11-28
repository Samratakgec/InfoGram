package org.example.daoImpl;

import org.example.dataAccessObject.UserDAO;
import org.example.entity.User;
import org.example.utility.ConnectionProvider;

import java.sql.*;
import java.util.Objects;

public class UserDAOImpl implements UserDAO {

    private Connection con ;
    public UserDAOImpl()
    {
        try {
            con = ConnectionProvider.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void signUp(User user) throws SQLException {
        String searchQuery = "SELECT * FROM User WHERE User_id=?";
        try (PreparedStatement pstmt1 = con.prepareStatement(searchQuery)) {
            pstmt1.setString(1, user.getUserId());
            try (ResultSet rs = pstmt1.executeQuery()) {
                if (rs.next()) {
                    System.out.println("User already registered, please login.");
                    return;
                }
            }
        }

        String insertQuery = "INSERT INTO User (User_ID, FirstName, LastName, Password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(insertQuery)) {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
            pstmt.setString(4, user.getPassword()); // Remember to hash the password before storing
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User registered successfully!");
            }
        }
    }




    @Override
    public void login(String user_id, String password) throws SQLException {
        String searchQuery = "Select Password from User where User_id=?";
        PreparedStatement pstmt = con.prepareStatement(searchQuery);
        pstmt.setString(1,user_id);
        ResultSet check = pstmt.executeQuery();
        if (!check.next()) System.out.println("User doesn't exists, Please Signup first");
        if (Objects.equals(check.getString(1), password))
        {
            System.out.println("User Logged in successfully");
        }
        else {
            System.out.println("Incorrect Password");
        }
    }
}
