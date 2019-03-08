package seg.major.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seg.major.App;
import seg.major.database.DatabaseConnection;
import seg.major.model.UpdatePatientModel;
import seg.major.structure.Patient;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * AddPatientController acts as the controller for the add_patient.fxml file
 */
public class UpdatePatientController implements Initializable, ViewsController {

    private PrimaryController primaryController;

    private Object data;

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
    private TextField diagnosisField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button updateButton;

    private UpdatePatientModel model;

    /** ---------- FXML ---------- */

    @FXML
    public void update()
    {
        if(checkUserInput()) {
            Patient newPatient = model.updatePatient(forenameField.getText(), surnameField.getText(),
                    dobField.getValue(), hospitalField.getText(), clinicField.getText(), nextAppField.getValue(), diagnosisField.getText());



            newPatient.setId(((Patient) data).getId());

            DatabaseConnection.updatePatientData(newPatient);
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Complete all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void cancel(){
        // TODO: Link back to the patient panel
        primaryController.setPane(App.patients);
    }

    private boolean checkUserInput() {
        return !forenameField.getText().equals("") && !surnameField.getText().equals("") && dobField.getValue()!=null &&
                !hospitalField.getText().equals("") && !clinicField.getText().equals("") &&  nextAppField.getValue()!=null;
    }

    /** ---------- Inherited / Implemented ---------- */
    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL url, ResourceBundle rb) {
        model = new UpdatePatientModel();

    }

    public void setUp() {
        Patient p = (Patient) data;
        if(data == null) {
            return;
        }
        TitleText.setText("Update patient data: " + p.getForename() + " " + p.getSurname());
        passPatient(p);
    }

    public void passPatient(Patient p) {
        forenameField.setText(p.getForename());
        surnameField.setText(p.getSurname());
        dobField.setValue(p.getDob());
        hospitalField.setText(p.getHospitalNumber());
        clinicField.setText(p.getLocalClinic());
        nextAppField.setValue(p.getNextAppointment());
        diagnosisField.setText((p.getDiagnosis()));
    }

    /**
     * Set the primaryController
     *
     * @param primaryController the PrimaryController to set
     */
    public void setScreenParent(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }
    /** ---------- Inherited / Implemented ---------- */

    public void setData(Object newData) {
        data = newData;
    }
}
