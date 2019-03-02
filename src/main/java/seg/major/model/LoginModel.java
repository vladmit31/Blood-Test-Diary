package seg.major.model;

public class LoginModel {
    // username = root，password = admin
    public static boolean validateLogin(String username, String password) {
        if(validateUsername(username) && validatePassword(password)) {
            return true;
        }
            return false;
    }
    private static boolean validateUsername(String username) {
        if(!NullOrEmpty(username)) {
            if(username.equals("root")) {
                return true;
            }
        }
        return false;
    }

    private static boolean validatePassword(String password) {
        if(!NullOrEmpty(password)) {
            if(password.equals("admin")) {
                return true;
            }
        }
        return false;
    }

    private static boolean NullOrEmpty(String str) {
        if(str == null || str.isEmpty()) {
            return true;
        }
        return false;
    }
}
