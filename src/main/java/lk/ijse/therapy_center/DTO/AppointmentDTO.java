package lk.ijse.therapy_center.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

    private String id;
    private String status;
    private String patient_id;
    private String session_id;
    private String startTime;
    private String endTime;
}
