package lk.ijse.therapy_center.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @Column(name = "apppointment_id")
    private String id;
    private String status;
    @ManyToOne
    @JoinColumn(name = "session_id")
    private TherapySession therapy_session;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private String startTime;
    private String endTime;
}
