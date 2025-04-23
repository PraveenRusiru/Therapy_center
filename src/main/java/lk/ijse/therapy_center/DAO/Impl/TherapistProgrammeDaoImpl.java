package lk.ijse.therapy_center.DAO.Impl;

import lk.ijse.therapy_center.Config.ConfigurationFactory;
import lk.ijse.therapy_center.DAO.TherapistProgrammeDAO;
import lk.ijse.therapy_center.Entity.Therapist;
import lk.ijse.therapy_center.Entity.Therapy_Programme;
import lk.ijse.therapy_center.Entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TherapistProgrammeDaoImpl implements TherapistProgrammeDAO {
    Session session=null;
    @Override
    public boolean save(Therapy_Programme entity) throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
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
    public boolean update(Therapy_Programme entity) throws Exception {
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
            session.remove(session.get(Therapy_Programme.class, id));
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
    public List<Therapy_Programme> getAll() throws Exception {
        session= ConfigurationFactory.getInstance().getSessionFactory();
        List<Therapy_Programme> therapy_programmes = session.createQuery("from Therapy_Programme", Therapy_Programme.class).list();
        return therapy_programmes;
    }

    @Override
    public String getNextId() throws SQLException {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        String lastId = (String) session.createQuery("SELECT thp.id FROM Therapy_Programme thp ORDER BY thp.id DESC")
                .setMaxResults(1)
                .uniqueResult();

        if (lastId == null) {
            return "MT001";
        }

        int num = Integer.parseInt(lastId.substring(2));
        num++;
        return String.format("MT%03d", num);
    }

    @Override
    public Optional<Therapy_Programme> findByPK(String pk) {
        session = ConfigurationFactory.getInstance().getSessionFactory();
        Therapy_Programme therapyProgramme = session.get(Therapy_Programme.class, pk);
//        System.out.println(therapyProgramme.toString());
        session.close();
        if (therapyProgramme == null) {
            return Optional.empty();
        }
        return Optional.of(therapyProgramme);
    }
}
