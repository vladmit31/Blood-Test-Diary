package seg.major.structure;

import java.time.LocalDate;

public class PatientEntry {

    private final int patientId;
    private final int appointmentId;

    private String forename;
    private String surname;
    private String hospitalNumber;
    private String localClinic;
    private LocalDate nextAppointment;

    public PatientEntry(int patientId, int appointmentId, String forename, String surname, String hospitalNumber, String localClinic, LocalDate nextAppointment) {
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.forename = forename;
        this.surname = surname;
        this.hospitalNumber = hospitalNumber;
        this.localClinic = localClinic;
        this.nextAppointment = nextAppointment;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getAppointmentId() {
        return appointmentId;
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

    public LocalDate getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(LocalDate nextAppointment) {
        this.nextAppointment = nextAppointment;
    }
}
