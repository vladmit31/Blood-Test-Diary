package seg.major.structure;

import java.time.LocalDate;
/**
 * Models patient entries for easy display
 * in the tables from the application.
 * @author Team Pacane
 * @version 1.0
 */
public class PatientEntry {

    private final int patientID;
    private final int appointmentID;

    private String forename;
    private String surname;
    private String hospitalNumber;
    private String localClinic;
    private String diagnosis;
    private String nextAppointment;
    private String lastNotified;

    public PatientEntry(int patientID, int appointmentID, String forename, String surname, String hospitalNumber,
            String localClinic, String nextAppointment, String diagnosis, String lastNotified) {
        this.patientID = patientID;
        this.appointmentID = appointmentID;
        this.forename = forename;
        this.surname = surname;
        this.hospitalNumber = hospitalNumber;
        this.localClinic = localClinic;
        this.nextAppointment = nextAppointment;
        this.diagnosis = diagnosis;
        this.lastNotified = lastNotified;
    }

    public String getLastNotified() {
        return lastNotified;
    }

    public void setLastNotified(String lastNotified) {
        this.lastNotified = lastNotified;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getPatientID() {
        return patientID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNumber(String hospitalNumber) {
        this.hospitalNumber = hospitalNumber;
    }

    public String getLocalClinic() {
        return localClinic;
    }

    public void setLocalClinic(String localClinic) {
        this.localClinic = localClinic;
    }

    public String getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(String nextAppointment) {
        this.nextAppointment = nextAppointment;
    }
}
