package seg.major.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import seg.major.App;
import seg.major.model.LoginModel;
import seg.major.controller.PrimaryController;
import javax.swing.*;

public class LoginController implements Initializable, ControllerInterface {

    private PrimaryController primaryController;
    private Map<String, Object> data = new HashMap<>();
    private static LoginModel loginModel = new LoginModel();

    /** ---------- FXML ---------- */
    @FXML
    public TextField username;
    @FXML
    public Button loginButton;
    @FXML
    public PasswordField password;

    /**
     * The login button was clicked, so load the patient schema view
     * 
     * @param e click event
     */
    @FXML
    public void loginBtn() {
        if (!username.getText().equals("") && !password.getText().equals("")) {
            if (loginModel.validateLogin(username.getText(), password.getText())) {
                primaryController.setPane(App.schema);
                primaryController.sendTo(App.schema, "username", loginModel.getUserByUsername(username.getText()));
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials", "Wrong username/password",
                        JOptionPane.NO_OPTION);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Complete all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** ---------- FXML ---------- */

    /** ---------- Inherited / Implemented ---------- */
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
    public void setData(Map<String, Object> toSet) {
        this.data = toSet;
    }

    /**
     * Add data to the given fx-item and update the scene
     * 
     * @param tpAddKey the key of the data
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

    /** ---------- Inherited / Implemented ---------- */

}
