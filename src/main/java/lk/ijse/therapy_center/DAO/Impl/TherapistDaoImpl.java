package lk.ijse.therapy_center.DAO.Impl;

import lk.ijse.therapy_center.Config.ConfigurationFactory;
import lk.ijse.therapy_center.DAO.TherapistDAO;
import lk.ijse.therapy_center.Entity.Therapist;
//import lk.ijse.therapy_center.Entity.Therapist_for_programme;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TherapistDaoImpl implements TherapistDAO {
   Session session=null;
    @Override
    public boolean save(Therapist entity) throws Exception {
        session= ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
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
    public boolean update(Therapist entity) throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
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
    public boolean delete(String id) throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        try{
            session.remove(session.get(Therapist.class, id));
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
    public List<Therapist> getAll() throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        List<Therapist> therapists = session.createQuery("from Therapist", Therapist.class).list();
        return therapists;
    }

    @Override
    public String getNextId() throws SQLException {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        String lastId = (String) session.createQuery("SELECT th.id FROM Therapist th ORDER BY th.id DESC")
                .setMaxResults(1)
                .uniqueResult();

        if (lastId == null) {
            return "THRPST001";
        }

        int num = Integer.parseInt(lastId.substring(6));
        num++;

        return String.format("THRPST%03d", num);

    }

    @Override
    public Optional<Therapist> findByPK(String pk) {
        session = ConfigurationFactory.getInstance().getSessionFactory();
        Therapist therapist = session.get(Therapist.class, pk);
        session.close();
        if (therapist == null) {
            return Optional.empty();
        }
        return Optional.of(therapist);
    }
}
