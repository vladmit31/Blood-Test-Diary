package seg.major.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seg.major.database.DatabaseConnection;
import seg.major.structure.Patient;

public class PatientLookupModel {

    private List<Patient> patientList;

    public PatientLookupModel() {
    }

    public PatientLookupModel(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void fetchData() {
        patientList = DatabaseConnection.getPatients();
    }

    public List<Patient> search(String searchString) {
        if (searchString.matches(".*\\d+.*")) {
            return searchByNumber(searchString);
        }
        else {
            return searchByName(searchString);
        }
    }

    private List<Patient> searchByName(String name){
        List<Patient> filtered = new ArrayList<>();
        patientList.stream()
                .filter(p -> (p.getForename() + " " + p.getSurname()).toLowerCase().contains(name.toLowerCase()))
                .forEach(p -> filtered.add(p));
        return filtered;
    }

    private List<Patient> searchByNumber(String number){
        List<Patient> filtered = new ArrayList<>();
        patientList.stream()
                .filter(p -> p.getHospitalNumber().toLowerCase().contains(number.toLowerCase()))
                .forEach(p -> filtered.add(p));
        return filtered;
    }

    public List<Patient> under12(){
        fetchData();
        List<Patient> filtered = new ArrayList<>();
        patientList.stream()
                .filter(p -> p.getDob().plusYears(12).isAfter(LocalDate.now()))
                .forEach(p -> filtered.add(p));
        patientList = filtered;
        return filtered;
    }

    public List<Patient> over12(){
        fetchData();
        List<Patient> filtered = new ArrayList<Patient>();
        patientList.stream()
                .filter(p -> p.getDob().plusYears(12).isBefore(LocalDate.now()) ||  p.getDob().plusYears(12).isEqual(LocalDate.now()))
                .forEach(p -> filtered.add(p));
        patientList = filtered;
        return filtered;
    }

}
