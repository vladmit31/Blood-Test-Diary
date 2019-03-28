package seg.major.controller;

import com.ja.security.PasswordHash;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import seg.major.App;
import seg.major.controller.util.EmailChecker;
import seg.major.model.database.UserDAO;
import seg.major.structure.User;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
/**
 * AddPatientController acts as the controller for the add_user.fxml file
 * @author Team Pacane
 * @version 1.0
 */
public class AddUserController implements Initializable, ControllerInterface {

    public TextField usernameField;
    public TextField passwordField;
    public TextField confirmPasswordField;
    public TextField emailField;
    public Button addButton;
    public Button backButton;
    private PrimaryController primaryController;

    private Map<String, Object> data = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }

    @Override
    public void setData(Map<String, Object> toInject) {
        this.data = toInject;
    }

    @Override
    public void addData(String toAddKey, Object toAddVal) {
        data.put(toAddKey, toAddVal);
        update();
    }

    @Override
    public void update() {

    }

    public void backButtonClicked(ActionEvent event) {
        primaryController.setPane(App.schema);
    }

    private boolean checkUserInput() {
        return usernameField.getText().equals("") || passwordField.getText().equals("")
                || confirmPasswordField.getText().equals("") || emailField.getText().equals("");
    }

    public void addButtonClicked(ActionEvent event) {

        if(checkUserInput()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong signing up details!");
            alert.setHeaderText(null);
            alert.setContentText("You need to complete all fields!");

            alert.showAndWait();

            return;
        }

        if(!EmailChecker.isValid(emailField.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong signing up details!");
            alert.setHeaderText(null);
            alert.setContentText("E-main needs to match the following pattern:\n <name>@<domainname>.<com/..>");

            alert.showAndWait();

            return;
        }

        if(passwordField.getText().equals(confirmPasswordField.getText())) {
            PasswordHash ph = new PasswordHash();

            try {
                User newUser = new User(usernameField.getText(), emailField.getText(), ph.createHash(passwordField.getText()), 0);

                UserDAO.create(newUser);

                primaryController.setPane(App.schema);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }

        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong signing up details!");
            alert.setHeaderText(null);
            alert.setContentText("Passwords need to match!");

            alert.showAndWait();
        }
    }
}
