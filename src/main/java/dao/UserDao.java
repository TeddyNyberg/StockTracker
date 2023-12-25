package dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {
    // Got code for logger from CoPilot chat asking for how to add robust logging
    private static final Logger logger = Logger.getLogger(UserDao.class.getName());
    private Session session;

    public UserDao() {
    }

    public User getUserByUsername(String username) {
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            user = query.uniqueResult();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    public void saveUser(User user) {
        super.save(user);
    }
}
