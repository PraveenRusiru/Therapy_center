package lk.ijse.therapy_center.BO.Impl;

import lk.ijse.therapy_center.BO.BOFactory;
import lk.ijse.therapy_center.BO.PatientBO;
import lk.ijse.therapy_center.DAO.DAOFactory;
import lk.ijse.therapy_center.DAO.Impl.PatientDaoImpl;
import lk.ijse.therapy_center.DAO.PatientDAO;
import lk.ijse.therapy_center.DTO.PatientDTO;
import lk.ijse.therapy_center.Entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientBOImpl implements PatientBO {
    PatientDaoImpl patientDaoImpl =(PatientDaoImpl) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.PATIENT);

    public PatientBOImpl() throws Exception {
    }

    @Override
    public boolean savePatient(PatientDTO dto) throws Exception {
        Patient patient = new Patient();
        patient.setId(dto.getId());
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setContact_number(dto.getContact_number());
        patient.setMedical_history(dto.getMedical_history());
        patient.setRegistration_date(dto.getRegistration_date());
        return patientDaoImpl.save(patient);
    }

    @Override
    public boolean updatePatient(PatientDTO dto) throws Exception {
        Patient patient = new Patient();
        patient.setId(dto.getId());
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setContact_number(dto.getContact_number());
        patient.setMedical_history(dto.getMedical_history());
        patient.setRegistration_date(dto.getRegistration_date());
        return patientDaoImpl.update(patient);
    }

    @Override
    public boolean deletePatient(String id) throws Exception {
        return patientDaoImpl.delete(id);
    }

    @Override
    public List<PatientDTO> getAllPatient() throws Exception {
        List<Patient> patientList = patientDaoImpl.getAll();
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient patient : patientList) {
            PatientDTO patientDTO = new PatientDTO();
            patientDTO.setId(patient.getId());
            patientDTO.setName(patient.getName());
            patientDTO.setEmail(patient.getEmail());
            patientDTO.setContact_number(patient.getContact_number());
            patientDTO.setMedical_history(patient.getMedical_history());
            patientDTO.setRegistration_date(patient.getRegistration_date());
            patientDTOList.add(patientDTO);
        }
        return patientDTOList;
    }

    @Override
    public String getNextPatientId() throws SQLException {
        return patientDaoImpl.getNextId();
    }

    @Override
    public Optional<Patient> findByPK(String pk) {
        return patientDaoImpl.findByPK(pk);
    }
}
