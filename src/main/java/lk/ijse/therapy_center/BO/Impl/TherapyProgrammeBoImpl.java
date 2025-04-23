package lk.ijse.therapy_center.BO.Impl;

import lk.ijse.therapy_center.BO.TherapistProgrammeBO;
import lk.ijse.therapy_center.Config.ConfigurationFactory;
import lk.ijse.therapy_center.DAO.DAOFactory;
import lk.ijse.therapy_center.DAO.Impl.TherapistProgrammeDaoImpl;
import lk.ijse.therapy_center.DTO.Therapy_ProgrammeDTO;
import lk.ijse.therapy_center.Entity.Therapy_Programme;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapyProgrammeBoImpl implements TherapistProgrammeBO {
    TherapistProgrammeDaoImpl therapistProgrammeDaoImpl=(TherapistProgrammeDaoImpl) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.THERAPY_PROGRAMME);

    public TherapyProgrammeBoImpl() throws Exception {
    }

    @Override
    public boolean saveProgramme(Therapy_ProgrammeDTO dto) throws Exception {
        Therapy_Programme therapy_programme=new Therapy_Programme();
        therapy_programme.setId(dto.getId());
        therapy_programme.setProgramme_name(dto.getProgramme_name());
        therapy_programme.setProgramme_description(dto.getProgramme_description());
        therapy_programme.setFee(dto.getFee());
        therapy_programme.setDuration(dto.getDuration());
        return therapistProgrammeDaoImpl.save(therapy_programme);
    }

    @Override
    public boolean updateProgramme(Therapy_ProgrammeDTO dto) throws Exception {
        Therapy_Programme therapy_programme=new Therapy_Programme();
        therapy_programme.setId(dto.getId());
        therapy_programme.setProgramme_name(dto.getProgramme_name());
        therapy_programme.setProgramme_description(dto.getProgramme_description());
        therapy_programme.setFee(dto.getFee());
        therapy_programme.setDuration(dto.getDuration());
        return therapistProgrammeDaoImpl.update(therapy_programme);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return therapistProgrammeDaoImpl.delete(id);
    }

    @Override
    public List<Therapy_ProgrammeDTO> getAllProgramme() throws Exception {
        List<Therapy_Programme> therapy_programmes = therapistProgrammeDaoImpl.getAll();
        List<Therapy_ProgrammeDTO> therapy_programmeDTOS=new ArrayList<>();
        for (Therapy_Programme therapy_programme : therapy_programmes) {
            Therapy_ProgrammeDTO dto=new Therapy_ProgrammeDTO();
            dto.setProgramme_name(therapy_programme.getProgramme_name());
            dto.setProgramme_description(therapy_programme.getProgramme_description());
            dto.setFee(therapy_programme.getFee());
            dto.setDuration(therapy_programme.getDuration());
            dto.setId(therapy_programme.getId());
            therapy_programmeDTOS.add(dto);
        }
        return therapy_programmeDTOS;
    }

    @Override
    public String getNextProgrammeId() throws SQLException {
        return therapistProgrammeDaoImpl.getNextId();
    }
}
