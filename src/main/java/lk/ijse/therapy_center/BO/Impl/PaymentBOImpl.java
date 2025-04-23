package lk.ijse.therapy_center.BO.Impl;

import lk.ijse.therapy_center.BO.PaymentBO;
import lk.ijse.therapy_center.Config.ConfigurationFactory;
import lk.ijse.therapy_center.DAO.DAOFactory;
import lk.ijse.therapy_center.DAO.Impl.PatientDaoImpl;
import lk.ijse.therapy_center.DAO.Impl.PaymentDaoImpl;
import lk.ijse.therapy_center.DAO.Impl.TherapySessionDaoImpl;
import lk.ijse.therapy_center.DTO.AppointmentDTO;
import lk.ijse.therapy_center.DTO.PaymentDTO;
import lk.ijse.therapy_center.Entity.Patient;
import lk.ijse.therapy_center.Entity.Payment;
import lk.ijse.therapy_center.Entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentBOImpl implements PaymentBO {
    PaymentDaoImpl paymentDaoImpl=(PaymentDaoImpl) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.PAYMENT);
    PatientDaoImpl patientDaoImpl=(PatientDaoImpl) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.PATIENT);
    TherapySessionDaoImpl therapySessionDaoImpl=(TherapySessionDaoImpl)DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.THERAPY_SESSION);
    Session session=null;
    public PaymentBOImpl() throws Exception {
    }

    @Override
    public boolean savePayments( PaymentDTO dto) throws Exception {
       Transaction transaction=session.beginTransaction();
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setDate(dto.getDate());
        payment.setAmount(dto.getAmount());
        payment.setStatus(dto.getStatus());

        Optional<Patient> optionalPatient = patientDaoImpl.findByPK(dto.getPatient_id());
        session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction1 = session.beginTransaction();
        if (optionalPatient.isEmpty()) {
            transaction1.rollback();
            return false;
        }
        session.close();
        System.out.println("bo : first ");
        Patient patient = optionalPatient.get();
        payment.setPatient(patient);

        Optional<TherapySession> optionalTherapySession=therapySessionDaoImpl.findByPK(dto.getSession_id());
        System.out.println("Optional therapy session :"+optionalTherapySession);
        session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction2 = session.beginTransaction();
        if (optionalTherapySession.isEmpty()) {
            transaction2.rollback();
            return false;
        }
        session.close();
        System.out.println("bo : second ");
        TherapySession therapySession=optionalTherapySession.get();
        payment.setTherapy_session(therapySession);

        boolean isSaved=false;
        try {
            isSaved=paymentDaoImpl.savePayments(session,payment);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }finally {
            session.close();
        }

        return isSaved;
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws Exception {
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setDate(dto.getDate());
        payment.setAmount(dto.getAmount());
        payment.setStatus(dto.getStatus());

        Optional<Patient> optionalPatient = patientDaoImpl.findByPK(dto.getPatient_id());
        session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        if (optionalPatient.isEmpty()) {
            transaction.rollback();
            return false;
        }
        session.close();
//        System.out.println("bo : second ");
        Patient patient = optionalPatient.get();
        payment.setPatient(patient);

        Optional<TherapySession> optionalTherapySession=therapySessionDaoImpl.findByPK(dto.getSession_id());
        session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction1 = session.beginTransaction();
        if (optionalTherapySession.isEmpty()) {
            transaction1.rollback();
            return false;
        }
        session.close();
        System.out.println("bo : second ");
        TherapySession therapySession=optionalTherapySession.get();
        payment.setTherapy_session(therapySession);

        return paymentDaoImpl.update(payment);
    }

    @Override
    public boolean deletePayment(String id) throws Exception {
        return paymentDaoImpl.delete(id);
    }

    @Override
    public List<PaymentDTO> getAllPayment() throws Exception {
        List<Payment> allPayments = paymentDaoImpl.getAll();
        List<PaymentDTO> dtos = new ArrayList<>();
        for (Payment payment : allPayments) {
            PaymentDTO dto = new PaymentDTO();
            dto.setId(payment.getId());
            dto.setDate(payment.getDate());
            dto.setAmount(payment.getAmount());
            dto.setStatus(payment.getStatus());
            dto.setSession_id(payment.getTherapy_session().getId());
            dto.setPatient_id(payment.getPatient().getId());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public String getNextPaymentId() throws SQLException {
        return paymentDaoImpl.getNextId();
    }

    @Override
    public Optional<PaymentDTO> findByPK(String pk) {
        return Optional.empty();
    }


}
