package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import seg.major.App;
import seg.major.model.UpdatePatientModel;
import seg.major.structure.Appointment;
import seg.major.structure.Patient;
import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * AddPatientController acts as the controller for the add_patient.fxml file
 */
public class UpdatePatientController implements Initializable, ControllerInterface {

    private PrimaryController primaryController;
    private Map<String, Object> data = new HashMap<>();

    /** ---------- FXML ---------- */

    @FXML
    private Text TitleText;

    @FXML
    private TextField forenameField;

    @FXML
    private TextField surnameField;

    @FXML
    private DatePicker dobField;

    @FXML
    private DatePicker nextAppField;

    @FXML
    private TextField hospitalField;

    @FXML
    private TextField clinicField;

    @FXML
    public Button editBtn;

    @FXML
    public Button contactsBtn;

    @FXML
    private Button cancelButton;

    @FXML
    private Button updateButton;

    @FXML
    private TextField diagnosisField;

    /** ---------- FXML ---------- */

    @FXML
    public void updateButtonClicked(MouseEvent mouseEvent) {
        if (checkUserInput()) {

            Patient patient = (Patient) data.get("patient");
            patient.setForename(forenameField.getText());
            patient.setSurname(surnameField.getText());
            patient.setDob(dobField.getValue());
            patient.setHospitalNumber(hospitalField.getText());
            patient.setLocalClinic(clinicField.getText());
            patient.setDiagnosis(diagnosisField.getText());

            Appointment appointment = (Appointment) data.get("appointment");
            appointment.setDueDate(nextAppField.getValue());

            UpdatePatientModel.updatePatient(patient);
            UpdatePatientModel.updateAppointment(appointment);

            data.remove("patient");
            data.remove("appointment");

            disableTextFields();

            primaryController.sendTo(App.patients, "patient", patient);
            primaryController.sendTo(App.patients, "appointment", appointment);

            primaryController.setPane(App.patients);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information needed!");
            alert.setHeaderText(null);
            alert.setContentText("You need to complete all fields before updating a patient.");
            alert.showAndWait();
        }
    }

    @FXML
    public void cancel() {
        // TODO: Link back to the patient panel
        disableTextFields();
        primaryController.setPane(App.patients);
    }

    private boolean checkUserInput() {
        return !forenameField.getText().equals("") && !surnameField.getText().equals("") && dobField.getValue() != null
                && !hospitalField.getText().equals("") && !clinicField.getText().equals("")
                && nextAppField.getValue() != null && !diagnosisField.getText().equals("");
    }

    /** ---------- Inherited / Implemented ---------- */
    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL url, ResourceBundle rb) {
        disableTextFields();
    }

    private void enableTextFields() {
        setTextFieldsAvailability(false);
    }

    private void disableTextFields() {
        setTextFieldsAvailability(true);
    }

    private void setTextFieldsAvailability(boolean value) {
        forenameField.setEditable(!value);
        surnameField.setEditable(!value);
        dobField.setDisable(value);
        clinicField.setEditable(!value);
        hospitalField.setEditable(!value);
        diagnosisField.setEditable(!value);
        nextAppField.setDisable(value);
    }

    public void setUp() {
    }

    public void passPatient(Patient p) {
        TitleText.setText(p.getForename() + " " + p.getSurname());
        forenameField.setText(p.getForename());
        surnameField.setText(p.getSurname());
        dobField.setValue(p.getDob());
        hospitalField.setText(p.getHospitalNumber());
        diagnosisField.setText(p.getDiagnosis());
        clinicField.setText(p.getLocalClinic());
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

    public void update() {
        if (data.get("patient") != null) {
            passPatient((Patient) data.get("patient"));
        }

        if (data.get("appointment") != null) {
            nextAppField.setValue(((Appointment) data.get("appointment")).getDueDate());
        }
    }

    /** ---------- Inherited / Implemented ---------- */
    public void contactsButtonClicked(ActionEvent event) {
        // Pair<Integer,Integer> p = (Pair<Integer, Integer>) data;

        if (data == null) {
            return;
        }
        primaryController.sendTo(App.contacts, "patient", data.get("patient"));
        primaryController.setPane(App.contacts);
    }

    public void editButtonClicked(ActionEvent event) {
        enableTextFields();
    }
}
