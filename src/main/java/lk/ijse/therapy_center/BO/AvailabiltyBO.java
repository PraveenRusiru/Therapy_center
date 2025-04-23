package lk.ijse.therapy_center.BO;

import lk.ijse.therapy_center.DTO.AvailabiltyDTO;
import lk.ijse.therapy_center.Entity.Availabilty;

import java.sql.SQLException;
import java.util.List;

public interface AvailabiltyBO extends SuperBO {
    public List<AvailabiltyDTO> getAllAvaialbilitiesById(String id) throws Exception;
    public String getNextAvaialbilityId() throws SQLException;
    public List<AvailabiltyDTO> getAllAvailabilties() throws Exception ;
    public boolean deleteAvaialbility(String id) throws Exception ;
    public boolean updateAvaialbility(AvailabiltyDTO dto) throws Exception;
    public boolean saveAvaialbility(AvailabiltyDTO dto) throws Exception ;
    public List<AvailabiltyDTO> checkAvailability(AvailabiltyDTO dto) throws Exception ;
    public int getId(AvailabiltyDTO dto) throws Exception;
}
