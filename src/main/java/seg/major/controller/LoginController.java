package seg.major.controller;

import com.ja.security.PasswordHash;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import seg.major.App;
import seg.major.controller.util.PasswordGenerator;
import seg.major.controller.util.RecoveryEmailSender;
import seg.major.model.LoginModel;
import seg.major.model.database.UserDAO;
import seg.major.structure.User;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * AddPatientController acts as the controller for the login.fxml file
 * @author Team Pacane
 * @version 1.0
 */
public class LoginController implements Initializable, ControllerInterface {

    private PrimaryController primaryController;
    private Map<String, Object> data = new HashMap<>();

    @FXML
    public Text errorLabel;
    @FXML
    public TextField username;
    @FXML
    public Button loginButton;
    @FXML
    public PasswordField password;
    @FXML
    public VBox parent;
    @FXML
    public ImageView keyImg;
    @FXML
    public ImageView humanImg;

    /**
     * The login button was clicked, so load the patient schema view
     *
     * @param e click event
     */
    @FXML
    public void loginBtn() {
        if (!username.getText().equals("") && !password.getText().equals("")) {
            if (LoginModel.validateLogin(username.getText(), password.getText())) {
                primaryController.sendTo(App.schema, "user", LoginModel.getUserByUsername(username.getText()));
                primaryController.setPane(App.schema);
                errorLabel.setText("");
            } else {
                errorLabel.setText("Invalid credentials");
                errorLabel.setFill(Color.RED);
            }
        } else {
            errorLabel.setText("Complete all the fields!");
            errorLabel.setFill(Color.RED);
        }
    }

    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL url, ResourceBundle rb) {
        setGlobalEventHandler(parent);
        humanImg.setImage(new Image("images/humanIcon.png"));
        keyImg.setImage(new Image("images/keyIcon.png"));
    }

    private void setGlobalEventHandler(Node root) {
            root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
                if (ev.getCode() == KeyCode.ENTER) {
                    loginBtn();
                    ev.consume();
                }
            });
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

    public void exit(ActionEvent actionEvent) {
        primaryController.closeStage();
    }


    public void recoverAccount(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Recovery window");
        dialog.setHeaderText("Forgot your login credentials?");
        dialog.setContentText("Please enter your email address:");

        Optional<String> result = dialog.showAndWait();
        User user = UserDAO.getByEmail(result.get());
        if (result.isPresent() && user != null ){
            PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                    .useDigits(true)
                    .useLower(true)
                    .useUpper(true)
                    .build();
            String password = passwordGenerator.generate(8);
            String email = "Hey there, \n Someone requested a new password for your Aeon account. \n New Password: "+ password;
            RecoveryEmailSender sender = new RecoveryEmailSender(result.get(),"Password recovery", email);
            sender.start();
            PasswordHash hash = new PasswordHash();
            String hashedPass= null;
            try {
                hashedPass = hash.createHash(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
            User updatedUser = user;
            updatedUser.setPassword(hashedPass);
            UserDAO.update(updatedUser);
        }
        else{
            Dialog dialogFailure = new Dialog();
            dialogFailure.setContentText("The email address you inserted isn't associated with an account.");
            dialogFailure.setTitle("Failure");
            dialogFailure.show();
        }
    }

}
