package seg.major.structure;

import java.time.LocalDate;

public class Patient {
    private int id;
    private String forename;
    private String surname;
    private LocalDate dob;
    private String hospitalNumber;
    private String localClinic;
    private LocalDate nextAppointment;
    private Double refreshRate;

    public static final double DEFAULT_REFRESH_RATE = 1.0;

    public Patient(String forename, String surname, LocalDate dob, String hospitalNumber, String localClinic,
            LocalDate nextAppointment, Double refreshRate) {
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.hospitalNumber = hospitalNumber;
        this.localClinic = localClinic;
        this.nextAppointment = nextAppointment;
        this.refreshRate = refreshRate;

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

    public Double getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(Double refreshRate) {
        this.refreshRate = refreshRate;
    }

}
