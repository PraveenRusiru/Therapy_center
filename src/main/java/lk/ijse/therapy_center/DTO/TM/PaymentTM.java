package lk.ijse.therapy_center.DTO.TM;

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
public class PaymentTM {
    private String id;
    private Date date;
    private String status;
    private BigDecimal amount;
    private String session_id;
    private String patient_id;
}
