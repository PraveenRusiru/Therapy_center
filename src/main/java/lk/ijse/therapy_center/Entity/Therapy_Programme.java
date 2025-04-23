package lk.ijse.therapy_center.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "therapy_programme")
public class Therapy_Programme {
    @Id
    @Column(name = "therapy_programme_id")
    private String id;
    private String programme_name;
    private String programme_description;
    private String duration;
    @Column(precision = 10, scale = 2)
    private BigDecimal fee;
}
