package seg.major.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import seg.major.App;
import seg.major.model.SchemaModel;
import seg.major.structure.Appointment;
import seg.major.structure.Patient;
import seg.major.structure.User;

/**
 * SchemaController acts as the controller for the schema.fxml file
 */
public class SchemaController implements Initializable, ViewsController {

    private PrimaryController primaryController;

    public Button under12Btn;
    public Button over12Btn;
    public Text bottomInfo;

    private SchemaModel schemaModel;

    private Object data;

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
      this.schemaModel = new SchemaModel();
      setUpTable();
      setUpButtons();
      setCurrentDate();
  }

    /**
     * Get date for calculating the current week
     */
  private void setCurrentDate() {
      LocalDate localDate = LocalDate.now();
      // TODO: get the current date and compute the week that we are in
      //System.out.println(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate));
  }

    private void setUpButtons() {

    }

    private void setUpTable() {
      setUpColumn();
      setUpRows();
      //fillTable(schemaModel.getAppointmentsAndPatientsForDayUnder12());
      under12Btn.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
      carriedOverTable.setPlaceholder(new Label("No patients found"));
    }

    public void fillTable(Map<Appointment, Patient> appToPatients) {

    }

    private void setUpRows() {

    }

    private void setUpColumn(){
        //((TableColumn)carriedOverTable.getColumns().get(0)).setCellFactory("");
        /*forename.setCellValueFactory(new PropertyValueFactory("forename"));
        surname.setCellValueFactory(new PropertyValueFactory("surname"));
        hospitalNumber.setCellValueFactory(new PropertyValueFactory("hospitalNumber"));
        localClinic.setCellValueFactory(new PropertyValueFactory("localClinic"));
        nextAppointment.setCellValueFactory(new PropertyValueFactory("nextAppointment"));*/
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

  public void setAuthenticatedUser() {
    userInfo.setText("You logged in as " + ((User)data).getUsername());
  }

    public void logout(ActionEvent event) {
      primaryController.setPane(App.login);
    }
}
