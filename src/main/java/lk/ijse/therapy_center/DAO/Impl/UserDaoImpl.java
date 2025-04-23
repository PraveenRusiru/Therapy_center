package lk.ijse.therapy_center.DAO.Impl;

import lk.ijse.therapy_center.Config.ConfigurationFactory;
import lk.ijse.therapy_center.DAO.UserDao;
import lk.ijse.therapy_center.Entity.Therapist;
import lk.ijse.therapy_center.Entity.User;
import lk.ijse.therapy_center.Utill.BCryptUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    Session session =null;
    @Override
    public boolean save(User entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(User entity) throws Exception {

        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public List<User> getAll() throws Exception {
        return null;
    }

    @Override
    public String getNextId() throws SQLException {
        return "0";
    }

    @Override
    public Optional<User> findByPK(String pk) {
         session = ConfigurationFactory.getInstance().getSessionFactory();
        User user = session.get(User.class, pk);
        session.close();
        if (user == null) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public boolean isUserValide(String userId, String password) throws SQLException {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        User user=session.get(User.class,userId);
        System.out.println("user name : "+user.getUsername());
        return  BCryptUtil.checkPassword(password, user.getPassword());
    }
}
