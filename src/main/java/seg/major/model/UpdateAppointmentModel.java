package seg.major.model;

import seg.major.structure.Appointment;

public class UpdateAppointmentModel {

  public static Appointment[] getAppointmentsForTheWeek() {

    return AppointmentDAO.getAll();
  }
}
