package lk.ijse.therapy_center.BO.Impl;

import lk.ijse.therapy_center.BO.UserBO;
import lk.ijse.therapy_center.DAO.DAOFactory;
import lk.ijse.therapy_center.DAO.SuperDAO;
import lk.ijse.therapy_center.DAO.UserDao;

import java.sql.SQLException;

public class UserBoImpl implements UserBO {
    UserDao userDaoImpl= (UserDao) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.USER);

    public UserBoImpl() throws Exception {
    }

    @Override
    public boolean updateUserPassword() throws Exception {
        return false;
    }

    @Override
    public boolean isUserValide(String userId, String password) throws SQLException {
        return userDaoImpl.isUserValide(userId, password);
    }
}
