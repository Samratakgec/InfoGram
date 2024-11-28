package org.example.doaImpl;

import org.example.dao.UserDAO;
import org.example.entity.User;
import org.example.util.HiberUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Objects;

public class UserDAOImpl implements UserDAO {
    Session session = HiberUtil.getSession() ;
    @Override
    public boolean signUp(User user) {
        Transaction tx = session.beginTransaction() ;
        try
        {
            session.save(user);
            tx.commit();
            return true ;
        }
        catch (Exception e){
            tx.rollback();
            System.out.println(STR."Error in signUp :\{e.toString()}");
            return false ;
        }

    }

    @Override
    public boolean logIn(String userId, String password) {
        try
        {
           User user = session.get(User.class, userId);
           if (user == null) throw new UserNotExistsException("UserNotExists") ;
           String correctPassword = user.getPassword() ;
            if ( Objects.equals(password, correctPassword))return true;
            else throw  new RuntimeException("Wrong Password") ;
        }
        catch (UserNotExistsException e) {
            System.out.println("User Not Found, please signUp");
            return false ;
        }
        catch (RuntimeException e){
            System.out.println("Wrong Password");
            return false ;
        }
    }
}
