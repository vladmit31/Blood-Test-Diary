package seg.major.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seg.major.database.DatabaseConnection;
import seg.major.model.AddPatientModel;
import seg.major.structure.Patient;

import javax.swing.*;

public class AddPatientController {

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
    private Button submitButton;

    private AddPatientModel model;

    @FXML
    public void initialize() {
        model = new AddPatientModel();
    }

    @FXML
    public void submit()
    {
        /*
        System.out.println(forenameField.getText());
        System.out.println(surnameField.getText());
        System.out.println(dobField.getValue());
        System.out.println(hospitalField.getText());
        System.out.println(clinicField.getText());
        System.out.println(nextAppField.getValue());
        */

        if(checkUserInput()) {
            Patient newPatient = model.createPatient(forenameField.getText(), surnameField.getText(),
                    dobField.getValue(), hospitalField.getText(), clinicField.getText(), nextAppField.getValue());

            System.out.println(newPatient);

            DatabaseConnection.insertPatient(newPatient);
            /*System.out.println(newPatient);
            return newPatient;*/
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

}
