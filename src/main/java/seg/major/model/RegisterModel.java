package seg.major.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterModel {

    public static int validateRegister(String username, String password, String confirmPW, String email){
        int[] results ={validateUsername(username), validatePassword(password, confirmPW), validateEmail(email)};

        for(int i = 0; i < 3; i++) {
            if(results[i] != 0) {
                return results[i];
            }
        }
        return 0;
    }

    //did not do the search sql.
    private static int validateUsername(String username) {
        if(NullOrEmpty(username) != true){
            return 0;
        }
        return 1;
    }

    private static int validatePassword(String password, String confirmPW) {
        if(NullOrEmpty(password) != true && NullOrEmpty(confirmPW)){
            if(password.equals(confirmPW)){
                return 0;
            }else{
                return 2;
            }
        }
        return 2;
    }

    //did not do the search sql.
    private static int validateEmail(String email) {
        if(NullOrEmpty(email) != true){
            if(checkEmailFormat(email)){
                return 0;
            }
        }
        return 3;
    }

    private static boolean NullOrEmpty(String str) {
        if(str == null || str.isEmpty()) {
            return true;
        }
        return false;
    }

    private static boolean checkEmailFormat(String email) {
        String sample = "^([a-z0-9A-Z] + [-|\\.]?) + [a-z0-9A-Z]@([a-z0-zA-Z] + (-[a-z0-9A-Z]+)?\\.) + [a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(sample);
        Matcher m = p.matcher(email);
        if(m.matches()){
            return true;
        }
        return false;
    }

}
