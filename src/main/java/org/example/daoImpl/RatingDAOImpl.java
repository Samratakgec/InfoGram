package org.example.daoImpl;

import org.example.dataAccessObject.RatingDAO;
import org.example.utility.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingDAOImpl implements RatingDAO {
    private Connection con ;
    public RatingDAOImpl()
    {
        try {
            con = ConnectionProvider.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void doRating(String user_id, int post_id, int rating) throws SQLException {
        String query = "INSERT INTO Rating(RatedByUser_ID,Post_ID,RatingScore)VALUES(?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1,user_id);
        pstmt.setString(2, String.valueOf(post_id));
        pstmt.setString(3, String.valueOf(rating));

        int rowsEff = pstmt.executeUpdate();
        if(rowsEff>0) System.out.println("Rating done successfully");
    }

    @Override
    public void showAvgRating(int post_id) throws SQLException {
        String query = "SELECT AVG(RatingScore) AS AverageRating FROM Rating WHERE Post_ID=?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, post_id);

            try (ResultSet avg = pstmt.executeQuery()) {
                if (avg.next()) {
                    double averageRating = avg.getDouble("AverageRating");
                    if (avg.wasNull()) { // Check if the average is null
                        System.out.println("No ratings available for this post.");
                    } else {
                        System.out.printf("The average rating for post %d is %.2f%n", post_id, averageRating);
                    }
                }
            }
        }
    }


    @Override
    public void updateRating(String user_id, int post_id, int rating) {
        String query = "UPDATE Rating SET RatingScore = ? WHERE RatedByUser_ID = ? AND Post_ID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, rating);
            pstmt.setString(2, user_id);
            pstmt.setInt(3, post_id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Rating updated successfully.");
            } else {
                System.out.println("No existing rating found for this user and post.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating rating: " + e.getMessage());
        }
    }

    @Override
    public void removeRating(String user_id, int post_id) {
        String query = "DELETE FROM Rating WHERE RatedByUser_ID = ? AND Post_ID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, user_id);
            pstmt.setInt(2, post_id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Rating removed successfully.");
            } else {
                System.out.println("No rating found for this user and post.");
            }
        } catch (SQLException e) {
            System.err.println("Error removing rating: " + e.getMessage());
        }
    }

}
