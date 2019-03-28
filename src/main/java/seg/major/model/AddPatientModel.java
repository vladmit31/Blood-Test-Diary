package seg.major.model;

import java.time.LocalDate;

import seg.major.model.database.AppointmentDAO;
import seg.major.structure.Appointment;
import seg.major.structure.Patient;
import seg.major.model.database.PatientDAO;

/**
 * Model class for AddPatientController class.
 * Provides communication between controller and DAOs if needed.
 * @author Team Pacane
 * @version 1.0
 */
public class AddPatientModel {

  public AddPatientModel() {
  }

  public static void createPatient(String forename, String surname, LocalDate dob, String hospitalNumber,
      String localClinic, String diagnosis, Double refreshRate, LocalDate nextAppointment, String labName, String labContact, String nhsNumber) {

    int id = PatientDAO.create(new Patient(forename, surname, dob, hospitalNumber, localClinic, diagnosis, refreshRate, labName, labContact, nhsNumber));

    createAppointment(nextAppointment,id);

  }

  private static void createAppointment(LocalDate dueDate, int patientId) {
    AppointmentDAO.create(new Appointment(0,dueDate,patientId));
  }
}