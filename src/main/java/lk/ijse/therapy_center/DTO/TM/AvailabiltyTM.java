package lk.ijse.therapy_center.DTO.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.DayOfWeek;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailabiltyTM {
    private int id;
    private String availableDays;
    private String startTime;
    private String endTime;
    private String therapist_id;
}
