package seg.major.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import seg.major.model.PatientLookupModel;

import seg.major.structure.Patient;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.List;

public class PatientLookupController implements Initializable, ControllerInterface {
    private PatientLookupModel patientModel;
    public TextField searchField;
    public Button searchButton;
    public Button filterButton;
    public TableView<Patient> patientTable;
    public TableColumn<Patient, String> forename;
    public TableColumn<Patient, String> surname;
    public TableColumn<Patient, String> hospitalNumber;
    public TableColumn<Patient, String> localClinic;
    public TableColumn<Patient, String> nextAppointment;
    public Button under12Button;
    public Button over12Button;

    private PrimaryController primaryController;
    private Map<String, Object> data = new HashMap<>();

    /** ---------- Inherited / Implemented ---------- */
    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL url, ResourceBundle rb) {
        this.patientModel = new PatientLookupModel();
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
            TableRow<Patient> row = new TableRow<>();
            row.setOnMouseClicked(click -> {
                if (!row.isEmpty() && click.getButton() == MouseButton.PRIMARY && click.getClickCount() == 2) {
                    Patient patient = row.getItem();
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
        });
        over12Button.setOnAction(e -> {
            over12Button.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
            under12Button.setStyle(null);
            fillTable(patientModel.over12());
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
        fillTable();
    }
}
