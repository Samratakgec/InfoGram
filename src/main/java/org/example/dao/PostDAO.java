package org.example.dao;

public interface PostDAO {
    void createPost (String postTitle , String postContent,String createdByUser) ;
    void deletePost (Long postId) ;
    void readPost (String postTitle ) ;
    void readMyPosts (String postId) ;
}
