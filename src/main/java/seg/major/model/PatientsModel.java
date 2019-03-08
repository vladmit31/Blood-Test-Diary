package seg.major.model;

import seg.major.database.DatabaseConnection;
import seg.major.structure.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientsModel {

    private List<Patient> patientList;

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void fetchData() {
        patientList = DatabaseConnection.getPatients();
    }

    public List<Patient> searchByName(String name){
        List<Patient> filtered = new ArrayList<>();
        patientList.stream()
                .filter(p -> (p.getForename() + " " + p.getSurname()).toLowerCase().contains(name.toLowerCase()))
                .forEach(p -> filtered.add(p));
        return filtered;
    }

    public List<Patient> searchByNumber(String number){
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
                .filter(p -> p.getDob().plusYears(12).isAfter(LocalDate.now()) ||  p.getDob().plusYears(12).isEqual(LocalDate.now()))
                .forEach(p -> filtered.add(p));
        patientList = filtered;
        return filtered;
    }

    public List<Patient> over12(){
        fetchData();
        List<Patient> filtered = new ArrayList<Patient>();
        patientList.stream()
                .filter(p -> p.getDob().plusYears(12).isBefore(LocalDate.now()))
                .forEach(p -> filtered.add(p));
        patientList = filtered;
        return filtered;
    }
}
