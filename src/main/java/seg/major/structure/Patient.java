package seg.major.structure;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Patient {
    private int id;
    private String forename;
    private String surname;
    private LocalDate dob;
    private String hospital_number;
    private String local_clinic;
    private String diagnosis;
    private Double refresh_rate;
    private String labName;
    private String labContact;
    private String nhsNumber;
    private LocalDate lastTimeNotified;

    public static final double DEFAULT_REFRESH_RATE = 1.0;

    public Patient(String forename, String surname, LocalDate dob, String hospital_number, String local_clinic,
            String diagnosis, Double refresh_rate, String labName, String labContact, String nhsNumber, LocalDate lastTimeNotified) {
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.hospital_number = hospital_number;
        this.local_clinic = local_clinic;
        this.diagnosis = diagnosis;
        this.refresh_rate = refresh_rate;
        this.labName = labName;
        this.labContact = labContact;
        this.nhsNumber = nhsNumber;
        this.lastTimeNotified = lastTimeNotified;
    }

    public Patient(int id, String forename, String surname, LocalDate dob, String hospital_number, String local_clinic,
            String diagnosis, Double refresh_rate, String labName, String labContact, String nhsNumber, LocalDate lastTimeNotified) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.hospital_number = hospital_number;
        this.local_clinic = local_clinic;
        this.diagnosis = diagnosis;
        this.refresh_rate = refresh_rate;
        this.labName = labName;
        this.labContact = labContact;
        this.nhsNumber = nhsNumber;
        this.lastTimeNotified = lastTimeNotified;
    }

    public Patient(String forename, String surname, LocalDate dob, String hospital_number, String local_clinic,
                   String diagnosis, Double refresh_rate, String labName, String labContact, String nhsNumber) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.hospital_number = hospital_number;
        this.local_clinic = local_clinic;
        this.diagnosis = diagnosis;
        this.refresh_rate = refresh_rate;
        this.labName = labName;
        this.labContact = labContact;
        this.nhsNumber = nhsNumber;
    }

    public LocalDate getLastTimeNotified() {
        return lastTimeNotified;
    }

    public void setLastTimeNotified(LocalDate lastTimeNotified) {
        this.lastTimeNotified = lastTimeNotified;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getLabContact() {
        return labContact;
    }

    public void setLabContact(String labContact) {
        this.labContact = labContact;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    public void setNhsNumber(String nhsNumber) {
        this.nhsNumber = nhsNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getID() {
        return id;
    }

    public void setID(int toSet) {
        this.id = toSet;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getHospitalNumber() {
        return hospital_number;
    }

    public void setHospitalNumber(String hospital_number) {
        this.hospital_number = hospital_number;
    }

    public String getLocalClinic() {
        return local_clinic;
    }

    public void setLocalClinic(String local_clinic) {
        this.local_clinic = local_clinic;
    }

    /*public LocalDate getNextAppointment() {
        return next_appointment;
    }

    public void setNextAppointment(LocalDate next_appointment) {
        this.next_appointment = next_appointment;
    }*/

    public Double getRefreshRate() {
        return refresh_rate;
    }

    public void setRefreshRate(Double refresh_rate) {
        this.refresh_rate = refresh_rate;
    }

    public String toString(){
        return this.getSurname() + " " + this.getForename();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id &&
                Objects.equals(forename, patient.forename) &&
                Objects.equals(surname, patient.surname) &&
                Objects.equals(dob, patient.dob) &&
                Objects.equals(hospital_number, patient.hospital_number) &&
                Objects.equals(local_clinic, patient.local_clinic) &&
                Objects.equals(diagnosis, patient.diagnosis) &&
                Objects.equals(refresh_rate, patient.refresh_rate) &&
                Objects.equals(labName, patient.labName) &&
                Objects.equals(labContact, patient.labContact) &&
                Objects.equals(nhsNumber, patient.nhsNumber) &&
                Objects.equals(lastTimeNotified, patient.lastTimeNotified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, forename, surname, dob, hospital_number, local_clinic, diagnosis, refresh_rate, labName, labContact, nhsNumber, lastTimeNotified);
    }
}
