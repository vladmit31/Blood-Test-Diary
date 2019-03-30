package seg.major.model;

import com.ja.security.PasswordHash;
import seg.major.model.database.UserDAO;
import seg.major.structure.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
/**
 * Model class for LoginController class.
 * Provides communication between controller and DAOs if needed.
 * @author Team Pacane
 * @version 1.0
 */
public class LoginModel {

    public LoginModel() {
    }

    /**
     * Attempt to validate a login attempt with username and password
     * 
     * @return true if the user was correctly logged in
     */
    public static boolean validateLogin(String username, String password) {

        PasswordHash ph = new PasswordHash();

        User toValidate = UserDAO.getByUsername(username);

        if (toValidate == null) {
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

    /**
     * Get a user by their username
     * 
     * @param toGet username to search
     * @return the corresponding user
     */
    public static User getUserByUsername(String toGet) {
        return UserDAO.getByUsername(toGet);
    }

}
