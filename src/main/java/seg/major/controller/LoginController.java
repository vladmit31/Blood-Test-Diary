package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import seg.major.database.DatabaseConnection;
import seg.major.model.LoginModel;
import seg.major.structure.User;

import javax.swing.*;
import java.io.IOException;

public class LoginController {
    @FXML
    public TextField username;
    @FXML
    public Button loginButton;
    @FXML
    public PasswordField password;

    @FXML
    public void login() {
        if(!username.getText().equals("") && !password.getText().equals("")) {

            if(LoginModel.validateUser(username.getText(), password.getText())) {
                try {
                    Parent newRoot = FXMLLoader.load(getClass().getResource("/views/schema.fxml"));
                    Scene newScene = new Scene(newRoot);
                    Stage newStage = (Stage) loginButton.getScene().getWindow();

                    newStage.setScene(newScene);
                    newStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                JOptionPane.showMessageDialog(null,
                        "Invalid credentials", "Wrong username/password", JOptionPane.NO_OPTION);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Complete all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
