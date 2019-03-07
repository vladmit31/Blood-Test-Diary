package seg.major.controller;

import java.util.Map;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seg.major.model.database.DatabaseConnection;
import seg.major.model.AddPatientModel;
import seg.major.structure.Patient;

import javax.swing.*;

/**
 * AddPatientController acts as the controller for the add_patient.fxml file
 */
public class AddPatientController implements Initializable, ControllerInterface {

  private PrimaryController primaryController;
  private Map<String, String> data;

  /** ---------- FXML ---------- */

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

  /** ---------- FXML ---------- */

  @FXML
  public void submit() {
    if (checkUserInput()) {
      Patient newPatient = model.createPatient(forenameField.getText(), surnameField.getText(), dobField.getValue(),
          hospitalField.getText(), clinicField.getText(), nextAppField.getValue());

      System.out.println(newPatient);

      DatabaseConnection.insertPatient(newPatient);
    } else {
      JOptionPane.showMessageDialog(null, "Complete all fields", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  @FXML
  public void cancel() {
    // TODO: Link back to the patient panel
    Stage stage = (Stage) cancelButton.getScene().getWindow();

    stage.close();
  }

  private boolean checkUserInput() {
    return !forenameField.getText().equals("") && !surnameField.getText().equals("") && dobField.getValue() != null
        && !hospitalField.getText().equals("") && !clinicField.getText().equals("") && nextAppField.getValue() != null;
  }

  /** ---------- Inherited / Implemented ---------- */
  /**
   * Allow javafx to initalise the controller with the view
   */
  public void initialize(URL url, ResourceBundle rb) {
    model = new AddPatientModel();
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
  public void setData(Map<String, String> data) {
    this.data = data;
  }

  /** ---------- Inherited / Implemented ---------- */

  public void addData(String fxID, String toAdd) {

  }

}
