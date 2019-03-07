package seg.major.model;

import java.time.LocalDate;

import seg.major.structure.Patient;

public class AddPatientModel {
  public AddPatientModel() {
  }

  public Patient createPatient(String forename, String surname, LocalDate dob, String hospitalNumber,
      String localClinic, LocalDate nextAppointment) {
    Patient patient = new Patient(forename, surname, dob, hospitalNumber, localClinic, nextAppointment);
    return patient;

  }
}