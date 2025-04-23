package lk.ijse.therapy_center.DTO.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientTM {
    private String  id;
    private String name;
    private String contact_number;
    private String email;
    private String medical_history;
    private Date registration_date;
}
