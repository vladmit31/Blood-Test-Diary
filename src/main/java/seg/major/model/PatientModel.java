package seg.major.model;

import seg.major.structure.Appointment;
import seg.major.structure.Patient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import seg.major.model.database.PatientDAO;
import seg.major.model.database.AppointmentDAO;

public class PatientModel {

    private List<Patient> patientList;

    private List<Appointment> appointmentList;

    public PatientModel() {
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void fetchData() {
        patientList = PatientDAO.getAll();
        appointmentList = AppointmentDAO.getAll();
    }

    public List<Patient> searchByName(String name) {
        List<Patient> filtered = new ArrayList<>();
        patientList.stream()
                .filter(p -> (p.getForename() + " " + p.getSurname()).toLowerCase().contains(name.toLowerCase()))
                .forEach(p -> filtered.add(p));
        return filtered;
    }

    public List<Patient> searchByNumber(String number) {
        List<Patient> filtered = new ArrayList<>();
        patientList.stream().filter(p -> p.getHospitalNumber().toLowerCase().contains(number.toLowerCase()))
                .forEach(p -> filtered.add(p));
        return filtered;
    }

    public List<Patient> under12() {
        fetchData();
        List<Patient> filtered = new ArrayList<>();
        patientList.stream().filter(p -> p.getDob().plusYears(12).isAfter(LocalDate.now())).forEach(p -> filtered.add(p));
        patientList = filtered;
        return filtered;
    }

    public List<Patient> getUnder12() {
        return PatientDAO.getAll();
    }

    public List<Patient> getOver12() {
        return PatientDAO.getAll();
    }

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
