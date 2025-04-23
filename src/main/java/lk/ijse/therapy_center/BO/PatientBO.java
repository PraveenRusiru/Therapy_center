package lk.ijse.therapy_center.BO;

import lk.ijse.therapy_center.DTO.PatientDTO;
import lk.ijse.therapy_center.DTO.TherapistDTO;
import lk.ijse.therapy_center.Entity.Patient;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PatientBO extends SuperBO {
    boolean savePatient(PatientDTO dto) throws Exception;
    boolean updatePatient(PatientDTO dto) throws Exception;
    boolean deletePatient(String id) throws Exception;
    List<PatientDTO> getAllPatient() throws Exception;
    String getNextPatientId() throws SQLException;
    public Optional<Patient> findByPK(String pk);
}
