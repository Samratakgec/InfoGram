package org.example.doaImpl;

import org.example.dao.PostDAO;
import org.example.entity.Post;
import org.example.util.HiberUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class PostDAOImpl implements PostDAO {
    Session session = HiberUtil.getSession() ;
    @Override
    public void createPost(String postTitle, String postContent, String createdByUser) {
        Transaction tx = session.beginTransaction() ;
        try{
            Post post = new Post() ;
            post.setPostTitle(postTitle);
            post.setPostContent(postContent);
            post.setCreatedByUser(createdByUser);
            session.save(post) ;
            System.out.println("Post Created successfully");
            tx.commit();
        }
        catch (Exception e){
            System.out.println("Error in creating post");
            tx.rollback();
        }
    }

    @Override
    public void deletePost(Long postId,String loggedInUserId) {
        Transaction tx = session.beginTransaction() ;
        boolean flag = false ;
        try {
            Post post = session.get(Post.class, postId);
            if (Objects.equals(post.getCreatedByUser(), loggedInUserId)) {
                session.remove(post);
                tx.commit();
                System.out.println("Post Deleted successfully");
            }
            else {
                flag = true ;
                throw new RuntimeException();
            }
        }
        catch (Exception e)
        {
            if (flag) System.out.println("Action Not Allowed!! You can't delete other than your post");
            else System.out.println("Error in deleting post! Please try again later");
            tx.rollback();
        }

    }

    @Override
    public void readPost(String postTitle) {
        Transaction tx = session.beginTransaction();
        try {
            // Correct the field name to match the Post entity
            String hql = "FROM Post p WHERE LOWER(p.postTitle) LIKE :title";
            Query<Post> query = session.createQuery(hql, Post.class);
            query.setParameter("title", "%" + postTitle.toLowerCase() + "%");

            List<Post> posts = query.getResultList();
            for (Post post : posts) {
                System.out.println(post);
            }
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public void readAllPosts() {

        try {
            String hql = "FROM Post" ;
            Query<Post> query = session.createQuery(hql, Post.class);
            // Execute the query and retrieve the post
            List<Post> posts = query.getResultList();
            for (Post post : posts) {
                System.out.println(post);
            }

        }
        catch (Exception e)
        {
            System.out.println("Unable to fetch data right now");
        }
    }


    @Override
    public void readMyPosts(String createdByUser) {
        Transaction tx = null;
        try {
            // Start the transaction
            tx = session.beginTransaction();

            // HQL to fetch the post with the specific postId
            String hql = "FROM Post p WHERE p.createdByUser = :createdByUser";
            Query<Post> query = session.createQuery(hql, Post.class);
            query.setParameter("createdByUser", createdByUser);

            // Execute the query and retrieve the post
            List<Post> posts = query.getResultList();

            // Process the retrieved posts (print or other operations)
            for (Post post : posts) {
                System.out.println(post);
            }

            // Commit the transaction
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // Rollback in case of an error
            }
            e.printStackTrace();
        }
    }
}
