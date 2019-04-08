package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import seg.major.App;
import seg.major.model.EditNotificationEmailModel;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
/**
 * AddPatientController acts as the controller for the edit_default_email.fxml file
 * @author Team Pacane
 * @version 1.0
 */
public class EditNotificationEmailController implements Initializable, ControllerInterface{

    private Map<String, Object> data = new HashMap<>();
    private EditNotificationEmailModel.EmailType type;
    private PrimaryController primaryController;

    @FXML
    public TextField subjectTextField;
    @FXML
    public TextArea contentTextArea;
    @FXML
    public Button cancelButton;
    @FXML
    public Button saveButton;

    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL location, ResourceBundle resources) {
        this.type = EditNotificationEmailModel.EmailType.MISSED;

        if(data.size() != 0){
            this.type = (EditNotificationEmailModel.EmailType)data.get("type");
        }

        saveButton.setDisable(true);

        EditNotificationEmailModel.getFileContents(type);
        this.subjectTextField.setText(EditNotificationEmailModel.getSubject());
        fillTextArea();
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

    private void fillTextArea() {
        StringBuilder sb = new StringBuilder();
        for(String line : EditNotificationEmailModel.getBody()) {
            sb.append(line);
            sb.append("\n");
        }
        this.contentTextArea.setText(sb.toString());
    }

    public void cancelButtonClicked(ActionEvent event) {
        this.primaryController.setPane(App.notifyList);
    }

    public void saveButtonClicked(ActionEvent event) {
        List<String> lines = Arrays.asList(this.contentTextArea.getText().split("\n"));
        EditNotificationEmailModel.setAll(this.subjectTextField.getText(), lines, type);
    }

    public void onContentModified(KeyEvent keyEvent) {
        this.saveButton.setDisable(false);
    }

    public void onSubjectModified(KeyEvent keyEvent) {
        this.saveButton.setDisable(false);
    }
}
