package org.example.doaImpl;

import org.example.dao.RatingDAO;
import org.example.entity.Post;
import org.example.entity.Rating;
import org.example.util.HiberUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RatingDAOImpl implements RatingDAO {
    Session session = HiberUtil.getSession() ;
    @Override
    public void doRating(String givenByUser, Long postId, int ratingScore) {
        Transaction tx = null;
        try {
            // Start a transaction
            tx = session.beginTransaction();

            // Fetch the Post entity
            Post post = session.get(Post.class, postId);
            if (post == null) {
                throw new IllegalArgumentException("Post with ID " + postId + " does not exist.");
            }

            // Check if the user has already rated this post
            String hql = "FROM Rating r WHERE r.userId = :userId AND r.post.postId = :postId";
            Rating existingRating = (Rating) session.createQuery(hql)
                    .setParameter("userId", givenByUser)
                    .setParameter("postId", postId)
                    .uniqueResult();

            if (existingRating != null) {
                // Update the existing rating
                existingRating.setRatingScore(ratingScore);
                session.update(existingRating);
            } else {
                // Create a new rating
                Rating newRating = new Rating();
                newRating.setRatingScore(ratingScore);
                newRating.setPost(post);
                newRating.setUserId(givenByUser);
                session.save(newRating);
            }

            // Commit the transaction
            tx.commit();
        } catch (Exception e) {
            // Rollback the transaction in case of error
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void showAvgRating(Long postId) {
        try{// HQL query to calculate the average rating
            Transaction tx = session.beginTransaction();
            String hql = "SELECT AVG(r.ratingScore) FROM Rating r WHERE r.post.postId = :postId";
            Double avgRating = (Double) session.createQuery(hql)
                    .setParameter("postId", postId)
                    .uniqueResult();
            tx.commit();
            if (avgRating != null) {
                System.out.println("Average rating for post ID " + postId + ": " + avgRating);
            } else {
                System.out.println("No ratings found for post ID " + postId);
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }



    @Override
    public void removeRating(String givenByUser, Long postId) {
        Transaction tx = null;
        try {
            // Start a transaction
            tx = session.beginTransaction();

            // Fetch the Post entity
            Post post = session.get(Post.class, postId);
            if (post == null) {
                throw new IllegalArgumentException("Post with ID " + postId + " does not exist.");
            }

            // Check if the user has already rated this post
            String hql = "FROM Rating r WHERE r.userId = :userId AND r.post.postId = :postId";
            Rating existingRating = (Rating) session.createQuery(hql)
                    .setParameter("userId", givenByUser)
                    .setParameter("postId", postId)
                    .uniqueResult();

            if (existingRating != null) {
                // delete the existing rating
                session.remove(existingRating);
                // Commit the transaction
                tx.commit();
            } else {
                System.out.println("No rating found");
            }


        } catch (Exception e) {
            // Rollback the transaction in case of error
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
