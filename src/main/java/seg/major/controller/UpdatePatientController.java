package seg.major.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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

    private UpdatePatientModel model;

    /** ---------- FXML ---------- */

    @FXML
    public void update()
    {
        if(checkUserInput()) {
            Patient newPatient = model.updatePatient(forenameField.getText(), surnameField.getText(),
                    dobField.getValue(), hospitalField.getText(), clinicField.getText(), nextAppField.getValue());

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
        Stage stage = (Stage) cancelButton.getScene().getWindow();

        stage.close();
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
        TitleText.setText("Update patient data: " /* +rb.getString()*/);

    }

    /*public void passPatient(Patient p) {
        forenameField.setText(p.getForename());
        surnameField.setText(p.getSurname());
        dobField.setValue(p.getDob());
        hospitalField.setText(p.getHospitalNumber());
        clinicField.setText(p.getLocalClinic());
        nextAppField.setValue(p.getNextAppointment());
    } */

    /**
     * Set the primaryController
     *
     * @param primaryController the PrimaryController to set
     */
    public void setScreenParent(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }
    /** ---------- Inherited / Implemented ---------- */

}
