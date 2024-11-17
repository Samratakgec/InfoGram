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
        String query = "INSERT INTO Post (CreatedByUser_ID, PostTitle, PostContent) VALUES (?, ?, ?)" ;
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
            int postId = rs.getInt(1);       // Column 1: PostID
            String postTitle = rs.getString(2);  // Column 2: PostTitle
            String postContent = rs.getString(3); // Column 3: PostContent

            System.out.println(postId + "\t" + postTitle + "\t" + postContent);
        }
    }

    @Override
    public void deleteMyPost(String user_id, int post_id) throws SQLException {
        String query = "Delete from Post where CreatedByUser_ID=? AND Post_ID=?" ;
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1,user_id);
        pstmt.setString(2, String.valueOf(post_id));

        int rs = pstmt.executeUpdate();
        if(rs>0)
        System.out.println("Post deleted successfully");
        else System.out.println("Action not allowed");

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
            String postTitle =  rs.getString(2);
            String postContent= rs.getString(3) ;
            String createdByUser =  rs.getString(4) ;

            System.out.println(postTitle+"\t"+postContent+"\t"+createdByUser);

        }
    }
}
