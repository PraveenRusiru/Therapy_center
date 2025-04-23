package lk.ijse.therapy_center.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TherapySessionDTO {
    private String id;
    private String therapist_id;
    private String therapy_programme_id;
    private String date;
    private String startTime;
    private String endTime;
}
