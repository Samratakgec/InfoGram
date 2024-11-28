package org.example.dao;

public interface RatingDAO {
    void doRating (String givenByUser, Long postId, int ratingScore) ;
    void showAvgRating (Long postId) ;

    void removeRating (String givenByUser, Long postId) ;
}
