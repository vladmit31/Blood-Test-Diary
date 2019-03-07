package seg.major.controller;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import seg.major.App;

/**
 * SchemaController acts as the controller for the schema.fxml file
 */
public class SchemaController implements Initializable, ControllerInterface {

  private PrimaryController primaryController;
  private HashMap<String, String[]> data;

  /** ---------- FXML ---------- */
  @FXML
  public Button logout;

  /**
   * The login button was clicked, so load the patient schema view
   * 
   * @param e click event
   */
  @FXML
  public void logoutBtn() {
    primaryController.setPane(App.login);
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
   * @param data the data to set
   */
  public void setData(HashMap<String, String[]> data) {
    this.data = data;
  }
  /** ---------- Inherited / Implemented ---------- */

}
