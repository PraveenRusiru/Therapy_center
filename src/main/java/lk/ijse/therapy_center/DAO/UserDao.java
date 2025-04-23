package lk.ijse.therapy_center.DAO;

import lk.ijse.therapy_center.Entity.User;

import java.sql.SQLException;

public interface UserDao extends CrudDAO<User>{
    public boolean isUserValide(String userId,String password) throws SQLException;
}
