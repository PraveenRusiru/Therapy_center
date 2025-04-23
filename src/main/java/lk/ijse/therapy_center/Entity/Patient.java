package lk.ijse.therapy_center.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Table(name = "patient")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {
    @Id
    @Column(name = "patient_id")
    private String  id;
    private String name;
    private String contact_number;
    private String email;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String medical_history;
    private Date registration_date;
}
