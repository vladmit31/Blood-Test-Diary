package seg.major.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import seg.major.model.AddPatientModel;
import seg.major.structure.Patient;

import javax.swing.*;

public class AddPatientController {


    @FXML
    private JTextField forenameField;

    @FXML
    private JTextField surnameField;

    @FXML
    private DatePicker dobField;

    @FXML
    private DatePicker nextAppField;

    @FXML
    private JTextField hospitalField;

    @FXML
    private JTextField clinicField;

    @FXML
    private JButton cancelButton;

    @FXML
    private JButton submitButton;

    @FXML
    private AddPatientModel model;

    public AddPatientController(AddPatientModel model){
        this.model = model;
    }


    @FXML
    public Patient submit()
    {
        if(!forenameField.getText().equals("") && !surnameField.getText().equals("") && dobField.getValue()!=null &&
                !hospitalField.equals("") && !clinicField.equals("") &&  nextAppField.getValue()!=null) {
            Patient newPatient = model.createPatient(forenameField.getText(), surnameField.getText(),
                    dobField.getValue(), hospitalField.getText(), clinicField.getText(), nextAppField.getValue());


            return newPatient;
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Complete all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
        }
    }


    @FXML
    public void cancel(){


    }


}
