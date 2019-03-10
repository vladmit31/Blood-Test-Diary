package seg.major.model;

import com.ja.security.PasswordHash;
import seg.major.structure.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginModel {

    public LoginModel() {
    }

    /**
     * Attempt to validate a login attempt with username and password
     * 
     * @return true if the user was correctly logged in
     */
    public boolean validateLogin(String username, String password) {

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

    public User getUserByUsername(String toGet) {
        return UserDAO.getByUsername(toGet);
    }

}
