package lk.ijse.therapy_center.BO.Impl;

import lk.ijse.therapy_center.BO.TherapistBO;
import lk.ijse.therapy_center.DAO.DAOFactory;
import lk.ijse.therapy_center.DAO.TherapistDAO;
import lk.ijse.therapy_center.DTO.TherapistDTO;
import lk.ijse.therapy_center.Entity.Therapist;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapistBoImpl implements TherapistBO {

    TherapistDAO therapistDAO= (TherapistDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.THERAPIST);

    public TherapistBoImpl() throws Exception {
    }

    @Override
    public boolean saveTherapist(TherapistDTO dto) throws Exception {
        Therapist therapist = new Therapist();
        therapist.setName(dto.getName());
        therapist.setTherapist_id(dto.getTherapist_id());
        therapist.setPhone(dto.getPhone());
        therapist.setEmail(dto.getEmail());
        therapist.setSpecilization(dto.getSpecilization());

        return therapistDAO.save(therapist);
    }

    @Override
    public boolean updateTherapist(TherapistDTO dto) throws Exception {
        Therapist therapist = new Therapist();
        therapist.setName(dto.getName());
        therapist.setTherapist_id(dto.getTherapist_id());
        therapist.setPhone(dto.getPhone());
        therapist.setEmail(dto.getEmail());
        therapist.setSpecilization(dto.getSpecilization());
        return therapistDAO.update(therapist);
    }

    @Override
    public boolean deleteTherapist(String id) throws Exception {
        return therapistDAO.delete(id);
    }

    @Override
    public List<TherapistDTO> getAllTherapist() throws Exception {
        List<Therapist> therapists = therapistDAO.getAll();
        List<TherapistDTO> dtos = new ArrayList<>();
        for (Therapist therapist : therapists) {
            TherapistDTO dto = new TherapistDTO();
            dto.setTherapist_id(therapist.getTherapist_id());
            dto.setName(therapist.getName());
            dto.setPhone(therapist.getPhone());
            dto.setEmail(therapist.getEmail());
            dto.setSpecilization(therapist.getSpecilization());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public String getNextTherapistId() throws SQLException {
        return therapistDAO.getNextId();
    }
}
