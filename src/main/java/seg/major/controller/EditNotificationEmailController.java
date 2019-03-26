package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import seg.major.App;
import seg.major.model.EditNotificationEmailModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class EditNotificationEmailController implements Initializable, ControllerInterface{

    public TextField subjectTextField;
    public TextArea contentTextArea;
    public Button cancelButton;
    public Button saveButton;

    private Map<String, Object> data = new HashMap<>();

    private EditNotificationEmailModel.EmailType type;

    private PrimaryController primaryController;

    @Override
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

    @Override
    public void setScreenParent(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }

    @Override
    public void setData(Map<String, Object> toInject) {
        this.data = toInject;
    }

    @Override
    public void addData(String toAddKey, Object toAddVal) {
        data.put(toAddKey, toAddVal);
        update();
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
        EditNotificationEmailModel.setAll(this.subjectTextField.getText(), lines, type);
    }

    public void onContentModified(KeyEvent keyEvent) {
        this.saveButton.setDisable(false);
    }

    public void onSubjectModified(KeyEvent keyEvent) {
        this.saveButton.setDisable(false);
    }
}
