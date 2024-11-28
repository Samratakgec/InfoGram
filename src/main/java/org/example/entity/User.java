package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String password;
    private String contactInfo ;
//    @OneToMany
//    @JoinColumn (name = "userId")
//    List<Post> posts ;
//    public User(String userId, String firstName, String lastName, String password, String contacInfo)
//    {
//        this.userId = userId;
//        this.firstName = firstName ;
//        this.lastName = lastName ;
//        this.password = password ;
//        this.contactInfo = contacInfo ;
//    }
}
