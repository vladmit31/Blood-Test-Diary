package seg.major.controller;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    public TableColumn local;
    public TableColumn nextAppointment;
    public Button under12Button;
    public Button over12Button;

    public void initialize() {
        this.patientModel = new PatientLookupModel();
        patientModel.fetchData();
        setButtons();
        fillTable();
    }

    private void setButtons() {
        under12Button.setOnAction(e -> {
            fillTable(patientModel.under12());
        });
        over12Button.setOnAction(e -> {
            fillTable(patientModel.over12());
        });
        searchButton.setOnAction(e -> {
            search(searchField.getText());
        });
        //TODO filter button
    }

    private void fillTable(){
        // may need to assign column to attributes using setCellValueFactory
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
