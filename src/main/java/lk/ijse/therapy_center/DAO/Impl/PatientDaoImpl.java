package lk.ijse.therapy_center.DAO.Impl;

import lk.ijse.therapy_center.Config.ConfigurationFactory;
import lk.ijse.therapy_center.DAO.PatientDAO;
import lk.ijse.therapy_center.Entity.Patient;
import lk.ijse.therapy_center.Entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PatientDaoImpl implements PatientDAO {
    Session session=null;
    @Override
    public boolean save(Patient entity) throws Exception {
        session= ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction=session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }

    }

    @Override
    public boolean update(Patient entity) throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction=session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction=session.beginTransaction();
        try {
            session.remove(session.get(Patient.class, id));
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Patient> getAll() throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        List<Patient> patients= (List<Patient>) session.createQuery("from Patient", Patient.class).list();
        return patients;
    }

    @Override
    public String getNextId() throws SQLException {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        String lastId = (String) session.createQuery("SELECT P.id FROM Patient P ORDER BY P.id DESC")
                .setMaxResults(1)
                .uniqueResult();

        if (lastId == null) {
            return "P001";
        }

        int num = Integer.parseInt(lastId.substring(1));
        num++;

        return String.format("P%03d", num);
    }

    @Override
    public Optional<Patient> findByPK(String pk) {
        session = ConfigurationFactory.getInstance().getSessionFactory();
        Patient patient = session.get(Patient.class, pk);

        session.close();
        if (patient == null) {
            return Optional.empty();
        }
        return Optional.of(patient);
    }
}
