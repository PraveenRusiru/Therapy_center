package lk.ijse.therapy_center.Entity;

import jakarta.persistence.*;
import lk.ijse.therapy_center.Utill.PasswordHashConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String username;
    @Convert(converter = PasswordHashConverter.class)
    private String password;
    private String recover_mail;
}
