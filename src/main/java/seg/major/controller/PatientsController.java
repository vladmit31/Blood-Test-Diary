package seg.major.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ResourceBundle;
import java.net.URL;
import java.time.LocalDate;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import seg.major.App;
import seg.major.model.PatientsModel;

import seg.major.structure.Patient;

/**
 * PatientsController acts as the controller for the patients.fxml file
 */
public class PatientsController implements Initializable, ControllerInterface {

  public MenuItem switchToDiary;
  public Text infoText;
  public FlowPane infoBar;
  public BorderPane infoFieldBorder;
  private PrimaryController primaryController;
  private Map<String, Object> data = new HashMap<>();

  private static PatientsModel patientModel = new PatientsModel();
  public TextField searchField;
  public Button searchButton;
  public Button filterButton;
  public TableView<Patient> patientTable;
  public TableColumn<Patient, String> forename;
  public TableColumn<Patient, String> surname;
  public TableColumn<Patient, String> hospitalNumber;
  public TableColumn<Patient, String> localClinic;
  public TableColumn<Patient, LocalDate> nextAppointment;
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
    patientModel = new PatientsModel();
    infoText.setText("");
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
    if (isUnder12) {
      fillTable(patientModel.getUnder12());
    } else {
      fillTable(patientModel.getOver12());
    }
  }

  /** ---------- Inherited / Implemented ---------- */

  private void setupTable() {
    setupColumns();
    setupRows();
    fillTable(patientModel.getUnder12());
    under12Button.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
    patientTable.setPlaceholder(new Label("No patients found"));
  }

  private void setupRows() {
    patientTable.setRowFactory(t -> {
      TableRow<Patient> row = new TableRow<>();
      // listen for click events on rows and open the update patients pane if an entry
      // is double clicked
      row.setOnMouseClicked(click -> {
        if (!row.isEmpty() && click.getButton() == MouseButton.PRIMARY && click.getClickCount() == 2) {
          Patient patientEntry = row.getItem();
          primaryController.sendTo(App.updatePatient, "patient", patientEntry);
          primaryController.setPane(App.updatePatient);
        }
      });
      return row;
    });
  }

  private void setupColumns() {
    forename.setCellValueFactory(new PropertyValueFactory<>("forename"));
    surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
    hospitalNumber.setCellValueFactory(new PropertyValueFactory<>("hospitalNumber"));
    localClinic.setCellValueFactory(new PropertyValueFactory<>("localClinic"));
    nextAppointment.setCellValueFactory(new PropertyValueFactory<>("nextAppointment"));
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
      primaryController.setPane(App.schema);
    });
    searchButton.setOnAction(e -> {
      search(searchField.getText());
    });
    // TODO filter button
  }

  private void fillTable() {
    patientTable.getItems().clear();
    for (Patient patient : patientModel.getPatientList()) {
      patientTable.getItems().add(patient);
    }
  }

  private void fillTable(List<Patient> patients) {
    patientTable.getItems().clear();
    for (Patient patient : patients) {

      patientTable.getItems().add(patient);

    }
  }

  private void search(String searchString) {
    if (searchString.matches(".*\\d+.*")) {
      searchNumber(searchString);
    } else {
      searchName(searchString);
    }
  }

  private void searchNumber(String number) {
    fillTable(patientModel.searchByNumber(number));
  }

  private void searchName(String name) {
    fillTable(patientModel.searchByName(name));
  }

  public void refresh() {
    patientModel.fetchData();
    if (isUnder12) {
      fillTable(patientModel.under12());
    } else {
      fillTable(patientModel.over12());
    }
  }

  public void setInfoText(String text) {
    infoText.setText(text);
  }

}
