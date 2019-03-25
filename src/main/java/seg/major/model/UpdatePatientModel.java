package seg.major.model;

import seg.major.model.database.AppointmentDAO;
import seg.major.structure.Appointment;
import seg.major.structure.Patient;
import seg.major.model.database.PatientDAO;

public class UpdatePatientModel {

    public UpdatePatientModel() {
    }

    public static void updatePatient(Patient patient) {
        PatientDAO.update(patient);
    }

    public static void updateAppointment(Appointment appointment) {
        AppointmentDAO.update(appointment);
    }

    public static Patient getByID(int toGet) {
        return PatientDAO.getByID(toGet);
    }
}
