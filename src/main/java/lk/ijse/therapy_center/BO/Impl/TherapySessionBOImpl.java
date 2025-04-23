package lk.ijse.therapy_center.BO.Impl;

//import lk.ijse.therapy_center.BO.TherapistForProgrammeBO;
import lk.ijse.therapy_center.BO.TherapySessionBO;
import lk.ijse.therapy_center.Config.ConfigurationFactory;
import lk.ijse.therapy_center.DAO.DAOFactory;
import lk.ijse.therapy_center.DAO.Impl.TherapistDaoImpl;
import lk.ijse.therapy_center.DAO.Impl.TherapySessionDaoImpl;
import lk.ijse.therapy_center.DAO.Impl.TherapistProgrammeDaoImpl;
//import lk.ijse.therapy_center.DTO.Therapist_for_programmeDTO;
import lk.ijse.therapy_center.DTO.TherapySessionDTO;
import lk.ijse.therapy_center.Entity.Therapist;
//import lk.ijse.therapy_center.Entity.Therapist_for_programme;
import lk.ijse.therapy_center.Entity.TherapySession;
import lk.ijse.therapy_center.Entity.Therapy_Programme;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapySessionBOImpl implements TherapySessionBO {
    TherapistDaoImpl therapistDao=(TherapistDaoImpl) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.THERAPIST);
    TherapistProgrammeDaoImpl therapistProgrammeDaoImpl=(TherapistProgrammeDaoImpl)DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.THERAPY_PROGRAMME);
    TherapySessionDaoImpl sessionDaoImpl =(TherapySessionDaoImpl)DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.THERAPIST_FOR_PROGRAMME);
    Session session=null;
    public TherapySessionBOImpl() throws Exception {
    }

    @Override
    public boolean saveTherapistForProgramme(TherapySessionDTO dto) throws Exception {

        TherapySession therapist_for_programme = new TherapySession();
        therapist_for_programme.setStartTime(dto.getStartTime());
        therapist_for_programme.setDate(dto.getDate());
        therapist_for_programme.setEndTime(dto.getEndTime());
        therapist_for_programme.setId(dto.getId());

        Optional<Therapist> optionalTherapist = therapistDao.findByPK(dto.getTherapist_id());
         session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        if (optionalTherapist.isEmpty()) {
            transaction.rollback();
            return false;
        }
        session.close();
        System.out.println("bo : second ");
        Therapist therapist = optionalTherapist.get();
        therapist_for_programme.setTherapist(therapist);

        Optional<Therapy_Programme> optionalTherapyProgramme = therapistProgrammeDaoImpl.findByPK(dto.getTherapy_programme_id());
         session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction1 = session.beginTransaction();
        if (optionalTherapyProgramme.isEmpty()) {
            transaction1.rollback();
            return false;
        }

        Therapy_Programme therapistProgramme = optionalTherapyProgramme.get();
        therapist_for_programme.setTherapy_programme(therapistProgramme);

//        System.out.println("bo :"+therapist_for_programme.toString());
        return sessionDaoImpl.save(therapist_for_programme);
    }

    @Override
    public boolean updateTherapistForProgramme(TherapySessionDTO dto) throws Exception {
        System.out.println("bo : started");
        TherapySession therapist_for_programme = new TherapySession();
        therapist_for_programme.setStartTime(dto.getStartTime());
        therapist_for_programme.setDate(dto.getDate());
        therapist_for_programme.setEndTime(dto.getEndTime());
        therapist_for_programme.setId(dto.getId());

        Optional<Therapist> optionalTherapist = therapistDao.findByPK(dto.getTherapist_id());
         session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        if (optionalTherapist.isEmpty()) {
            transaction.rollback();
            return false;
        }
        session.close();
        System.out.println("bo : second");
        Therapist therapist = optionalTherapist.get();
        therapist_for_programme.setTherapist(therapist);

        Optional<Therapy_Programme> optionalTherapyProgramme = therapistProgrammeDaoImpl.findByPK(dto.getTherapy_programme_id());
        session = ConfigurationFactory.getInstance().getSessionFactory();
        Transaction transaction1 = session.beginTransaction();
        if (optionalTherapyProgramme.isEmpty()) {
            transaction1.rollback();
            return false;
        }
        System.out.println("bo : third");
        Therapy_Programme therapistProgramme = optionalTherapyProgramme.get();
        therapist_for_programme.setTherapy_programme(therapistProgramme);
        return sessionDaoImpl.update(therapist_for_programme);
    }

    @Override
    public boolean deleteTherapistForProgramme(String id) throws Exception {
        return sessionDaoImpl.delete(id);
    }

    @Override
    public ArrayList<TherapySessionDTO> getAllTherapistForProgramme() throws Exception {
        List<TherapySession> all = sessionDaoImpl.getAll();
        ArrayList<TherapySessionDTO> dtos = new ArrayList<>();
        for (TherapySession session : all) {
            TherapySessionDTO dto = new TherapySessionDTO();
            dto.setId(session.getId());
            dto.setStartTime(session.getStartTime());
            dto.setDate(session.getDate());
            dto.setEndTime(session.getEndTime());
            dto.setTherapist_id(session.getTherapist().getTherapist_id());
            dto.setTherapy_programme_id(session.getTherapy_programme().getId());
            dtos.add(dto);
        }
        System.out.println("Size "+dtos.size());
        return dtos;
    }

    @Override
    public String getNextTherapistForProgrammeId() throws SQLException {
        return sessionDaoImpl.getNextId();
    }

    @Override
    public List<TherapySessionDTO> getAllById(String id) throws Exception {
        List<TherapySession> allById = sessionDaoImpl.getAllById(id);
        List<TherapySessionDTO> therapistForProgrammeDTOList = new ArrayList<>();
        for (TherapySession therapistForProgramme : allById) {
            TherapySessionDTO therapist_for_programmeDTO = new TherapySessionDTO();
            therapist_for_programmeDTO.setId(therapistForProgramme.getId());
            therapist_for_programmeDTO.setStartTime(therapistForProgramme.getStartTime());
            therapist_for_programmeDTO.setEndTime(therapistForProgramme.getEndTime());
            therapist_for_programmeDTO.setDate(therapistForProgramme.getDate());
            therapist_for_programmeDTO.setTherapist_id(therapistForProgramme.getTherapist().getTherapist_id());
            therapist_for_programmeDTO.setTherapy_programme_id(therapistForProgramme.getTherapy_programme().getId());
            therapistForProgrammeDTOList.add(therapist_for_programmeDTO);
        }
        return therapistForProgrammeDTOList;
    }

    @Override
    public Optional<TherapySession> findByPK(String pk) {
        return sessionDaoImpl.findByPK(pk);
    }
}
