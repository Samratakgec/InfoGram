package org.example.entity;

<<<<<<< HEAD
public class Post {
    String PostTitle ;
    String PostContent ;

    public String getPostTitle() {
        return PostTitle;
    }

    public String getPostContent() {
        return PostContent;
    }

    public void setPostTitle(String postTitle) {
        PostTitle = postTitle;
    }

    public void setPostContent(String postContent) {
        PostContent = postContent;
    }
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId ;
    private String postTitle ;
    private String postContent ;
    private String createdByUser ;



   public Post (String postTitle,String postContent, String createdByUser)
    {
        this.postTitle = postTitle ;
        this.postContent = postContent ;
        this.createdByUser = createdByUser ;
    }


>>>>>>> master
}
