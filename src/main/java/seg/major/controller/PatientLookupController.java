package seg.major.controller;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
        patientModel.fetchData();
        setColumns();
        setButtons();
        fillTable(patientModel.under12());
        under12Button.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
    }

    private void setColumns(){
        forename.setCellValueFactory(new PropertyValueFactory("forename"));
        surname.setCellValueFactory(new PropertyValueFactory("surname"));
        hospitalNumber.setCellValueFactory(new PropertyValueFactory("hospitalNumber"));
        localClinic.setCellValueFactory(new PropertyValueFactory("localClinic"));
        nextAppointment.setCellValueFactory(new PropertyValueFactory("nextAppointment"));
    }

    private void setButtons() {
        under12Button.setOnAction(e -> {
            under12Button.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
            over12Button.setStyle(null);
            fillTable(patientModel.under12());
        });
        over12Button.setOnAction(e -> {
            under12Button.setStyle(null);
            over12Button.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
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
        fillTable();
    }
}
