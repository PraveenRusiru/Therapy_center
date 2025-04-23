package lk.ijse.therapy_center.DAO.Impl;

import lk.ijse.therapy_center.Config.ConfigurationFactory;
import lk.ijse.therapy_center.DAO.AppointmentDAO;
import lk.ijse.therapy_center.Entity.Appointment;
import lk.ijse.therapy_center.Entity.Therapist;
import lk.ijse.therapy_center.Entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AppointmentDaoImpl implements AppointmentDAO {
    Session session=null;
    @Override
    public boolean save(Appointment entity) throws Exception {
        session= ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction =session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(Appointment entity) throws Exception {
       session=ConfigurationFactory.getInstance().getSessionFactory();
       Transaction transaction =session.beginTransaction();
       try {
           session.update(entity);
           transaction.commit();
           return true;
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println(e.getMessage());
           return false;
       }finally {
           session.close();
       }

    }

    @Override
    public boolean delete(String id) throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction =session.beginTransaction();
        try {
            session.remove(session.get(Appointment.class, id));
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }finally {
            session.close();
        }

    }

    @Override
    public List<Appointment> getAll() throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        List<Appointment> appointments = session.createQuery("from Appointment ", Appointment.class).list();
        return appointments;
    }

    @Override
    public String getNextId() throws SQLException {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        String lastId = (String) session.createQuery("SELECT a.id FROM Appointment a ORDER BY a.id DESC")
                .setMaxResults(1)
                .uniqueResult();

        if (lastId == null) {
            return "A001";
        }

        int num = Integer.parseInt(lastId.substring(1));
        num++;

        return String.format("A%03d", num);
    }

    @Override
    public Optional<Appointment> findByPK(String pk) {

        return Optional.empty();
    }

    @Override
    public List<TherapySession> findAllByCustomer(String customer) throws Exception {
        session = ConfigurationFactory.getInstance().getSessionFactory();
        List<TherapySession> sessionIds = session.createQuery(
                        "SELECT a.therapy_session FROM Appointment a WHERE a.patient.id = :patientId AND a.status = :status",
                        TherapySession.class)
                .setParameter("patientId", customer)
                .setParameter("status", "Unpaid")
                .getResultList();

        return sessionIds;
    }

    @Override
    public boolean UpdateAppointment(Appointment appointment, Session session) throws Exception {
        int updatedRows = session.createQuery(
                        "UPDATE Appointment a SET a.status = :newStatus WHERE a.id = :appointmentId")
                .setParameter("newStatus", appointment.getStatus())
                .setParameter("appointmentId", appointment.getId())
                .executeUpdate();

        return updatedRows>1?true:false;
    }

}
