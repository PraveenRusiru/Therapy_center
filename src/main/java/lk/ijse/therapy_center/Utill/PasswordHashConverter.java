package lk.ijse.therapy_center.Utill;

import jakarta.persistence.AttributeConverter;

public class PasswordHashConverter implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String password) {
        return BCryptUtil.hashPassword(password);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
