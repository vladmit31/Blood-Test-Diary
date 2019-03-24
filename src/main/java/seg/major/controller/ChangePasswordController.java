package seg.major.controller;

import com.ja.security.PasswordHash;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
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

public class ChangePasswordController implements Initializable, ControllerInterface {

    @FXML
    public PasswordField passField2;
    @FXML
    public PasswordField passField1;
    @FXML
    public Text errorText;

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

    }

    @Override
    public void addData(String toAddKey, Object toAddVal) {
        data.put(toAddKey, toAddVal);
        update();
    }

    @Override
    public void update() {

    }

    public void confirmBtn(MouseEvent mouseEvent) {
        if(passField1.getText().equals(passField2.getText()))
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
