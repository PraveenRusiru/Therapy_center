package lk.ijse.therapy_center.DAO;

import lk.ijse.therapy_center.Entity.Therapist;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO <T> extends SuperDAO {
    boolean save(T entity) throws Exception;
    boolean update(T entity) throws Exception;
    boolean delete(String id) throws Exception;
    List<T> getAll() throws Exception;
    String getNextId() throws SQLException;
    public Optional<T> findByPK(String pk);
}
