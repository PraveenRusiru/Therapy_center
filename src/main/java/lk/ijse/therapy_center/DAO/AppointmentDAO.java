package lk.ijse.therapy_center.DAO;

import lk.ijse.therapy_center.Entity.Appointment;
import lk.ijse.therapy_center.Entity.TherapySession;
import org.hibernate.Session;

import java.util.List;

public interface AppointmentDAO extends CrudDAO<Appointment> {
    List<TherapySession> findAllByCustomer(String customer) throws Exception;
    boolean UpdateAppointment(Appointment appointment, Session session) throws Exception;
}
