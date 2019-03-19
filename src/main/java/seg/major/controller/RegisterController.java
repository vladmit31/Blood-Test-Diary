package seg.major.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import seg.major.model.RegisterModel;

public class RegisterController {
    public TextField username;
    public TextField password;
    public TextField confirmPW;
    public TextField email;
    public Label usernameM;
    public Label confirmM;
    public Label emailM;
    public Label registerM;
    private RegisterModel model;

    @FXML
    public void handleRegisterButton() {
        int result = model.validateRegister(username.getText(), password.getText(), confirmPW.getText(), email.getText());
        if (result == 0) {
            usernameM.setVisible(false);
            confirmM.setVisible(false);
            emailM.setVisible(false);
            registerM.setVisible(true);
        } else {
            registerM.setVisible(false);
            switch(result){
                case 1:
                    usernameM.setVisible(true);
                    break;
                case 2:
                    confirmM.setVisible(true);
                    break;
                case 3:
                    emailM.setVisible(true);
                    break;
            }
        }
    }

    @FXML
    public void handleCancelButton() {

    }
}
