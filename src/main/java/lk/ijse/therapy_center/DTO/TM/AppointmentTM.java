package lk.ijse.therapy_center.DTO.TM;

import lk.ijse.therapy_center.DTO.TherapySessionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentTM {
    private String id;
    private String patient_id;
    private String session_id;
    private String startTime;
    private String endTime;
    private String status;

}
