package lk.ijse.therapy_center.DAO.Impl;

import lk.ijse.therapy_center.Config.ConfigurationFactory;
import lk.ijse.therapy_center.DAO.TherapySessionDAO;
//import lk.ijse.therapy_center.Entity.Therapist_for_programme;
import lk.ijse.therapy_center.Entity.Therapist;
import lk.ijse.therapy_center.Entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TherapySessionDaoImpl implements TherapySessionDAO {
   Session session=null;
    @Override
    public boolean save(TherapySession entity) throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        Transaction tx=session.beginTransaction();
        try {
            session.persist(entity);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
            return false;
        }finally {
            session.close();
        }

    }

    @Override
    public boolean update(TherapySession entity) throws Exception {
        session= ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
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
            session.remove(session.get(TherapySession.class, Integer.parseInt(id)));
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
    public List<TherapySession> getAll() throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        List<TherapySession> therapySessions = session.createQuery("from TherapySession ", TherapySession.class).list();
        System.out.println(therapySessions);
        return therapySessions;

    }

    @Override
    public String getNextId() throws SQLException {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        String lastId = (String) session.createQuery("SELECT S.id FROM TherapySession S ORDER BY S.id DESC")
                .setMaxResults(1)
                .uniqueResult();

        if (lastId == null) {
            return "S001";
        }

        int num = Integer.parseInt(lastId.substring(1));
        num++;

        return String.format("S%03d", num);
    }

    @Override
    public Optional<TherapySession> findByPK(String pk) {
        session = ConfigurationFactory.getInstance().getSessionFactory();
        TherapySession therapistForProgramme = session.get(TherapySession.class, pk);
        System.out.println("Therapy session "+therapistForProgramme);
        session.close();
        if (therapistForProgramme == null) {
            return Optional.empty();
        }
        return Optional.of(therapistForProgramme);
    }

    @Override
    public List<TherapySession> getAllById(String id) throws Exception {
        session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        List<TherapySession> resultList = session.createQuery("FROM TherapySession TP WHERE TP.therapist.therapist_id = :id", TherapySession.class)
                .setParameter("id", id)
                .getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }
}
