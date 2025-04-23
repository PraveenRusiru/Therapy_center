package lk.ijse.therapy_center.BO;

import lk.ijse.therapy_center.DTO.AppointmentDTO;
import lk.ijse.therapy_center.DTO.PatientDTO;
import lk.ijse.therapy_center.DTO.PaymentDTO;
import lk.ijse.therapy_center.Entity.Patient;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PaymentBO extends SuperBO {
    boolean savePayments(  PaymentDTO dto) throws Exception;
    boolean updatePayment(PaymentDTO dto) throws Exception;
    boolean deletePayment(String id) throws Exception;
    List<PaymentDTO> getAllPayment() throws Exception;
    String getNextPaymentId() throws SQLException;
    public Optional<PaymentDTO> findByPK(String pk);

}
