package seg.major.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import seg.major.model.LoginModel;
import seg.major.structure.User;
import java.net.URL;
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
        if (passField1.getText().equals(passField2.getText())) {
            User user = (User) data.get("user");
            LoginModel.changePassword(passField1.getText(), user);

            primaryController.setPane("schema");
            errorText.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Password successfully changed");
            alert.initStyle(StageStyle.UTILITY);
            alert.initOwner((Window) primaryController.getStage());
            alert.showAndWait();
        } else {
            errorText.setText("Passwords must match up.");
            errorText.setFill(Color.RED);
        }
    }

    public void cancelBtn(MouseEvent mouseEvent) {
        primaryController.setPane("schema");
        errorText.setText("");
    }
}
