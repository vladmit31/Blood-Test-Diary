package seg.major.controller;

import java.util.List;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.util.Pair;
import seg.major.App;
import seg.major.model.PatientsModel;
import seg.major.structure.Appointment;
import seg.major.structure.Patient;
import seg.major.structure.PatientEntry;

import javax.xml.validation.Schema;

/**
 * PatientsController acts as the controller for the patients.fxml file
 */
public class PatientsController implements Initializable, ViewsController {


  public MenuItem switchToDiary;
  private PrimaryController primaryController;

  private PatientsModel patientModel;

  public TextField searchField;
  public Button searchButton;
  public Button filterButton;
  public TableView patientTable;
  public TableColumn forename;
  public TableColumn surname;
  public TableColumn hospitalNumber;
  public TableColumn localClinic;
  public TableColumn nextAppointment;
  public Button under12Button;
  public Button over12Button;

  boolean isUnder12 = true;


  /** ---------- FXML ---------- */



  /** ---------- FXML ---------- */

  /** ---------- Inherited / Implemented ---------- */
  /**
   * Allow javafx to initalise the controller with the view
   */
  public void initialize(URL url, ResourceBundle rb) {
    this.patientModel = new PatientsModel();
    patientModel.fetchData();
    setupTable();
    setupButtons();
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

  private void setupTable() {
    setupColumns();
    setupRows();
    fillTable(patientModel.under12());
    under12Button.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
    patientTable.setPlaceholder(new Label("No patients found"));
  }

  private void setupRows() {
    patientTable.setRowFactory(t -> {
      TableRow<PatientEntry> row = new TableRow<>();
      row.setOnMouseClicked(click -> {
        if (!row.isEmpty() && click.getButton() == MouseButton.PRIMARY && click.getClickCount() == 2) {
          PatientEntry patientEntry = row.getItem();
          viewPatient(patientEntry);

          UpdatePatientController upc = (UpdatePatientController) primaryController.getControllerByName(App.updatePatient);
          upc.setData(new Pair<Integer,Integer>(patientEntry.getPatientId(),patientEntry.getAppointmentId()));
          primaryController.setPane(App.updatePatient);
          upc.setUp();
        }
      });
      return row;
    });
  }

  private void viewPatient(PatientEntry patientEntry){
    System.out.println(patientEntry.getPatientId() + " " + patientEntry.getForename() + " " + patientEntry.getSurname());
  }

  private void setupColumns(){
    forename.setCellValueFactory(new PropertyValueFactory("forename"));
    surname.setCellValueFactory(new PropertyValueFactory("surname"));
    hospitalNumber.setCellValueFactory(new PropertyValueFactory("hospitalNumber"));
    localClinic.setCellValueFactory(new PropertyValueFactory("localClinic"));
    nextAppointment.setCellValueFactory(new PropertyValueFactory("nextAppointment"));
  }

  private void setupButtons() {
    under12Button.setOnAction(e -> {
      under12Button.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
      over12Button.setStyle(null);
      fillTable(patientModel.under12());
      isUnder12 = true;
    });
    over12Button.setOnAction(e -> {
      over12Button.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
      under12Button.setStyle(null);
      fillTable(patientModel.over12());
      isUnder12 = false;
    });
    switchToDiary.setOnAction(e -> {
      SchemaController sc = (SchemaController) primaryController.getControllerByName(App.schema);
      sc.refresh();
      primaryController.setPane(App.schema);
      sc.refresh();
    });
    searchButton.setOnAction(e -> {
      search(searchField.getText());
    });
    //TODO filter button
  }

  private void fillTable(){
    patientTable.getItems().clear();
    for (Patient patient : patientModel.getPatientList()) {
      patientTable.getItems().add(patient);
    }
  }

  private void fillTable(List<Patient> patients){
    patientTable.getItems().clear();
    for (Patient patient : patients) {
      for (Appointment appointment : patientModel.getAppointmentList()) {
          if(appointment.getPatientId() == patient.getId()) {
            patientTable.getItems().add(new PatientEntry(patient.getId(),appointment.getAppId(),
                    patient.getForename(),patient.getSurname(),patient.getHospitalNumber(),patient.getLocalClinic(),appointment.getDueDate()));
          }
      }
    }
  }

  private void search(String searchString){
    if (searchString.matches(".*\\d+.*")) {
      searchNumber(searchString);
    }
    else{
      searchName(searchString);
    }
  }

  private void searchNumber(String number) {
    fillTable(patientModel.searchByNumber(number));
  }

  private void searchName(String name){
    fillTable(patientModel.searchByName(name));
  }

  public void refresh(){
    patientModel.fetchData();
    if(isUnder12) {
      fillTable(patientModel.under12());
    }else {
      fillTable(patientModel.over12());
    }
  }

}
