package seg.major.model;

import java.util.List;
import seg.major.model.database.AppointmentDAO;
import seg.major.structure.Appointment;
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

  public static void updateAppointment(Appointment appointment) {
    AppointmentDAO.update(appointment);
  }
}
