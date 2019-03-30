package seg.major.model;

import seg.major.model.database.AppointmentDAO;
import seg.major.model.database.PatientDAO;
import seg.major.structure.Appointment;
import seg.major.structure.Patient;

import java.time.LocalDate;

/**
 * Model class for AddPatientController class.
 * Provides communication between controller and DAOs if needed.
 * @author Team Pacane
 * @version 1.0
 */
public class AddPatientModel {

  /**
   * Create a new patient in the database
   *
   * @param forename
   * @param surname
   * @param dob
   * @param hospitalNumber
   * @param localClinic
   * @param diagnosis
   * @param refreshRate
   * @param nextAppointment
   * @param labName
   * @param labContact
   * @param nhsNumber
   */
  public static void createPatient(String forename, String surname, LocalDate dob, String hospitalNumber,
      String localClinic, String diagnosis, Double refreshRate, LocalDate nextAppointment, String labName, String labContact, String nhsNumber) {

    int id = PatientDAO.create(new Patient(forename, surname, dob, hospitalNumber, localClinic, diagnosis, refreshRate, labName, labContact, nhsNumber));

    createAppointment(nextAppointment,id);

  }

  /**
   * Create an appointment for the patient
   *
   * @param dueDate date of the appointment
   * @param patientId the ID of the patient
   */
  private static void createAppointment(LocalDate dueDate, int patientId) {
    AppointmentDAO.create(new Appointment(0,dueDate,patientId));
  }
}