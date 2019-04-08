package seg.major.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import seg.major.App;
import seg.major.model.SchemaModel;
import seg.major.structure.AppointmentEntry;
import seg.major.structure.User;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * SchemaController acts as the controller for the schema.fxml file
 * 
 * @author Team Pacane
 * @version 1.0
 */
public class SchemaController implements Initializable, ControllerInterface {

  public MenuItem editReminderEmail;
  public MenuItem addNewUser;
  public boolean isUnder12 = true;

  private PrimaryController primaryController;
  private Map<String, Object> data = new HashMap<>();
  private MenuItem notificationList;
  private SchemaModel schemaModel;

  @FXML
  public TableColumn<AppointmentEntry, String> carriedOverName;
  @FXML
  public TableColumn<AppointmentEntry, String> carriedOverHospital;
  @FXML
  public TableColumn<AppointmentEntry, String> carriedOverComplete;
  @FXML
  public TableColumn<AppointmentEntry, String> carriedOverDueDate;
  @FXML
  public TableColumn<AppointmentEntry, String> thisWeekName;
  @FXML
  public TableColumn<AppointmentEntry, String> thisWeekHospital;
  @FXML
  public TableColumn<AppointmentEntry, String> thisWeekComplete;
  @FXML
  public TableColumn<AppointmentEntry, String> thisWeekDueDate;
  @FXML
  public TableColumn<AppointmentEntry, String> mondayName;
  @FXML
  public TableColumn<AppointmentEntry, String> mondayHospital;
  @FXML
  public TableColumn<AppointmentEntry, String> mondayComplete;
  @FXML
  public TableColumn<AppointmentEntry, String> mondayDueDate;
  @FXML
  public TableColumn<AppointmentEntry, String> tuesdayName;
  @FXML
  public TableColumn<AppointmentEntry, String> tuesdayHospital;
  @FXML
  public TableColumn<AppointmentEntry, String> tuesdayComplete;
  @FXML
  public TableColumn<AppointmentEntry, String> tuesdayDueDate;
  @FXML
  public TableColumn<AppointmentEntry, String> wednesdayName;
  @FXML
  public TableColumn<AppointmentEntry, String> wednesdayHospital;
  @FXML
  public TableColumn<AppointmentEntry, String> wednesdayComplete;
  @FXML
  public TableColumn<AppointmentEntry, String> wednesdayDueDate;
  @FXML
  public TableColumn<AppointmentEntry, String> thursdayName;
  @FXML
  public TableColumn<AppointmentEntry, String> thursdayHospital;
  @FXML
  public TableColumn<AppointmentEntry, String> thursdayComplete;
  @FXML
  public TableColumn<AppointmentEntry, String> thursdayDueDate;
  @FXML
  public TableColumn<AppointmentEntry, String> fridayName;
  @FXML
  public TableColumn<AppointmentEntry, String> fridayHospital;
  @FXML
  public TableColumn<AppointmentEntry, String> fridayComplete;
  @FXML
  public TableColumn<AppointmentEntry, String> fridayDueDate;
  @FXML
  public Button under12Btn;
  @FXML
  public Button over12Btn;
  @FXML
  public Button logoutButton;
  @FXML
  public Label bottomInfo;
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

  /**
   * Allow javafx to initalise the controller with the view
   */
  public void initialize(URL url, ResourceBundle rb) {
    this.schemaModel = new SchemaModel();
    setUpTable();
    setUpButtons();
    setCurrentDate();
    this.weekText.setText(schemaModel.getWeek());
  }

  /**
   * Get date for calculating the current week
   */
  private void setCurrentDate() {
    LocalDate localDate = LocalDate.now();
  }

  private void setUpButtons() {
    under12Btn.setOnAction(e -> {
      under12Btn.setStyle("-fx-background-color: #0096c9;" + "-fx-text-fill: white");
      over12Btn.setStyle(null);
      isUnder12 = true;
      update();
    });
    over12Btn.setOnAction(e -> {
      over12Btn.setStyle("-fx-background-color: #0096c9;" + "-fx-text-fill: white");
      under12Btn.setStyle(null);
      isUnder12 = false;
      update();
    });
  }

  private void setUpTable() {
    setUpColumns();
    setUpRows();
    fillTablesForUnder12();
    under12Btn.setStyle("-fx-background-color: #0096c9;" + "-fx-text-fill: white");
    over12Btn.setStyle(null);
  }

  private void setUpRowsForTable(TableView<AppointmentEntry> tbv) {
    tbv.setRowFactory(t -> {
      TableRow<AppointmentEntry> row = new TableRow<>();
      row.setOnMouseClicked(click -> {
        if (!row.isEmpty() && click.getButton() == MouseButton.PRIMARY && click.getClickCount() == 2) {
          AppointmentEntry appointmentEntry = row.getItem();

          primaryController.sendTo(App.updateAppointment, "appointmentEntry", appointmentEntry);
          primaryController.setPane(App.updateAppointment);

        }
      });

      return row;
    });
  }

  private void setUpRows() {
    setUpRowsForTable(carriedOverTable);
    setUpRowsForTable(thisWeekTable);
    setUpRowsForTable(mondayTable);
    setUpRowsForTable(tuesdayTable);
    setUpRowsForTable(wednesdayTable);
    setUpRowsForTable(thursdayTable);
    setUpRowsForTable(fridayTable);
  }

  public void fillTable(TableView<AppointmentEntry> tb, List<AppointmentEntry> appEntryList) {
    tb.getItems().clear();
    ObservableList<AppointmentEntry> toFill = FXCollections.observableArrayList(appEntryList);
    tb.setItems(toFill);
  }

  private void fillTablesForUnder12() {
    fillTable(carriedOverTable, schemaModel.getCarriedOverAppointments());
    fillTable(thisWeekTable, schemaModel.getPatientsUnder12());
    fillTable(mondayTable, schemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.MONDAY));
    fillTable(tuesdayTable, schemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.TUESDAY));
    fillTable(wednesdayTable, schemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.WEDNESDAY));
    fillTable(thursdayTable, schemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.THURSDAY));
    fillTable(fridayTable, schemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.FRIDAY));
  }

  public void fillTablesForOver12() {
    fillTable(carriedOverTable, schemaModel.getCarriedOverAppointments());
    fillTable(thisWeekTable, schemaModel.getPatientsOver12());
    fillTable(mondayTable, schemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.MONDAY));
    fillTable(tuesdayTable, schemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.TUESDAY));
    fillTable(wednesdayTable, schemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.WEDNESDAY));
    fillTable(thursdayTable, schemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.THURSDAY));
    fillTable(fridayTable, schemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.FRIDAY));
  }

  public void setUpColumns() {
    carriedOverName.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("name"));
    carriedOverHospital.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("vnumber"));
    carriedOverComplete.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("complete"));
    carriedOverDueDate.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("dateString"));

    thisWeekName.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("name"));
    thisWeekHospital.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("vnumber"));
    thisWeekComplete.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("complete"));
    thisWeekDueDate.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("dateString"));

    mondayName.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("name"));
    mondayHospital.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("vnumber"));
    mondayComplete.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("complete"));
    mondayDueDate.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("dateString"));

    tuesdayName.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("name"));
    tuesdayHospital.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("vnumber"));
    tuesdayComplete.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("complete"));
    tuesdayDueDate.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("dateString"));

    wednesdayName.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("name"));
    wednesdayHospital.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("vnumber"));
    wednesdayComplete.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("complete"));
    wednesdayDueDate.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("dateString"));

    thursdayName.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("name"));
    thursdayHospital.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("vnumber"));
    thursdayComplete.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("complete"));
    thursdayDueDate.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("dateString"));

    fridayName.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("name"));
    fridayHospital.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("vnumber"));
    fridayComplete.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("complete"));
    fridayDueDate.setCellValueFactory(new PropertyValueFactory<AppointmentEntry, String>("dateString"));
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
   * @param toAddKey the key of the data
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
    schemaModel.updateData();
    if (isUnder12) {
      fillTablesForUnder12();
    } else {
      fillTablesForOver12();
    }
  }

  public void setAuthenticatedUser() {
    User loggedInUser = (User) data.get("user");
    userInfo.setText("User: " + loggedInUser.getUsername());
    if (loggedInUser.getIsAdmin() == 0) {
      addNewUser.setVisible(false);
    }
  }

  public void switchToPatients(ActionEvent event) {
    primaryController.setPane(App.patients);
  }

  public void previousWeekButtonClicked(ActionEvent event) {
    schemaModel.decrementWeek();
    this.weekText.setText(schemaModel.getWeek());
    update();
  }

  public void nextWeekButtonClicked(ActionEvent event) {
    schemaModel.incrementWeek();
    this.weekText.setText(schemaModel.getWeek());
    update();
  }

  public void notificationListMenuItemClicked() {
    primaryController.setPane(App.notifyList);
  }

  public void logoutButtonClicked(ActionEvent event) {
    primaryController.setPane(App.login);
  }

  public void changePassword(ActionEvent actionEvent) {
    primaryController.sendTo(App.changePassword, "user", data.get("user"));
    primaryController.setPane("change_password");
  }

  public void editReminderEmailClicked(ActionEvent event) {
    primaryController.setPane(App.customReminder);
  }

  public void addNewUserClicked(ActionEvent event) {
    primaryController.setPane(App.addUser);
  }
}
