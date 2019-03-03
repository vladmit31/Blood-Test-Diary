package seg.major.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seg.major.database.DatabaseConnection;
import seg.major.structure.Patient;

public class PatientLookupModel {

    private List<Patient> patientList;

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void fetchData() {
        patientList = DatabaseConnection.getPatients();
    }

    /*
     * search and filter methods
     * TODO handle failed searches
     *
     */
    public List searchByName(String name){
        List<Patient> filtered = new ArrayList<Patient>();
        patientList.stream()
                .filter(p -> (p.getForename() + " " + p.getSurname()).contains(name))
                .forEach(p -> filtered.add(p));
        return filtered;
    }

    public List searchByNumber(String number){
        List<Patient> filtered = new ArrayList<Patient>();
        patientList.stream()
                .filter(p -> p.getHospitalNumber().contains(number))
                .forEach(p -> filtered.add(p));
        return filtered;
    }

    public List over12(){
        List<Patient> filtered = new ArrayList<Patient>();
        patientList.stream()
                .filter(p -> p.getDob().plusYears(12).isAfter(LocalDate.now()) ||  p.getDob().plusYears(12).isEqual(LocalDate.now()))
                .forEach(p -> filtered.add(p));
        return filtered;
    }

    public List under12(){
        List<Patient> filtered = new ArrayList<Patient>();
        patientList.stream()
                .filter(p -> p.getDob().plusYears(12).isBefore(LocalDate.now()))
                .forEach(p -> filtered.add(p));
        return filtered;
    }

}
