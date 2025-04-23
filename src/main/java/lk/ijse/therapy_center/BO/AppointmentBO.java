package lk.ijse.therapy_center.BO;

import lk.ijse.therapy_center.DTO.AppointmentDTO;
import lk.ijse.therapy_center.DTO.PatientDTO;
import lk.ijse.therapy_center.Entity.Appointment;
import lk.ijse.therapy_center.Entity.Patient;
import lk.ijse.therapy_center.Entity.TherapySession;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AppointmentBO extends SuperBO {
    boolean saveAppointment(AppointmentDTO dto) throws Exception;
    boolean updateAppointment(AppointmentDTO dto) throws Exception;
    boolean deleteAppointment(String id) throws Exception;
    List<AppointmentDTO> getAllAppointment() throws Exception;
    String getNextAppointmentId() throws SQLException;
    public Optional<Appointment> findByPK(String pk);
    public List<TherapySession> findAllByCustomer(String customer) throws Exception;
    public boolean UpdateAppointment(AppointmentDTO dto, Session session) throws Exception;
}
