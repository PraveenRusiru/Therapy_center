package lk.ijse.therapy_center.BO;


import lk.ijse.therapy_center.DTO.TherapySessionDTO;
import lk.ijse.therapy_center.Entity.TherapySession;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TherapySessionBO extends SuperBO {
    boolean saveTherapistForProgramme(TherapySessionDTO dto) throws Exception;
    boolean updateTherapistForProgramme(TherapySessionDTO dto) throws Exception;
    boolean deleteTherapistForProgramme(String id) throws Exception;
    ArrayList<TherapySessionDTO> getAllTherapistForProgramme() throws Exception;
    String getNextTherapistForProgrammeId() throws SQLException;
    public List<TherapySessionDTO> getAllById(String id) throws Exception;
    public Optional<TherapySession> findByPK(String pk);
}
