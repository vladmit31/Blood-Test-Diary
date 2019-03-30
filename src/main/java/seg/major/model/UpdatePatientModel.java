package seg.major.model;

import seg.major.model.database.AppointmentDAO;
import seg.major.model.database.PatientDAO;
import seg.major.structure.Appointment;
import seg.major.structure.Patient;
/**
 * Model class for UpdatePatientController class.
 * Provides communication between controller and DAOs if needed.
 * @author Team Pacane
 * @version 1.0
 */
public class UpdatePatientModel {

    /**
     * Update a given patient
     * @param toUpdate the patient to update
     */
    public static void updatePatient(Patient patient) {
        PatientDAO.update(patient);
    }

    /**
     * Update a given appointment
     * @param toUpdate the appointment to update
     */
    public static void updateAppointment(Appointment appointment) {
        AppointmentDAO.update(appointment);
    }

    /**
     * Get a patient by ID
     *
     * @param toGet the ID to get
     * @return the patient
     */
    public static Patient getByID(int toGet) {
        return PatientDAO.getByID(toGet);
    }
}
