package seg.major.model;

import java.util.List;

import seg.major.structure.Appointment;

public class UpdateAppointmentModel {

  public static List<Appointment> getAppointmentsForTheWeek() {

    return AppointmentDAO.getAll();
  }
}
