package lk.ijse.therapy_center.DAO.Impl;

import lk.ijse.therapy_center.Config.ConfigurationFactory;
import lk.ijse.therapy_center.DAO.PaymentDAO;
import lk.ijse.therapy_center.DTO.PaymentDTO;
import lk.ijse.therapy_center.Entity.Appointment;
import lk.ijse.therapy_center.Entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl implements PaymentDAO {
    Session session=null;
    @Override
    public boolean save(Payment entity) throws Exception {
        session= ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction =session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            System.out.println("save successful");
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
    public boolean update(Payment entity) throws Exception {
        session= ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction =session.beginTransaction();
        try {
            session.merge(entity);
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
            session.remove(session.get(Payment.class, id));
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
    public List<Payment> getAll() throws Exception {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        List<Payment> payments = session.createQuery("from Payment ", Payment.class).list();
        return payments;
    }

    @Override
    public String getNextId() throws SQLException {
        session=ConfigurationFactory.getInstance().getSessionFactory();
        String lastId = (String) session.createQuery("SELECT a.id FROM Payment a ORDER BY a.id DESC")
                .setMaxResults(1)
                .uniqueResult();

        if (lastId == null) {
            return "Pay001";
        }

        int num = Integer.parseInt(lastId.substring(3));
        num++;

        return String.format("Pay%03d", num);
    }

    @Override
    public Optional<Payment> findByPK(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean savePayments(Session session, Payment entity) throws Exception {
            try {
                session.merge(entity);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                return false;
            }
    }
}
