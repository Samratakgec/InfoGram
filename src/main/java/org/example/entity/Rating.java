package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue
    private Long ratingId ;
   private String userId ;
    private int ratingScore ;
    @ManyToOne
    private Post post ;
    public Rating(String userId, int ratingScore)
    {
        this.userId = userId ;
        this.ratingScore = ratingScore ;
    }
}
