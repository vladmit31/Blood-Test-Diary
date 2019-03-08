package seg.major.controller;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import seg.major.model.PatientLookupModel;

import seg.major.structure.Patient;

import java.util.List;

public class PatientLookupController {
    private PatientLookupModel patientModel;
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

    public void initialize() {
        this.patientModel = new PatientLookupModel();
        setupTable();
        setupButtons();
    }

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
                    viewPatient(patient);
                }
            });
            return row;
        });
    }

    private void viewPatient(Patient patient){
        System.out.println(patient.getForename() + " " + patient.getSurname());
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
        });
        over12Button.setOnAction(e -> {
            over12Button.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
            under12Button.setStyle(null);
            fillTable(patientModel.over12());
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
            patientTable.getItems().add(patient);
        }
    }

    private void search(String searchString) {
        fillTable(patientModel.search(searchString));
    }

    public void refresh(){
        patientModel.fetchData();
        fillTable();
    }
}
