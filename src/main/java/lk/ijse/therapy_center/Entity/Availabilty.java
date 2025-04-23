package lk.ijse.therapy_center.Entity;


import jakarta.persistence.*;
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
@Entity
@Table
public class Availabilty {
    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availablity_id")
    private int id;
    private String availableDays;
    private String startTime;
    private String endTime;

}
