package seg.major.controller.util;

import java.util.regex.Pattern;
/**
 * Checks if a string has the correct phone structure.
 * @author Team Pacane
 * @version 1.0
 */
public class PhoneChecker {
    public static boolean isValid(String phoneNumber) {
        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";

        Pattern pat = Pattern.compile(regex);
        if (phoneNumber == null)
            return false;
        return pat.matcher(phoneNumber).matches();
    }
}
