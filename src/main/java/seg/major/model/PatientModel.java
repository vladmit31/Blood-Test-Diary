package seg.major.model;

import seg.major.model.database.AppointmentDAO;
import seg.major.model.database.PatientDAO;
import seg.major.structure.Appointment;
import seg.major.structure.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Model class for PatientsController class.
 * Provides communication between controller and DAOs if needed.
 * @author Team Pacane
 * @version 1.0
 */
public class PatientModel {

    private List<Patient> patientList;
    private List<Appointment> appointmentList;

    public PatientModel(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public PatientModel() {
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    /**
     * Updates patientList and appointmentList with the data
     */
    public void fetchData() {
        patientList = PatientDAO.getAll();
        appointmentList = AppointmentDAO.getAll();
    }

    /**
     * Search the patients, matching by name or Vnumber
     *
     * @param searchString the search criteria
     * @return the patients matching the search criteria
     */
    public List<Patient> search(String searchString) {
        if (searchString.matches(".*\\d+.*")) {
            return searchByNumber(searchString);
        } else {
            return searchByName(searchString);
        }
    }

    /**
     * Search the patients by name
     *
     * @param name characters to search for in the patients name
     * @return patients matching the criteria
     */
    private List<Patient> searchByName(String name) {
        List<Patient> filtered = new ArrayList<>();
        patientList.stream()
                .filter(p -> (p.getForename() + " " + p.getSurname()).toLowerCase().contains(name.toLowerCase()))
                .forEach(p -> filtered.add(p));
        return filtered;
    }

    /**
     * Search the patients by Vnumber
     *
     * @param number numbers matching the Vnumber of the patient
     * @return patients matching the criteria
     */
    private List<Patient> searchByNumber(String number) {
        List<Patient> filtered = new ArrayList<>();
        patientList.stream().filter(p -> p.getHospitalNumber().toLowerCase().contains(number.toLowerCase()))
                .forEach(p -> filtered.add(p));
        return filtered;
    }

    /**
     * Filter and return all the patients aged under 12 years old
     *
     * @return patients under 12 years old
     */
    public List<Patient> under12() {
        fetchData();
        List<Patient> filtered = new ArrayList<>();
        patientList.stream().filter(p -> p.getDob().plusYears(12).isAfter(LocalDate.now())).forEach(p -> filtered.add(p));
        patientList = filtered;
        return filtered;
    }

    /**
     * Filter and return all the patients aged over 12 years old
     *
     * @return patients over 12 years old
     */
    public List<Patient> over12() {
        fetchData();
        List<Patient> filtered = new ArrayList<Patient>();
        patientList.stream().filter(p -> p.getDob().plusYears(12).isBefore(LocalDate.now())
                || p.getDob().plusYears(12).isEqual(LocalDate.now()))
                .forEach(p -> filtered.add(p));
        patientList = filtered;
        return filtered;
    }
}
