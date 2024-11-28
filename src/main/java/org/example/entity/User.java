package org.example.entity;

<<<<<<< HEAD
public class User {
=======
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
>>>>>>> master
    private String userId;
    private String firstName;
    private String lastName;
    private String password;
<<<<<<< HEAD

    // Constructor
    public User(String userId, String firstName, String lastName, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
=======
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
>>>>>>> master
}
