package seg.major.controller;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import seg.major.model.LoginModel;
import seg.major.App;

/**
 * LoginController acts as the controller for the login.fxml file
 */
public class LoginController implements Initializable, ViewsController {

    public static LoginModel loginModel;
    private PrimaryController primaryController;

    /** ---------- FXML ---------- */
    @FXML
    public TextField username;
    @FXML
    public Button login_button;
    @FXML
    public Button register_button;
    @FXML
    public PasswordField password;

    /**
     * The login button was clicked, so load the patient schema view
     * 
     * @param e click event
     */
    @FXML
    private void loginBtn(ActionEvent e) {
        if (LoginModel.validateLogin(username.getText(), password.getText()) == true) {
            primaryController.setPane(App.schema);
        }
        primaryController.setPane(App.schema);
    }

    /**
     * The register button was clicked, so load the register staff view
     * 
     * TODO Create staff registration view and controller
     * 
     * @param e click event
     */
    @FXML
    public void registerBtn(ActionEvent e) {

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
    /** ---------- Inherited / Implemented ---------- */

}
