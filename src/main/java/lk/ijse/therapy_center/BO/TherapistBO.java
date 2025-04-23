package lk.ijse.therapy_center.BO;

import lk.ijse.therapy_center.DTO.TherapistDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TherapistBO extends SuperBO {
    boolean saveTherapist(TherapistDTO dto) throws Exception;
    boolean updateTherapist(TherapistDTO dto) throws Exception;
    boolean deleteTherapist(String id) throws Exception;
    List<TherapistDTO> getAllTherapist() throws Exception;
    String getNextTherapistId() throws SQLException;
}
