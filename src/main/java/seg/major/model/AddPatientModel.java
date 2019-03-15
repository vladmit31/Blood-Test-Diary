package seg.major.model;

import java.time.LocalDate;

import seg.major.model.database.AppointmentDAO;
import seg.major.structure.Appointment;
import seg.major.structure.Patient;
import seg.major.model.database.PatientDAO;

public class AddPatientModel {

  public AddPatientModel() {
  }

  public static void createPatient(String forename, String surname, LocalDate dob, String hospitalNumber,
      String localClinic, /*LocalDate nextAppointment,*/ Double refreshRate, LocalDate nextAppointment) {

    int id = PatientDAO.create(new Patient(forename, surname, dob, hospitalNumber, localClinic, /*nextAppointment,*/ refreshRate));

    createAppointment(nextAppointment,id);

  }

  private static void createAppointment(LocalDate dueDate, int patientId) {
    AppointmentDAO.create(new Appointment(0,dueDate,patientId));
  }
}