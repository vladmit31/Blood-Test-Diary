package seg.major.controller;

import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

/**
 * SchemaController acts as the controller for the schema.fxml file
 */
public class SchemaController implements Initializable, ViewsController {


    public Tab carriedOverTab;
    public TableView carriedOverTable;
    public Tab thisWeekTab;
    public TableView thisWeekTable;
    public Tab mondayTab;
    public TableView mondayTable;
    public Tab tuesdayTab;
    public TableView tuesdayTable;
    public Tab wednesdayTab;
    public TableView wednesdayTable;
    public Tab thursdayTab;
    public TableView thursdayTable;
    public Tab fridayTab;
    public TableView fridayTable;
    private PrimaryController primaryController;

  /** ---------- FXML ---------- */
  public Button prevWeekBtn;
  public Button nextWeekBtn;
  public Text weekText;
  public Text userInfo;
  public Button logoutBtn;
  public MenuItem menuPatientsInfo;
  public MenuItem menuExit;
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
  /** ---------- Inherited / Implemented ---------- */

}
