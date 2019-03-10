package seg.major.structure;

import java.time.LocalDate;

public class Patient {
    private int id;
    private String forename;
    private String surname;
    private LocalDate dob;
    private String hospital_number;
    private String local_clinic;
    private LocalDate next_appointment;
    private Double refresh_rate;

    public static final double DEFAULT_REFRESH_RATE = 1.0;

    public Patient(String forename, String surname, LocalDate dob, String hospital_number, String local_clinic,
            LocalDate next_appointment, Double refresh_rate) {
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.hospital_number = hospital_number;
        this.local_clinic = local_clinic;
        this.next_appointment = next_appointment;
        this.refresh_rate = refresh_rate;

    }

    public Patient(int id, String forename, String surname, LocalDate dob, String hospital_number, String local_clinic,
            LocalDate next_appointment, Double refresh_rate) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.hospital_number = hospital_number;
        this.local_clinic = local_clinic;
        this.next_appointment = next_appointment;
        this.refresh_rate = refresh_rate;

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

    public LocalDate getNextAppointment() {
        return next_appointment;
    }

    public void setNextAppointment(LocalDate next_appointment) {
        this.next_appointment = next_appointment;
    }

    public Double getRefreshRate() {
        return refresh_rate;
    }

    public void setRefreshRate(Double refresh_rate) {
        this.refresh_rate = refresh_rate;
    }

}
