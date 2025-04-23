package lk.ijse.therapy_center.DAO;

import lk.ijse.therapy_center.Entity.Availabilty;

import java.sql.SQLException;
import java.util.List;

public interface AvailablityDAO extends CrudDAO<Availabilty>{
    public List<Availabilty> getAllById(String id) throws Exception;
    public List<Availabilty> checkAvailability(Availabilty entity) throws Exception;
    public int getId(Availabilty entity) throws Exception;
}
