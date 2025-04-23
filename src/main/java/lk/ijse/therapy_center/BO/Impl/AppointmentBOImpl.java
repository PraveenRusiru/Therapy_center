package lk.ijse.therapy_center.BO.Impl;

import lk.ijse.therapy_center.BO.AppointmentBO;
import lk.ijse.therapy_center.Config.ConfigurationFactory;
import lk.ijse.therapy_center.DAO.DAOFactory;
import lk.ijse.therapy_center.DAO.Impl.AppointmentDaoImpl;
import lk.ijse.therapy_center.DAO.Impl.PatientDaoImpl;
import lk.ijse.therapy_center.DAO.Impl.TherapySessionDaoImpl;
import lk.ijse.therapy_center.DTO.AppointmentDTO;
import lk.ijse.therapy_center.Entity.Appointment;
import lk.ijse.therapy_center.Entity.Patient;
import lk.ijse.therapy_center.Entity.Therapist;
import lk.ijse.therapy_center.Entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentBOImpl implements AppointmentBO {
    AppointmentDaoImpl appointmentDaoImpl=(AppointmentDaoImpl) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.APPOINTMENT);
    PatientDaoImpl patientDaoImpl=(PatientDaoImpl) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.PATIENT);
    Session session=null;
    TherapySessionDaoImpl therapySessionDaoImpl=(TherapySessionDaoImpl)DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.THERAPY_SESSION);
    public AppointmentBOImpl() throws Exception {
    }

    @Override
    public boolean saveAppointment(AppointmentDTO dto) throws Exception {
        Appointment appointment = new Appointment();
        appointment.setId(dto.getId());
        appointment.setStatus(dto.getStatus());
        appointment.setStartTime(dto.getStartTime());
        appointment.setEndTime(dto.getEndTime());

        Optional<Patient> optionalPatient = patientDaoImpl.findByPK(dto.getPatient_id());
        session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        if (optionalPatient.isEmpty()) {
            transaction.rollback();
            return false;
        }
        session.close();
        System.out.println("bo : second ");
        Patient patient = optionalPatient.get();
        appointment.setPatient(patient);

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
        appointment.setTherapy_session(therapySession);

        return appointmentDaoImpl.save(appointment);
    }

    @Override
    public boolean updateAppointment(AppointmentDTO dto) throws Exception {
        Appointment appointment = new Appointment();
        appointment.setId(dto.getId());
        appointment.setStatus(dto.getStatus());
        appointment.setStartTime(dto.getStartTime());
        appointment.setEndTime(dto.getEndTime());

        Optional<Patient> optionalPatient = patientDaoImpl.findByPK(dto.getPatient_id());
        session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        if (optionalPatient.isEmpty()) {
            transaction.rollback();
            return false;
        }
        session.close();
        System.out.println("bo : second ");
        Patient patient = optionalPatient.get();
        appointment.setPatient(patient);

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
        appointment.setTherapy_session(therapySession);

        return appointmentDaoImpl.update(appointment);
    }

    @Override
    public boolean deleteAppointment(String id) throws Exception {
        return appointmentDaoImpl.delete(id);
    }

    @Override
    public List<AppointmentDTO> getAllAppointment() throws Exception {
        List<Appointment> allAppointements=appointmentDaoImpl.getAll();
        List<AppointmentDTO> appointmentDTOS=new ArrayList<>();
        for (Appointment appointment:allAppointements) {
            AppointmentDTO appointmentDTO=new AppointmentDTO();
            appointmentDTO.setId(appointment.getId());
            appointmentDTO.setStatus(appointment.getStatus());
            appointmentDTO.setStartTime(appointment.getStartTime());
            appointmentDTO.setEndTime(appointment.getEndTime());
            appointmentDTO.setPatient_id(appointment.getPatient().getId());
            appointmentDTO.setSession_id(appointment.getTherapy_session().getId());
            appointmentDTOS.add(appointmentDTO);
        }
        return appointmentDTOS;
    }

    @Override
    public String getNextAppointmentId() throws SQLException {
        return appointmentDaoImpl.getNextId();
    }

    @Override
    public Optional<Appointment> findByPK(String pk) {
        return Optional.empty();
    }

    @Override
    public List<TherapySession> findAllByCustomer(String customer) throws Exception {
        return appointmentDaoImpl.findAllByCustomer(customer);
    }

    @Override
    public boolean UpdateAppointment(AppointmentDTO dto, Session session) throws Exception {
        Appointment appointment = new Appointment();
        appointment.setId(dto.getId());
        appointment.setStatus(dto.getStatus());
        appointment.setStartTime(dto.getStartTime());
        appointment.setEndTime(dto.getEndTime());

        Optional<Patient> optionalPatient = patientDaoImpl.findByPK(dto.getPatient_id());
        session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        if (optionalPatient.isEmpty()) {
            transaction.rollback();
            return false;
        }
        session.close();
        System.out.println("bo : second ");
        Patient patient = optionalPatient.get();
        appointment.setPatient(patient);

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
        appointment.setTherapy_session(therapySession);
        return appointmentDaoImpl.UpdateAppointment(appointment,session);


    }

}
