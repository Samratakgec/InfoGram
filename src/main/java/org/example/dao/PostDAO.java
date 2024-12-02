package org.example.dao;

public interface PostDAO {

    void createPost (String postTitle , String postContent,String createdByUser) ;
    void deletePost (Long postId, String loggedInUserId) ;
    void readPost (String postTitle ) ;
    void readAllPosts () ;
    void readMyPosts (String postId) ;
}
