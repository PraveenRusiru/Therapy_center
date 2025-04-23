package lk.ijse.therapy_center.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class TherapistDTO {

    private String therapist_id;
    private String name;
    private String email;
    private String phone;
    private String specilization;
    private List<AvailabiltyDTO> availabilties;
}
