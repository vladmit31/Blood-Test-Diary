package seg.major.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seg.major.model.AddPatientModel;

import javax.swing.*;

/**
 * AddPatientController acts as the controller for the add_patient.fxml file
 */
public class AddPatientController implements Initializable, ControllerInterface {

  private PrimaryController primaryController;
  private Map<String, Object> data = new HashMap<>();

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
  private TextField refreshRateField;

  @FXML
  private Button cancelButton;

  @FXML
  private Button submitButton;

  private AddPatientModel model;

  /** ---------- FXML ---------- */

  @FXML
  public void submit() {
    if (checkUserInput()) {

      AddPatientModel.createPatient(forenameField.getText(), surnameField.getText(), dobField.getValue(),
          hospitalField.getText(), clinicField.getText(), nextAppField.getValue(),
          Double.parseDouble(refreshRateField.getText()));
    } else

    {
      JOptionPane.showMessageDialog(null, "Complete all fields", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  @FXML
  public void cancel() {
    Stage stage = (Stage) cancelButton.getScene().getWindow();
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

  /**
   * Update the scene with changes from the data HashMap
   */
  public void update() {
  }
  /** ---------- Inherited / Implemented ---------- */

}
