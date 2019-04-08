package seg.major.model;

import seg.major.model.database.AppointmentDAO;
import seg.major.structure.Appointment;

import java.util.List;
/**
 * Model class for UpdateAppointmentController class.
 * Provides communication between controller and DAOs if needed.
 * @author Team Pacane
 * @version 1.0
 */
public class UpdateAppointmentModel {

  public static List<Appointment> getAppointmentsForTheWeek() {

    return AppointmentDAO.getAll();
  }

  /**
   * Update a given appointment
   * @param toUpdate the appointment to update
   */
  public static void updateAppointment(Appointment toUpdate) {
    AppointmentDAO.update(toUpdate);
  }
}
