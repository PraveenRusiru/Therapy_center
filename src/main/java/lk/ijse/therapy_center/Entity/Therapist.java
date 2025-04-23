package lk.ijse.therapy_center.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.DayOfWeek;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "therapist")
public class Therapist {
    @Id
    private String therapist_id;
    private String name;
    private String email;
    private String phone;
    private String specilization;
    @ElementCollection
    @CollectionTable(name = "therapist_availability", joinColumns = @JoinColumn(name = "therapist_id"))
    private List<Availabilty> availabilties;
}
