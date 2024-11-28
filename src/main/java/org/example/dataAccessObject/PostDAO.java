package org.example.dataAccessObject;

import java.sql.SQLException;

public interface PostDAO {
    void createPost(String user_id, String PostTitle, String PostBody) throws SQLException;
    void readMyPost(String user_id) throws SQLException;
    void deleteMyPost (String user_id, int post_id) throws SQLException;
    void readOtherPost(String title) throws SQLException;
}
