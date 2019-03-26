package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import seg.major.App;
import seg.major.model.EditNotificationEmailModel;

import java.net.URL;
import java.util.*;

public class CustomReminderController implements Initializable, ControllerInterface {
    public TextField subjectTextField;
    public TextArea contentTextArea;
    public Button cancelButton;
    public Button saveButton;

    private Map<String, Object> data = new HashMap<>();

    private EditNotificationEmailModel.EmailType type;

    private PrimaryController primaryController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.type = EditNotificationEmailModel.EmailType.REMINDER;

        saveButton.setDisable(true);

        EditNotificationEmailModel.getFileContents(type);
        this.subjectTextField.setText(EditNotificationEmailModel.getSubject());
        fillTextArea();
    }

    @Override
    public void setScreenParent(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }

    @Override
    public void setData(Map<String, Object> toInject) {
        this.data = toInject;
    }

    private void fillTextArea() {
        this.contentTextArea.setText(EditNotificationEmailModel.getBodyAsString());
    }

    @Override
    public void addData(String toAddKey, Object toAddVal) {
        data.put(toAddKey, toAddVal);
        update();
    }

    @Override
    public void update() {

    }

    public void onSubjectModified(KeyEvent keyEvent) {
        this.saveButton.setDisable(false);
    }

    public void onContentModified(KeyEvent keyEvent) {
        this.saveButton.setDisable(false);
    }

    public void cancelButtonClicked(ActionEvent event) {
        this.primaryController.setPane(App.schema);
    }

    public void saveButtonClicked(ActionEvent event) {
        List<String> lines = Arrays.asList(this.contentTextArea.getText().split("\n"));
        EditNotificationEmailModel.setAll(this.subjectTextField.getText(), lines, type);
        primaryController.setPane(App.schema);
    }
}
