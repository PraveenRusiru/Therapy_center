package lk.ijse.therapy_center.BO.Impl;

import lk.ijse.therapy_center.BO.AvailabiltyBO;
import lk.ijse.therapy_center.BO.BOFactory;
import lk.ijse.therapy_center.Config.ConfigurationFactory;
import lk.ijse.therapy_center.DAO.DAOFactory;
import lk.ijse.therapy_center.DAO.Impl.AvailablityDaoImpl;
import lk.ijse.therapy_center.DAO.Impl.TherapistDaoImpl;
import lk.ijse.therapy_center.DTO.AvailabiltyDTO;
import lk.ijse.therapy_center.Entity.Availabilty;
import lk.ijse.therapy_center.Entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AvailabiltyBOImpl implements AvailabiltyBO {
    AvailablityDaoImpl availablityDao = (AvailablityDaoImpl) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.AVAILABILITY);
    TherapistDaoImpl therapistDao=(TherapistDaoImpl)DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.THERAPIST);
    public AvailabiltyBOImpl() throws Exception {
    }

    @Override
    public List<AvailabiltyDTO> getAllAvaialbilitiesById(String id) throws Exception {
        List<Availabilty> availabiltyList=availablityDao.getAllById(id);
        List<AvailabiltyDTO> availabiltyDTOS=new ArrayList<>();
        for (Availabilty availabilty :availabiltyList){
            AvailabiltyDTO availabiltyDTO=new AvailabiltyDTO();
            availabiltyDTO.setId(availabilty.getId());
            availabiltyDTO.setTherapist_id(availabilty.getTherapist().getTherapist_id());
            availabiltyDTO.setAvailableDays(availabilty.getAvailableDays());
            availabiltyDTO.setStartTime(availabilty.getStartTime());
            availabiltyDTO.setEndTime(availabilty.getEndTime());
            availabiltyDTOS.add(availabiltyDTO);
        }
        return availabiltyDTOS;
    }

    @Override
    public String getNextAvaialbilityId() throws SQLException {
        return "";
    }

    @Override
    public List<AvailabiltyDTO> getAllAvailabilties() throws Exception {
        return List.of();
    }

    @Override
    public boolean deleteAvaialbility(String id) throws Exception {
        return availablityDao.delete(id);
    }

    @Override
    public boolean updateAvaialbility(AvailabiltyDTO dto) throws Exception {
        Availabilty availabilty=new Availabilty();
        availabilty.setId(dto.getId());
        availabilty.setAvailableDays(dto.getAvailableDays());
        availabilty.setStartTime(dto.getStartTime());
        availabilty.setEndTime(dto.getEndTime());
//        availabilty.se

        Optional<Therapist> optionalCustomer = therapistDao.findByPK(dto.getTherapist_id());
        Session session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        if (optionalCustomer.isEmpty()) {
            transaction.rollback();
            return false;
        }

        Therapist therapist = optionalCustomer.get();
        availabilty.setTherapist(therapist);
        return availablityDao.update(availabilty);
    }

    @Override
    public boolean saveAvaialbility(AvailabiltyDTO dto) throws Exception {
        Availabilty availabilty=new Availabilty();
        availabilty.setAvailableDays(dto.getAvailableDays());
        availabilty.setStartTime(dto.getStartTime());
        availabilty.setEndTime(dto.getEndTime());

        Optional<Therapist> optionalCustomer = therapistDao.findByPK(dto.getTherapist_id());
        Session session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        if (optionalCustomer.isEmpty()) {
            transaction.rollback();
            return false;
        }

        Therapist therapist = optionalCustomer.get();
        availabilty.setTherapist(therapist);
        return availablityDao.save(availabilty);
    }

    @Override
    public List<AvailabiltyDTO> checkAvailability(AvailabiltyDTO dto) throws Exception {
        Availabilty availabilty=new Availabilty();
        availabilty.setAvailableDays(dto.getAvailableDays());
        availabilty.setStartTime(dto.getStartTime());
        availabilty.setEndTime(dto.getEndTime());
        List<Availabilty> availabiltyList = availablityDao.checkAvailability(availabilty);
        List<AvailabiltyDTO> availabiltyDTOS=new ArrayList<>();
        for (Availabilty availabilty1 : availabiltyList) {
            AvailabiltyDTO availabiltyDTO=new AvailabiltyDTO();
            availabiltyDTO.setTherapist_id(availabilty1.getTherapist().getTherapist_id());
            availabiltyDTO.setStartTime(availabilty1.getStartTime());
            availabiltyDTO.setEndTime(availabilty1.getEndTime());
            availabiltyDTOS.add(availabiltyDTO);
        }
        return availabiltyDTOS;
    }

    @Override
    public int getId(AvailabiltyDTO dto) throws Exception {
        Availabilty availabilty=new Availabilty();
        availabilty.setAvailableDays(dto.getAvailableDays());
        availabilty.setStartTime(dto.getStartTime());
        availabilty.setEndTime(dto.getEndTime());
        availabilty.setId(dto.getId());
        Optional<Therapist> optionalCustomer = therapistDao.findByPK(dto.getTherapist_id());
        Session session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        if (optionalCustomer.isEmpty()) {
            transaction.rollback();
//            return false;
        }

        Therapist therapist = optionalCustomer.get();
        availabilty.setTherapist(therapist);

        return availablityDao.getId(availabilty);
    }


}
