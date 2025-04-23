package lk.ijse.therapy_center.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "session")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TherapySession {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;
    @ManyToOne
    @JoinColumn(name = "therapy_programme_id")
    private Therapy_Programme therapy_programme;

    private String date;
    private String startTime;//time
    private String endTime;
}
