package lk.ijse.therapy_center.DTO;

import jakarta.persistence.*;
import lk.ijse.therapy_center.Utill.PasswordHashConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String id;
    private String username;

    private String password;
    private String recover_mail;
}
