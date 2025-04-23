package lk.ijse.therapy_center.DTO.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProgrammeTM {
    private String id;
    private String programme_name;
    private String programme_description;
    private String duration;
    private BigDecimal fee;
}
