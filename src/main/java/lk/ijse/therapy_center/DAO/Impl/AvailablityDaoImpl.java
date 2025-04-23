package lk.ijse.therapy_center.DAO.Impl;

import lk.ijse.therapy_center.Config.ConfigurationFactory;
import lk.ijse.therapy_center.DAO.AvailablityDAO;
import lk.ijse.therapy_center.DTO.AvailabiltyDTO;
import lk.ijse.therapy_center.Entity.Availabilty;
import lk.ijse.therapy_center.Entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AvailablityDaoImpl implements AvailablityDAO {
   Session session=null;
    @Override
    public boolean save(Availabilty entity) throws Exception {
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
    public boolean update(Availabilty entity) throws Exception {
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
            session.remove(session.get(Availabilty.class, Integer.parseInt(id)));
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
    public List<Availabilty> getAll() throws Exception {
        return List.of();
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public Optional<Availabilty> findByPK(String pk) {
        session = ConfigurationFactory.getInstance().getSessionFactory();
        Availabilty availabilty = session.get(Availabilty.class, pk);
        session.close();
        if (availabilty == null) {
            return Optional.empty();
        }
        return Optional.of(availabilty);
    }

    @Override
    public List<Availabilty> getAllById(String id) throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction=session.beginTransaction();
        List<Availabilty> availabiltyListList = session.createQuery(
                        "FROM Availabilty A WHERE A.therapist.therapist_id = :id", Availabilty.class)
                .setParameter("id", id)
                .getResultList();

        transaction.commit();
        session.close();

        return availabiltyListList;
    }

    @Override
    public List<Availabilty> checkAvailability(Availabilty entity) throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction=session.beginTransaction();
        List<Availabilty> availabiltyListList = session.createQuery(
                        "FROM Availabilty a WHERE a.availableDays = :day " +
                                "AND a.startTime BETWEEN :startTime AND :endTime", Availabilty.class)
                .setParameter("day", entity.getAvailableDays())
                .setParameter("startTime", entity.getStartTime())
                .setParameter("endTime", entity.getEndTime())
                .getResultList();
        transaction.commit();
        session.close();
        return availabiltyListList;
    }

    @Override
    public int getId(Availabilty entity) throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction=session.beginTransaction();
        Availabilty availabiltyEntity = session.createQuery(
                        "FROM Availabilty a WHERE a.therapist.id = :therapistId AND a.startTime = :startTime AND a.availableDays = :day",
                        Availabilty.class)
                .setParameter("day", entity.getAvailableDays())
                .setParameter("startTime", entity.getStartTime())
                .setParameter("therapistId", entity.getTherapist().getTherapist_id())
                .uniqueResult();
        transaction.commit();
        session.close();
        return availabiltyEntity!=null?availabiltyEntity.getId():-1;
    }
}
