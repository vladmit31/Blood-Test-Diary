package seg.major.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import seg.major.App;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import seg.major.model.SchemaModel;
import seg.major.structure.AppointmentEntry;
import seg.major.structure.User;

/**
 * SchemaController acts as the controller for the schema.fxml file
 */
public class SchemaController implements Initializable, ControllerInterface {

  private PrimaryController primaryController;
  private Map<String, Object> data = new HashMap<>();

  @FXML
  public TableColumn<AppointmentEntry, String> carriedOverName;
  @FXML
  public TableColumn<AppointmentEntry, String> carriedOverHospital;
  @FXML
  public TableColumn<AppointmentEntry, Integer> carriedOverComplete;
  @FXML
  public TableColumn<AppointmentEntry, LocalDate> carriedOverDueDate;
  @FXML
  public TableColumn<AppointmentEntry, String> thisWeekName;
  @FXML
  public TableColumn<AppointmentEntry, String> thisWeekHospital;
  @FXML
  public TableColumn<AppointmentEntry, Integer> thisWeekComplete;
  @FXML
  public TableColumn<AppointmentEntry, LocalDate> thisWeekDueDate;
  @FXML
  public TableColumn<AppointmentEntry, String> mondayName;
  @FXML
  public TableColumn<AppointmentEntry, String> mondayHospital;
  @FXML
  public TableColumn<AppointmentEntry, Integer> mondayComplete;
  @FXML
  public TableColumn<AppointmentEntry, LocalDate> mondayDueDate;
  @FXML
  public TableColumn<AppointmentEntry, String> tuesdayName;
  @FXML
  public TableColumn<AppointmentEntry, String> tuesdayHospital;
  @FXML
  public TableColumn<AppointmentEntry, Integer> tuesdayComplete;
  @FXML
  public TableColumn<AppointmentEntry, LocalDate> tuesdayDueDate;
  @FXML
  public TableColumn<AppointmentEntry, String> wednesdayName;
  @FXML
  public TableColumn<AppointmentEntry, String> wednesdayHospital;
  @FXML
  public TableColumn<AppointmentEntry, Integer> wednesdayComplete;
  @FXML
  public TableColumn<AppointmentEntry, LocalDate> wednesdayDueDate;
  @FXML
  public TableColumn<AppointmentEntry, String> thursdayName;
  @FXML
  public TableColumn<AppointmentEntry, String> thursdayHospital;
  @FXML
  public TableColumn<AppointmentEntry, Integer> thursdayComplete;
  @FXML
  public TableColumn<AppointmentEntry, LocalDate> thursdayDueDate;
  @FXML
  public TableColumn<AppointmentEntry, String> fridayName;
  @FXML
  public TableColumn<AppointmentEntry, String> fridayHospital;
  @FXML
  public TableColumn<AppointmentEntry, Integer> fridayComplete;
  @FXML
  public TableColumn<AppointmentEntry, LocalDate> fridayDueDate;

  public Button under12Btn;
  public Button over12Btn;
  public boolean isUnder12 = true;
  @FXML
  public Text bottomInfo;
  // @FXML
  // private SchemaModel SchemaModel;
  @FXML
  public Tab carriedOverTab;
  @FXML
  public TableView<AppointmentEntry> carriedOverTable;
  @FXML
  public Tab thisWeekTab;
  @FXML
  public TableView<AppointmentEntry> thisWeekTable;
  @FXML
  public Tab mondayTab;
  @FXML
  public TableView<AppointmentEntry> mondayTable;
  @FXML
  public Tab tuesdayTab;
  @FXML
  public TableView<AppointmentEntry> tuesdayTable;
  @FXML
  public Tab wednesdayTab;
  @FXML
  public TableView<AppointmentEntry> wednesdayTable;
  @FXML
  public Tab thursdayTab;
  @FXML
  public TableView<AppointmentEntry> thursdayTable;
  @FXML
  public Tab fridayTab;
  @FXML
  public TableView<AppointmentEntry> fridayTable;

  /** ---------- FXML ---------- */
  @FXML
  public Button prevWeekBtn;
  @FXML
  public Button nextWeekBtn;
  @FXML
  public Text weekText;
  @FXML
  public Label userInfo;
  @FXML
  public Button logoutBtn;
  @FXML
  public MenuItem menuPatientsInfo;
  @FXML
  public MenuItem menuExit;

  /** ---------- FXML ---------- */

  /** ---------- Inherited / Implemented ---------- */
  /**
   * Allow javafx to initalise the controller with the view
   */
  public void initialize(URL url, ResourceBundle rb) {
    setUpTable();
    setUpButtons();
    setCurrentDate();
    this.weekText.setText(SchemaModel.getWeek());
  }

  /**
   * Get date for calculating the current week
   */
  private void setCurrentDate() {
    LocalDate localDate = LocalDate.now();
    // TODO: get the current date and compute the week that we are in
  }

  private void setUpButtons() {
    under12Btn.setOnAction(e -> {
      under12Btn.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
      over12Btn.setStyle(null);
      isUnder12 = true;
    });
    over12Btn.setOnAction(e -> {
      over12Btn.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
      under12Btn.setStyle(null);
      isUnder12 = false;
    });
  }

  private void setUpTable() {
    setUpColumns();
    setUpRows();
    fillTablesForUnder12();
    under12Btn.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
    over12Btn.setStyle(null);
  }

  private void setUpRows() {
    mondayTable.setRowFactory(t -> {
      TableRow<AppointmentEntry> row = new TableRow<>();
      row.setOnMouseClicked(click -> {
        if (!row.isEmpty() && click.getButton() == MouseButton.PRIMARY && click.getClickCount() == 2) {
          AppointmentEntry appointment = row.getItem();
          // viewPatient(patient);

          primaryController.sendTo(App.updateAppointment, "appointmentID", appointment);
          primaryController.setPane(App.updateAppointment);

        }
      });
      return row;
    });
  }

  /*
   * private void fillTable(){ patientTable.getItems().clear(); for (Patient
   * patient : patientModel.getPatientList()) {
   * patientTable.getItems().add(patient); } }
   */

  public void fillTable(TableView<AppointmentEntry> tb, List<AppointmentEntry> appEntryList) {
    tb.getItems().clear();
    ObservableList<AppointmentEntry> toFill = FXCollections.observableArrayList(appEntryList);
    tb.setItems(toFill);
  }

  public void fillTablesForUnder12() {
    fillTable(carriedOverTable, SchemaModel.getAll());
    fillTable(thisWeekTable, SchemaModel.getAll());
    fillTable(mondayTable, SchemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.MONDAY));
    fillTable(tuesdayTable, SchemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.TUESDAY));
    fillTable(wednesdayTable, SchemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.WEDNESDAY));
    fillTable(thursdayTable, SchemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.THURSDAY));
    fillTable(fridayTable, SchemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.FRIDAY));
  }

  public void fillTablesForOver12() {
    fillTable(carriedOverTable, SchemaModel.getAll());
    fillTable(thisWeekTable, SchemaModel.getAll());
    fillTable(mondayTable, SchemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.MONDAY));
    fillTable(tuesdayTable, SchemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.TUESDAY));
    fillTable(wednesdayTable, SchemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.WEDNESDAY));
    fillTable(thursdayTable, SchemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.THURSDAY));
    fillTable(fridayTable, SchemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.FRIDAY));
  }

  private void setUpColumns() {
    carriedOverName.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("name"));
    carriedOverHospital.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("vnumber"));
    carriedOverComplete.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, Integer>("complete"));
    carriedOverDueDate.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, LocalDate>("dueDate"));

    thisWeekName.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("name"));
    thisWeekHospital.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("vnumber"));
    thisWeekComplete.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, Integer>("complete"));
    thisWeekDueDate.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, LocalDate>("dueDate"));

    mondayName.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("name"));
    mondayHospital.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("vnumber"));
    mondayComplete.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, Integer>("complete"));
    mondayDueDate.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, LocalDate>("dueDate"));

    tuesdayName.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("name"));
    tuesdayHospital.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("vnumber"));
    tuesdayComplete.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, Integer>("complete"));
    tuesdayDueDate.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, LocalDate>("dueDate"));

    wednesdayName.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("name"));
    wednesdayHospital.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("vnumber"));
    wednesdayComplete.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, Integer>("complete"));
    wednesdayDueDate.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, LocalDate>("dueDate"));

    thursdayName.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("name"));
    thursdayHospital.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("vnumber"));
    thursdayComplete.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, Integer>("complete"));
    thursdayDueDate.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, LocalDate>("dueDate"));

    fridayName.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("name"));
    fridayHospital.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("vnumber"));
    fridayComplete.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, Integer>("complete"));
    fridayDueDate.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, LocalDate>("dueDate"));
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
  public void setData(Map<String, Object> toSet) {
    this.data = toSet;
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
    setAuthenticatedUser();
  }

  /** ---------- Inherited / Implemented ---------- */

  public void setAuthenticatedUser() {
    userInfo.setText("You logged in as " + ((User) data.get("user")).getUsername());
  }

  public void logoutBtn(ActionEvent event) {
    primaryController.setPane(App.login);
  }

  public void switchToPatients(ActionEvent event) {
    primaryController.setPane(App.patients);

    // ((PatientsController)
    // primaryController.getControllerByName(App.patients)).setInfoText("");
  }

  public void previousWeekButtonClicked(ActionEvent event) {
    SchemaModel.decrementWeek();

    this.weekText.setText(SchemaModel.getWeek());
    refresh();
  }

  public void nextWeekButtonClicked(ActionEvent event) {
    SchemaModel.incrementWeek();

    this.weekText.setText(SchemaModel.getWeek());
    refresh();
  }

  public void refresh() {
    SchemaModel.updateData();
    // TODO: check for which button is selected for update.
    if (isUnder12) {
      fillTablesForUnder12();
    } else {
      fillTablesForOver12();
    }
  }
}
