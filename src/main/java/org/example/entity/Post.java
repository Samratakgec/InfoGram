package org.example.entity;

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


}
