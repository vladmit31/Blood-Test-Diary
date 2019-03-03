package seg.major.model;

import com.ja.security.PasswordHash;
import seg.major.database.DatabaseConnection;
import seg.major.structure.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginModel {

    public static boolean validateUser(String username, String password) {

        PasswordHash ph = new PasswordHash();

        User toValidate = DatabaseConnection.getUserByUsername(username);

        if(toValidate == null) {
            return false;
        }

        try {
            return ph.validatePassword(password, toValidate.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return false;
    }
}
