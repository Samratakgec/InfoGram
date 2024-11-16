package org.example.daoImpl;

import org.example.dataAccessObject.PostDAO;
import org.example.utility.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostDAOImpl implements PostDAO {
    private Connection con ;
    public PostDAOImpl()
    {
        try {
            con = ConnectionProvider.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void createPost(String user_id, String PostTitle, String PostBody) throws SQLException {
        String query = "INSERT INTO Post (CreatedByUser_ID, PostTitle, PostBody) VALUES (?, ?, ?)" ;
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1,user_id);
        pstmt.setString(2,PostTitle);
        pstmt.setString(3,PostBody);

        int rowsUpdated = pstmt.executeUpdate() ;
        if (rowsUpdated>0) System.out.println("Post created successfully");

    }

    @Override
    public void readMyPost(String user_id) throws SQLException {
        String query = "Select * from Post where CreatedByUser_ID=?" ;
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1,user_id);

        ResultSet rs = pstmt.executeQuery();
        System.out.println("PostID\tPostTitle\tPostContent");
        while (rs.next())
        {
            rs.getString(0) ;
            rs.getString(1) ;
            rs.getString(2) ;
        }
    }

    @Override
    public void deleteMyPost(String user_id, int post_id) throws SQLException {
        String query = "Delete from Post where CreatedByUser_ID=? AND Post_ID=?" ;
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1,user_id);
        pstmt.setString(2, String.valueOf(post_id));

        ResultSet rs = pstmt.executeQuery();
        System.out.println("Post deleted successfully");

    }

    @Override
    public void readOtherPost(String title) throws SQLException {
        String query = "Select * from Post where PostTitle=?" ;
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1,title);

        ResultSet rs = pstmt.executeQuery();
        System.out.println("PostTitle\tPostContent\tCreatedByUser");
        while (rs.next())
        {
            rs.getString(1);
            rs.getString(2) ;
            rs.getString(3) ;
        }
    }
}
