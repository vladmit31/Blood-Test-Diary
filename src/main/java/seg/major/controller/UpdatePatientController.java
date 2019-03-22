package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import seg.major.App;
import seg.major.controller.util.EmailChecker;
import seg.major.model.UpdatePatientModel;
import seg.major.structure.Appointment;
import seg.major.structure.Patient;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * AddPatientController acts as the controller for the add_patient.fxml file
 */
public class UpdatePatientController implements Initializable, ControllerInterface {

    @FXML
    public TextField nhsNumber;

    @FXML
    public TextField labName;

    @FXML
    public TextField labContact;

    @FXML
    public Button notifyLabButton;

    private PrimaryController primaryController;
    private Map<String, Object> data = new HashMap<>();
    private Boolean isEditable;

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
            patient.setLabName(labName.getText());
            patient.setLabContact(labContact.getText());
            patient.setNhsNumber(nhsNumber.getText());

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

        initialState();
    }

    @FXML
    public void cancel() {
        primaryController.setPane(App.patients);

        initialState();
    }

    private boolean checkUserInput() {
        return !forenameField.getText().equals("") && !surnameField.getText().equals("") && dobField.getValue() != null
                && !hospitalField.getText().equals("") && !clinicField.getText().equals("")
                && nextAppField.getValue() != null && !diagnosisField.getText().equals("")
                && !labName.getText().equals("") && !labContact.getText().equals("")
                && EmailChecker.isValid(labContact.getText())
                && !nhsNumber.getText().equals("");
    }

    /** ---------- Inherited / Implemented ---------- */
    /**
     * Allow javafx to initalise the controller with the view
     */

    public void initialize(URL url, ResourceBundle rb) {
        initialState();
    }

    private void initialState() {
        editBtn.setText("Edit");
        disableTextFields();
        setupDefaultEditButton();
    }

    private ImageView setImageProperties(ImageView img){
        img.smoothProperty();
        img.setFitWidth(15);
        img.setFitHeight(13);
        return img;
    }

    private void setupDefaultEditButton(){
        ImageView lockImage;
        lockImage = new ImageView((new Image(getClass().getResourceAsStream("/images/lockDefault.png"))));
        editBtn.setGraphic(setImageProperties(setImageProperties(lockImage)));
        editBtn.setText("Edit");
    }
    private void setupLockButton(){
        ImageView lockImage;
        if(isEditable)
        lockImage = new ImageView((new Image(getClass().getResourceAsStream("/images/lockGreen.png"))));
        else{
            lockImage = new ImageView((new Image(getClass().getResourceAsStream("/images/lockRed.png"))));
        }
        editBtn.setGraphic(setImageProperties(setImageProperties(lockImage)));
    }

    private void enableTextFields() {
        setTextFieldsAvailability(false);
        isEditable = true;
    }

    private void disableTextFields() {
        setTextFieldsAvailability(true);
        isEditable = false;
    }

    private void setTextFieldsAvailability(boolean value) {
        forenameField.setEditable(!value);
        surnameField.setEditable(!value);
        dobField.setDisable(value);
        clinicField.setEditable(!value);
        hospitalField.setEditable(!value);
        diagnosisField.setEditable(!value);
        nextAppField.setDisable(value);
        labName.setDisable(value);
        labContact.setDisable(value);
        nhsNumber.setDisable(value);
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
        labName.setText(p.getLabName());
        labContact.setText(p.getLabContact());
        nhsNumber.setText(p.getNhsNumber());
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
        editBtn.setText("Edit");
        setupDefaultEditButton();
        primaryController.sendTo(App.contacts, "patient", data.get("patient"));
        primaryController.setPane(App.contacts);

        initialState();
    }


    public void editButtonClicked(ActionEvent event) {
        if(isEditable){
            disableTextFields();
            setupLockButton();
            editBtn.setText("Locked");
        }
        else{
            enableTextFields();
            setupLockButton();
            editBtn.setText("Unlocked");
        }
    }

    public void notifyLaboratoryButtonClicked(ActionEvent event) {

    }
}
