package seg.major.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import seg.major.App;
import seg.major.model.UpdatePatientModel;
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
    private Button cancelButton;

    @FXML
    private Button updateButton;

    private static UpdatePatientModel model;

    /** ---------- FXML ---------- */

    @FXML
    public void updateBtn() {
        if (checkUserInput()) {
            Patient p = (Patient) data.get("patient");
            p.setForename(forenameField.getText());
            p.setSurname(surnameField.getText());
            p.setDob(dobField.getValue());
            p.setNextAppointment(nextAppField.getValue());
            p.setHospitalNumber(hospitalField.getText());
            p.setLocalClinic(clinicField.getText());
            UpdatePatientModel.updatePatient(p);
            data.remove("patient");
            primaryController.sendTo(App.patients, "patient", p);
            primaryController.setPane(App.patients);
        } else {
            JOptionPane.showMessageDialog(null, "Complete all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void cancel() {
        // TODO: Link back to the patient panel
        primaryController.setPane(App.patients);
    }

    private boolean checkUserInput() {
        return !forenameField.getText().equals("") && !surnameField.getText().equals("") && dobField.getValue() != null
                && !hospitalField.getText().equals("") && !clinicField.getText().equals("")
                && nextAppField.getValue() != null;
    }

    /** ---------- Inherited / Implemented ---------- */
    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL url, ResourceBundle rb) {
        model = new UpdatePatientModel();

    }

    public void setUp() {
    }

    public void passPatient(Patient p) {
        forenameField.setText(p.getForename());
        surnameField.setText(p.getSurname());
        dobField.setValue(p.getDob());
        hospitalField.setText(p.getHospitalNumber());
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
     * @param tpAddKey the key of the data
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
    }

    /** ---------- Inherited / Implemented ---------- */
}
