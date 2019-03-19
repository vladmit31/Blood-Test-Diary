package seg.major.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ResourceBundle;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import seg.major.model.LoginModel;
import javafx.fxml.FXMLLoader;

public class LoginController implements Initializable {

    @FXML
    public TextField username;
    @FXML
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
        if (model.validateLogin(username.getText(), password.getText())) {

            message.setVisible(false);
        } else {
            message.setVisible(true);
        }
    }

    @FXML
    public Button register_button;

    public static LoginModel loginModel;

    public void initialize(URL url, ResourceBundle rb) {
    }

    public AnchorPane getPane() throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("views/login.fxml"));
        return pane;
    }

}
