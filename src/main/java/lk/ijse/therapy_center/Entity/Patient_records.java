package lk.ijse.therapy_center.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient_records {
    @Id
    private String record_id;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String record;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
