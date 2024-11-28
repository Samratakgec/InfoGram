package org.example;

import org.example.doaImpl.PostDAOImpl;
import org.example.doaImpl.RatingDAOImpl;
import org.example.doaImpl.UserDAOImpl;
import org.example.entity.User;
import org.example.util.HiberUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.print("Hello and welcome!");
        Scanner sc = new Scanner(System.in) ;
        boolean hasLoggedIn = false;
        String loggedInUserId = null;
        int choice =0 ;

        PostDAOImpl postDAOimpl = new PostDAOImpl() ;
        RatingDAOImpl ratingDAOimpl = new RatingDAOImpl();
        while (true)
        {
            System.out.println("Welcome to InfoGram! Please make a choice:");
            System.out.println("1. Sign Up\n2. Login\n3. Create Post");
            System.out.println("4. Read My Posts\n5. Read Other Posts");
            System.out.println("6. Delete Post\n7. Rate a Post");
            System.out.println("8. Display Average Rating of a Post");
            System.out.println("9. Delete Post Rating");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            if (choice == 0) {
                System.out.println("Thank you for using InfoGram. Goodbye!");
                break;
            }
 
            switch (choice)
            {
                case 1: // Sign Up
                    System.out.print("Enter User ID: ");
                    String userId = sc.nextLine();

                    System.out.print("Enter First Name: ");
                    String firstName = sc.nextLine();

                    System.out.print("Enter Last Name: ");
                    String lastName = sc.nextLine();
                    System.out.print("Enter Contact Number: ");
                    String contacInfo = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();
                    User user = new User(userId,firstName,lastName,password,contacInfo) ;
                    UserDAOImpl userDAOImpl = new UserDAOImpl() ;
                    boolean ans1 = false ;
                    ans1 = userDAOImpl.signUp(user);
                    hasLoggedIn = ans1 ;
                    if(hasLoggedIn) {
                        System.out.println("User Signed-up successfully");
                        loggedInUserId = userId;
                    }
                    break;
                case 2: if (!hasLoggedIn) {
                    System.out.print("Enter User ID: ");
                    userId = sc.nextLine();
                    System.out.print("Enter Password: ");
                    password = sc.nextLine();
                    userDAOImpl = new UserDAOImpl();
                    boolean ans2 = false;
                    ans2 = userDAOImpl.logIn(userId, password);
                    hasLoggedIn = ans2;
                    if (hasLoggedIn) {
                        System.out.println("User Logged-in successfully");
                        loggedInUserId = userId;
                    }

                }
                else System.out.println("Already Logged in. Enjoy our Services");
                break;
                case 3 :
                    if (hasLoggedIn)
                    {

                        String  postTitle,postContent ;
                        System.out.println("Enter title of post");
                        postTitle = sc.nextLine() ;
                        System.out.println("Enter content of post");
                        postContent = sc.nextLine() ;
                        postDAOimpl.createPost(postTitle,postContent,loggedInUserId);
                    }
                    else System.out.println("Please login or signup");
                    break;
                case 4:
                    if (hasLoggedIn)
                    {
                        postDAOimpl.readMyPosts(loggedInUserId);
                    }
                    else System.out.println("Please login or signup");
                    break;
                case 5:
                    if (hasLoggedIn)
                    {
                        String postTitle ;
                        System.out.println("Enter Post Title");
                        postTitle = sc.nextLine() ;
                        postDAOimpl.readPost(postTitle);
                    }
                    else System.out.println("Please login or signup");
                    break;
                case 6:
                    if (hasLoggedIn)
                    {
                        Long postid ;
                        System.out.println("Enter the post-id ");
                        postid = sc.nextLong() ;
                        postDAOimpl.deletePost(postid);
                    }
                    else System.out.println("Please login or signup");
                    break;
                case 7:
                    if (hasLoggedIn)
                    {
                        Long postid ; int score ;
                        System.out.println("Enter post id");
                        postid = sc.nextLong() ;
                        System.out.println("Enter rating score");
                        score = sc.nextInt() ;

                        ratingDAOimpl.doRating(loggedInUserId,postid,score);
                    }
                    else System.out.println("Please login or signup");
                    break;
                case 8:
                    Long postid ;
                    System.out.println("Enter post id");
                    postid = sc.nextLong() ;
                    ratingDAOimpl.showAvgRating(postid);
                    break;
                case 9:
                    if (hasLoggedIn)
                    {
//                        Long postid ;
                        System.out.println("Enter post id");
                        postid = sc.nextLong() ;
                        ratingDAOimpl.removeRating(loggedInUserId,postid);
                    }
                    else System.out.println("Please login or signup");
                    break;
            }
        }
    }
}