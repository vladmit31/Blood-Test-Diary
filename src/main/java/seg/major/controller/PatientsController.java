package seg.major.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import seg.major.App;
import seg.major.model.PatientModel;
import seg.major.model.database.AppointmentDAO;
import seg.major.model.database.PatientDAO;
import seg.major.model.util.DateReverser;
import seg.major.structure.Appointment;
import seg.major.structure.Patient;
import seg.major.structure.PatientEntry;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * PatientsController acts as the controller for the patients.fxml file
 * @author Team Pacane
 * @version 1.0
 */
public class PatientsController implements Initializable, ControllerInterface {

  private PrimaryController primaryController;
  private Map<String, Object> data = new HashMap<>();
  private boolean isUnder12 = true;
  private PatientModel patientModel;

  @FXML
  public MenuItem switchToDiary;
  @FXML
  public Text infoText;
  @FXML
  public FlowPane infoBar;
  @FXML
  public BorderPane infoFieldBorder;
  @FXML
  public TextField searchField;
  @FXML
  public Button searchButton;
  @FXML
  public Button filterButton;
  @FXML
  public TableView<PatientEntry> patientTable;
  @FXML
  public TableColumn<PatientEntry, String> forename;
  @FXML
  public TableColumn<PatientEntry, String> surname;
  @FXML
  public TableColumn<PatientEntry, String> hospitalNumber;
  @FXML
  public TableColumn<PatientEntry, String> localClinic;
  @FXML
  public TableColumn<PatientEntry, String > diagnosis;
  @FXML
  public TableColumn<PatientEntry, LocalDate> nextAppointment;
  @FXML
  public Button under12Button;
  @FXML
  public Button over12Button;
  @FXML
  public MenuItem addNewPatientMenuItem;

  /**
   * Allow javafx to initalise the controller with the view
   */
  public void initialize(URL url, ResourceBundle rb) {
    this.patientModel = new PatientModel();
    infoText.setText("");
    patientModel.fetchData();
    setupTable();
    setupButtons();
    setUpDynamicSearchField();
  }

  /**
   * Setup the search field to search on each new keystroke
   */
  private void setUpDynamicSearchField(){
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      searchButton.fire();
    });
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
    if (isUnder12) {
      fillTable(patientModel.under12());
    } else {
      fillTable(patientModel.over12());
    }
  }


  private void setupTable() {
    setupColumns();
    setupRows();
    fillTable(patientModel.under12());
    under12Button.setStyle("-fx-background-color: #0096c9;" + "-fx-text-fill: white");
    // patientTable.setPlaceholder(new Label("No patients found"));
  }

  private void setupRows() {
    patientTable.setRowFactory(t -> {
      TableRow<PatientEntry> row = new TableRow<>();
      row.setOnMouseClicked(click -> {
        if (!row.isEmpty() && click.getButton() == MouseButton.PRIMARY && click.getClickCount() == 2) {
          PatientEntry patientEntry = row.getItem();
          viewPatient(patientEntry);
        }
      });
      return row;
    });
  }

  private void viewPatient(PatientEntry patientEntry){
    primaryController.sendTo(App.updatePatient,"patient", PatientDAO.getByID(patientEntry.getPatientID()));
    primaryController.sendTo(App.updatePatient, "appointment", AppointmentDAO.getById(patientEntry.getAppointmentID()));
    primaryController.setPane(App.updatePatient);
  }

  private void setupColumns() {
    forename.setCellValueFactory(new PropertyValueFactory<>("forename"));
    surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
    hospitalNumber.setCellValueFactory(new PropertyValueFactory<>("hospitalNumber"));
    diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
    localClinic.setCellValueFactory(new PropertyValueFactory<>("localClinic"));
    nextAppointment.setCellValueFactory(new PropertyValueFactory<>("nextAppointment"));
  }

  private void setupButtons() {
    under12Button.setOnAction(e -> {
      under12Button.setStyle("-fx-background-color: #0096c9;" + "-fx-text-fill: white");
      over12Button.setStyle(null);
      isUnder12 = true;
      fillTable(patientModel.under12());
      update();
    });
    over12Button.setOnAction(e -> {
      over12Button.setStyle("-fx-background-color: #0096c9;" + "-fx-text-fill: white");
      under12Button.setStyle(null);
      isUnder12 = false;
      fillTable(patientModel.over12());
      update();
    });
    switchToDiary.setOnAction(e -> {
      primaryController.setPane(App.schema);
    });
    searchButton.setOnAction(e -> {
      search(searchField.getText());
    });
  }

  private void fillTable(List<Patient> patients) {
    patientTable.getItems().clear();
    for (Patient patient : patients) {
      for (Appointment appointment : patientModel.getAppointmentList()) {
        if(appointment.getPatientID() == patient.getID()) {
          patientTable.getItems().add(new PatientEntry(patient.getID(),appointment.getID(),
                  patient.getForename(),patient.getSurname(),patient.getHospitalNumber(),
                  patient.getLocalClinic(), DateReverser.reverseDateFormat(appointment.getDueDate()),
                  patient.getDiagnosis(), DateReverser.reverseDateFormat(patient.getLastTimeNotified())));
        }
      }
    }
  }

  private void search(String searchString) {
    fillTable(patientModel.search(searchString));
  }

  public void addNewPatientMenuItemClicked() {
    primaryController.setPane(App.addPatient);
  }

  public void setInfoText(String text) {
    infoText.setText(text);
  }

}
