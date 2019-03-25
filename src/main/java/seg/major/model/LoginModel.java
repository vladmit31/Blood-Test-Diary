package seg.major.model;

import com.ja.security.PasswordHash;
import seg.major.structure.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import seg.major.controller.util.PasswordGenerator;
import seg.major.controller.util.RecoveryEmailSender;
import seg.major.model.database.UserDAO;

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

    public static User getUserByUsername(String toGet) {
        return UserDAO.getByUsername(toGet);
    }

    public static User getUserByEmail(String toGet) {
        return UserDAO.getByEmail(toGet);
    }

    public static void changePassword(String toSet, User toUpdate) {
        PasswordHash hash = new PasswordHash();

        try {
            toUpdate.setPassword(hash.createHash(toSet));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        UserDAO.update(toUpdate);
    }

    public static void recoverAccount(String toSet, User toRecover) {
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder().useDigits(true)
                .useLower(true).useUpper(true).build();
        String password = passwordGenerator.generate(8);
        String email = "Hey there, \n Someone requested a new password for your Aeon account. \n New Password: "
                + password;
        RecoveryEmailSender sender = new RecoveryEmailSender(toSet, "Password recovery", email);
        sender.start();
        PasswordHash hash = new PasswordHash();
        String hashedPass = null;
        try {
            hashedPass = hash.createHash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        User updatedUser = toRecover;
        updatedUser.setPassword(hashedPass);
        UserDAO.update(updatedUser);
    }

}
