package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import seg.major.App;
import seg.major.database.DatabaseConnection;
import seg.major.model.UpdatePatientModel;
import seg.major.structure.Appointment;
import seg.major.structure.Patient;

import javax.swing.*;
import javax.xml.crypto.Data;
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
    public Button editBtn;

    @FXML
    public Button contactsBtn;

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
                    dobField.getValue(), hospitalField.getText(), clinicField.getText());


            Patient patient = DatabaseConnection.getPatientById(((Pair<Integer,Integer>) data).getKey());

            newPatient.setId(patient.getId());

            DatabaseConnection.updatePatientData(newPatient, nextAppField.getValue());

            PatientsController pc = ((PatientsController)primaryController.getControllerByName(App.patients));
            pc.refresh();
            pc.setInfoText("Updated patient: " + newPatient.getForename()+ " " +newPatient.getSurname());
            primaryController.setPane(App.patients);
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Complete all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void cancel(){
        // TODO: Link back to the patient panel
        disableTextFields();
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
        nextAppField.setDisable(value);
    }

    public void setUp() {
        Pair<Integer,Integer> p = (Pair<Integer, Integer>) data;
        if(data == null) {
            return;
        }
        Patient patient = DatabaseConnection.getPatientById(p.getKey());
        Appointment appointment = DatabaseConnection.getAppointmentById(p.getValue());
        if(patient == null || appointment == null) {
            return;
        }
        TitleText.setText("Update patient data: " + patient.getForename() + " " + patient.getSurname());
        passPatient(patient);
        nextAppField.setValue(appointment.getDueDate());
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
    /** ---------- Inherited / Implemented ---------- */

    public void setData(Object newData) {
        data = newData;
    }

    public void contactsButtonClicked(ActionEvent event) {
        Pair<Integer,Integer> p = (Pair<Integer, Integer>) data;

        if(data == null) {
            return;
        }

        Patient patient = DatabaseConnection.getPatientById(p.getKey());

        if(patient == null) {
            return;
        }

        //System.out.println("!!!!" + patient.getForename() + " " + patient.getSurname());

        ((ContactsController)primaryController.getControllerByName(App.contacts)).setData(patient);

        primaryController.setPane(App.contacts);
    }

    public void editButtonClicked(ActionEvent event) {
        enableTextFields();
    }
}
