package org.example;

import org.example.daoImpl.PostDAOImpl;
import org.example.daoImpl.RatingDAOImpl;
import org.example.daoImpl.UserDAOImpl;
import org.example.entity.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean hasLoggedIn = false;
        String loggedInUserId = null;
        int choice;

        UserDAOImpl userdaoimpl = new UserDAOImpl();
        PostDAOImpl postdaoimpl = new PostDAOImpl();
        RatingDAOImpl ratingdaoimpl = new RatingDAOImpl() ;

        while (true) {
            System.out.println("Welcome to InfoGram! Please make a choice:");
            System.out.println("1. Sign Up\n2. Login\n3. Create Post");
            System.out.println("4. Read My Posts\n5. Read Other Posts");
            System.out.println("6. Delete Post\n7. Rate a Post");
            System.out.println("8. Display Average Rating of a Post");
            System.out.println("9. Update Post Rating\n10. Delete Post Rating");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            if (choice == 0) {
                System.out.println("Thank you for using InfoGram. Goodbye!");
                break;
            }

            try {
                switch (choice) {
                    case 1: // Sign Up
                        System.out.print("Enter User ID: ");
                        String userId = sc.nextLine();

                        System.out.print("Enter First Name: ");
                        String firstName = sc.nextLine();

                        System.out.print("Enter Last Name: ");
                        String lastName = sc.nextLine();

                        System.out.print("Enter Password: ");
                        String password = sc.nextLine();

                        User newUser = new User(userId, firstName, lastName, password);
                        userdaoimpl.signUp(newUser);
                        loggedInUserId = userId;
                        break;

                    case 2: // Login
                        System.out.print("Enter User ID: ");
                        userId = sc.nextLine();

                        System.out.print("Enter Password: ");
                        password = sc.nextLine();

                        userdaoimpl.login(userId, password);
                        hasLoggedIn = true;
                        loggedInUserId = userId;
                        break;

                    case 3: // Create Post
                        if (hasLoggedIn) {
                            System.out.print("Enter Post Title: ");
                            String postTitle = sc.nextLine();

                            System.out.print("Enter Post Content: ");
                            String postContent = sc.nextLine();

                            postdaoimpl.createPost(loggedInUserId, postTitle, postContent);
                        } else {
                            System.out.println("You must log in first!");
                        }
                        break;

                    case 4: // Read My Posts
                        if (hasLoggedIn) {
                            postdaoimpl.readMyPost(loggedInUserId);
                        } else {
                            System.out.println("You must log in first!");
                        }
                        break;

                    case 5: // Read Other Posts
                        System.out.print("Enter the Title to view posts: ");
                        String title = sc.nextLine();
                        postdaoimpl.readOtherPost(title);
                        break;

                    case 6: // Delete Post
                        if (hasLoggedIn) {
                            System.out.print("Enter Post ID to delete: ");
                            int postId = sc.nextInt();
                            sc.nextLine(); // Consume the newline character

                            postdaoimpl.deleteMyPost(loggedInUserId, postId);
                        } else {
                            System.out.println("You must log in first!");
                        }
                        break;

                    case 7: // Rate a Post
                        if (hasLoggedIn) {
                            System.out.print("Enter Post ID to rate: ");
                            int postId = sc.nextInt();

                            System.out.print("Enter Rating (1-5): ");
                            int rating = sc.nextInt();
                            sc.nextLine(); // Consume the newline character

                            ratingdaoimpl.doRating(loggedInUserId, postId, rating);
                        } else {
                            System.out.println("You must log in first!");
                        }
                        break;

                    case 8: // Display Average Rating
                        System.out.print("Enter Post ID to view average rating: ");
                        int postId = sc.nextInt();
                        sc.nextLine(); // Consume the newline character

                        ratingdaoimpl.showAvgRating(postId);
                        break;

                    case 9: // Update Rating
                        if (hasLoggedIn) {
                            System.out.print("Enter Post ID to update rating: ");
                            postId = sc.nextInt();

                            System.out.print("Enter New Rating (1-5): ");
                            int newRating = sc.nextInt();
                            sc.nextLine(); // Consume the newline character

                            ratingdaoimpl.updateRating(loggedInUserId, postId, newRating);
                        } else {
                            System.out.println("You must log in first!");
                        }
                        break;

                    case 10: // Delete Rating
                        if (hasLoggedIn) {
                            System.out.print("Enter Post ID to delete your rating: ");
                            postId = sc.nextInt();
                            sc.nextLine(); // Consume the newline character

                            ratingdaoimpl.removeRating(loggedInUserId, postId);
                        } else {
                            System.out.println("You must log in first!");
                        }
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }

        sc.close();
    }
}
