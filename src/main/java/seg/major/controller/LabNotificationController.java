package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import seg.major.App;
import seg.major.controller.util.LaboratoryEmailSender;
import seg.major.structure.Patient;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class LabNotificationController implements Initializable, ControllerInterface {

    @FXML
    public TextField sendToTextBox;

    @FXML
    public TextField subjectTextBox;

    @FXML
    public TextArea emailBody;

    @FXML
    public Button backButton111;

    @FXML
    public Button sendButton111;

    private PrimaryController primaryController;

    private Map<String, Object> data = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.sendToTextBox.setDisable(true);
    }

    @Override
    public void setScreenParent(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }

    @Override
    public void setData(Map<String, Object> toInject) {
        this.data = data;
    }

    @Override
    public void addData(String toAddKey, Object toAddVal) {
        data.put(toAddKey, toAddVal);
    }

    @Override
    public void update() {
        if (data.get("patient") != null) {
            Patient patient = (Patient) data.get("patient");
            this.sendToTextBox.setText(patient.getLabContact());
        }

        fillSubjectField();

        fillEmailBody();
    }

    public void fillSubjectField() {
        this.subjectTextBox.setText("<fill-subject-here>");
    }

    public void fillEmailBody() {
        StringBuilder sb = new StringBuilder();

        Patient patient = (Patient) data.get("patient");

        sb.append("Dear ");
        sb.append(patient.getLabName() + ",\n\n");
        sb.append("The Denmark Hill Hospital, Pediatrics Department, wants to contact you on the following issue:\n\n");
        sb.append(" - Late blood test results for patient: \n\n");
        sb.append("Name: " + patient.getForename() + " " + patient.getSurname() + "\n");
        sb.append("NHS Number: " + patient.getNhsNumber() + "\n\n");
        sb.append("We will expect the results in the near future. Thank you!\n\n");
        sb.append("Best regards,\n");
        sb.append("LiverCNS");

        this.emailBody.setText(sb.toString());
    }

    public void backButtonClicked(ActionEvent event) {
        primaryController.setPane(App.updatePatient);
    }

    public void sendButtonClicked(ActionEvent event) {
        LaboratoryEmailSender les = new LaboratoryEmailSender(sendToTextBox.getText(), subjectTextBox.getText(),
                emailBody.getText());
        les.start();
        primaryController.setPane(App.updatePatient);
    }
}
