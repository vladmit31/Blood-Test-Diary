package seg.major.controller.util;

import java.util.regex.Pattern;

/**
 * Checks if a string has the correct e-mail structure.
 * @author Team Pacane
 * @version 1.0
 */
public class EmailChecker {
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
