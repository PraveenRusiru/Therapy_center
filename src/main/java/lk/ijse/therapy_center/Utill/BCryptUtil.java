package lk.ijse.therapy_center.Utill;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtil {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // 12 is the cost factor
    }

    // Verify entered password against hashed password in DB
    public static boolean checkPassword(String enteredPassword, String storedHashedPassword) {
        return BCrypt.checkpw(enteredPassword, storedHashedPassword);
    }
}
