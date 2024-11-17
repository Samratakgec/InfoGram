package org.example.dataAccessObject;

import java.sql.SQLException;

public interface RatingDAO {
    void doRating (String user_id, int post_id, int rating) throws SQLException;
    void showAvgRating (int post_id) throws SQLException;
    void updateRating (String user_id, int post_id, int rating) ;
    void removeRating (String user_id, int post_id) ;
}
