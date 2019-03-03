package seg.major.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import seg.major.model.LoginModel;

public class LoginController {
    public TextField username;
    public Button login_button;
    public PasswordField password;
    public Label message;
    private LoginModel model;

    public LoginController() {
        /*
        username = new TextField();
        login_button = new Button();
        password = new PasswordField();
        message = new Label();
        model = new LoginModel();
        */

    }

    @FXML
    private void handleLoginButton() {
        if(model.validateLogin(username.getText(), password.getText())) {

            message.setVisible(false);
        }else {
            message.setVisible(true);
        }
    }
}
