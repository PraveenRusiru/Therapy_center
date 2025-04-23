package lk.ijse.therapy_center.BO;

import lk.ijse.therapy_center.DAO.CrudDAO;
import lk.ijse.therapy_center.DAO.SuperDAO;

import java.sql.SQLException;

public interface UserBO extends SuperBO {
    boolean updateUserPassword() throws Exception;
    public boolean isUserValide(String userId,String password) throws SQLException;
}
