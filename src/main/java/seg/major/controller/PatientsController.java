package seg.major.controller;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.Initializable;

/**
 * PatientsController acts as the controller for the patients.fxml file
 */
public class PatientsController implements Initializable, ControllerInterface {

  private PrimaryController primaryController;
  private HashMap<String, String[]> data;

  /** ---------- FXML ---------- */

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
   * @param data the data to set
   */
  public void setData(HashMap<String, String[]> data) {
    this.data = data;
  }
  /** ---------- Inherited / Implemented ---------- */

}
