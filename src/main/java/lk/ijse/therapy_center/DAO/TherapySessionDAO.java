package lk.ijse.therapy_center.DAO;

//import lk.ijse.therapy_center.Entity.Therapist_for_programme;
import lk.ijse.therapy_center.Entity.TherapySession;

import java.util.List;

public interface TherapySessionDAO extends CrudDAO<TherapySession>{
    public List<TherapySession> getAllById(String id) throws Exception;
}
