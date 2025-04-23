package lk.ijse.therapy_center.DAO;

import lk.ijse.therapy_center.DTO.PaymentDTO;
import lk.ijse.therapy_center.Entity.Payment;
import org.hibernate.Session;

public interface PaymentDAO extends CrudDAO<Payment> {
    public boolean savePayments(Session session, Payment entity) throws Exception ;

}
