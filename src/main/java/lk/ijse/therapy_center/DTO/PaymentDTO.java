package lk.ijse.therapy_center.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String id;
    private Date date;
    private String status;
    private BigDecimal amount;
    private String session_id;
    private String patient_id;
}
