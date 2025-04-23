package lk.ijse.therapy_center.DTO;

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
public class Therapy_ProgrammeDTO {
    private String id;
    private String programme_name;
    private String programme_description;
    private String duration;
    private BigDecimal fee;
}
