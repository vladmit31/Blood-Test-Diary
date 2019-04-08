package seg.major.controller;

import com.ja.security.PasswordHash;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    private PrimaryController primaryController;
    private Map<String, Object> data = new HashMap<>();

    @FXML
    public TextField usernameField;
    @FXML
    public TextField passwordField;
    @FXML
    public TextField confirmPasswordField;
    @FXML
    public TextField emailField;
    @FXML
    public Button addButton;
    @FXML
    public Button backButton;

    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Set the primaryController
     *
     * @param primaryController the PrimaryController to set
     */
    public void setScreenParent(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }

    /**
     * Set the data
     *
     * @param data the data to set
     */
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * Add data to the given fx-item and update the scene
     *
     * @param toAddKey the key of the data
     * @param toAddVal the value of the data
     */
    public void addData(String toAddKey, Object toAddVal) {
        data.put(toAddKey, toAddVal);
        update();
    }

    /**
     * Update the scene with changes from the data HashMap
     */
    public void update() {
    }

    public void backButtonClicked(ActionEvent event) {
        primaryController.setPane(App.schema);
    }

    /**
     * Check that that no fields are blank
     * @return true if no fields are blank
     */
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
