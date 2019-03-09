package seg.major.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seg.major.App;

/**
 * SchemaController acts as the controller for the schema.fxml file
 */
public class SchemaController implements Initializable, ControllerInterface {

  private PrimaryController primaryController;
  private Map<String, String> data = new HashMap<>();

  /** ---------- FXML ---------- */
  @FXML
  public Button logout;
  @FXML
  public Label currentUserLabel;

  /**
   * The login button was clicked, so load the patient schema view
   */
  @FXML
  public void logoutBtn() {
    primaryController.setPane(App.login);
  }

  /**
   * Set the currentUser Text to the user of current user
   */
  @FXML
  public void currentUserLabel(String toSet) {
    System.out.println(toSet);
    currentUserLabel.setText(toSet);
  }

  /** ---------- FXML ---------- */

  /** ---------- Inherited / Implemented ---------- */
  /**
   * Allow javafx to initalise the controller with the view
   */
  public void initialize(URL url, ResourceBundle rb) {

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
   * @param toSet the data to set
   */
  public void setData(Map<String, String> toSet) {
    this.data = toSet;
  }

  /**
   * Add data to the given fx-item and update the scene
   * 
   * @param tpAddKey the key of the data
   * @param toAddVal the value of the data
   */
  public void addData(String toAddKey, String toAddVal) {
    data.put(toAddKey, toAddVal);
    update();
  }

  /**
   * Update the scene with changes from the data HashMap
   */
  public void update() {
    currentUserLabel(data.get("username"));
  }
  /** ---------- Inherited / Implemented ---------- */

}
