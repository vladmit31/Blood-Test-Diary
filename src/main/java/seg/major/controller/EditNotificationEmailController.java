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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class EditNotificationEmailController implements Initializable, ControllerInterface{

    public TextField subjectTextField;
    public TextArea contentTextArea;
    public Button cancelButton;
    public Button saveButton;

    private PrimaryController primaryController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.setDisable(true);

        EditNotificationEmailModel.getFileContents();
        this.subjectTextField.setText(EditNotificationEmailModel.getSubject());
        fillTextArea();

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

    }

    @Override
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
        EditNotificationEmailModel.setAll(this.subjectTextField.getText(), lines);
        primaryController.setPane(App.notifyList);
    }

    public void onContentModified(KeyEvent keyEvent) {
        this.saveButton.setDisable(false);
    }

    public void onSubjectModified(KeyEvent keyEvent) {
        this.saveButton.setDisable(false);
    }
}
