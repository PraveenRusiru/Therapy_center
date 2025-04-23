package lk.ijse.therapy_center.BO;

import lk.ijse.therapy_center.DTO.Therapy_ProgrammeDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TherapistProgrammeBO extends SuperBO {
    boolean saveProgramme(Therapy_ProgrammeDTO dto) throws Exception;
    boolean updateProgramme(Therapy_ProgrammeDTO dto) throws Exception;
    boolean delete(String id) throws Exception;
    List<Therapy_ProgrammeDTO> getAllProgramme() throws Exception;
    String getNextProgrammeId() throws SQLException;
}
