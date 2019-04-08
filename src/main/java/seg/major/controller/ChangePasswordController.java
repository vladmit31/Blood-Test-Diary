package seg.major.controller;

import com.ja.security.PasswordHash;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import seg.major.model.database.UserDAO;
import seg.major.structure.User;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
/**
 * AddPatientController acts as the controller for the change_password.fxml file
 * @author Team Pacane
 * @version 1.0
 */
public class ChangePasswordController implements Initializable, ControllerInterface {

    private PrimaryController primaryController;
    private Map<String, Object> data = new HashMap<>();

    @FXML
    public PasswordField passField2;
    @FXML
    public PasswordField passField1;
    @FXML
    public Text errorText;
    @FXML
    public Button confirmButton;
    @FXML
    public VBox parent;

    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL location, ResourceBundle resources) {
    setGlobalEventHandler(parent);
    }

    /**
     * Set the event handler to allow keystrokes to interact with GUI
     *
     * @param root the node to set as the handler
     */
    private void setGlobalEventHandler(Node root) {
        root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                confirmBtn();
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

    public void confirmBtn() {
        if(passField1.getText().equals(passField2.getText())&&!passField1.getText().equals(""))
        {
            PasswordHash hash = new PasswordHash();
            User user = (User) data.get("user");
            try {
                user.setPassword(hash.createHash(passField1.getText()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
            UserDAO.update(user);
            primaryController.setPane("schema");
            errorText.setText("");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Password successfully changed");
            alert.initStyle(StageStyle.UTILITY);
            alert.initOwner((Window)primaryController.getStage());
            alert.showAndWait();
        }
        else if(passField1.getText().equals("")||passField1.getText().equals("")){
            errorText.setText("Password can't be empty string");
            errorText.setFill(Color.RED);
        }
        else{
            errorText.setText("Passwords must match up.");
            errorText.setFill(Color.RED);
        }
    }

    public void cancelBtn(MouseEvent mouseEvent) {
        primaryController.setPane("schema");
        errorText.setText("");
    }
}
