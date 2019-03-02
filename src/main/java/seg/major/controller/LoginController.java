package seg.major.controller;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import seg.major.model.LoginModel;

public class LoginController {
    public TextField username;
    public Button login_button;
    public Button register_button;
    public PasswordField password;
    private LoginModel model;
    public LoginController() {
        model = new LoginModel();
        model.validateLogin(username.getText(), password.getText());
    }
}
