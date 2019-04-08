package seg.major.model;

import seg.major.model.database.AppointmentDAO;
import seg.major.model.database.ContactDAO;
import seg.major.model.database.PatientDAO;
import seg.major.model.util.DateReverser;
import seg.major.structure.Appointment;
import seg.major.structure.Contact;
import seg.major.structure.Patient;
import seg.major.structure.PatientEntry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Model class for NotificationListController class.
 * Provides communication between controller and DAOs if needed.
 * @author Team Pacane
 * @version 1.0
 */
public class NotificationListModel {

    /**
     * Get all the appointments which have been missed
     *
     * @return
     */
    public List<PatientEntry> getCarriedOverAppointmentEntries(){

        List<PatientEntry> toReturn = new ArrayList<PatientEntry>();

        for (Patient patient : PatientDAO.getAll()) {
            for (Appointment appointment : AppointmentDAO.getAll()) {
                if(appointment.getPatientID() == patient.getID() && isCarriedOver(appointment)) {
                    toReturn.add(new PatientEntry(patient.getID(),appointment.getID(),
                            patient.getForename(),patient.getSurname(),patient.getHospitalNumber(),
                            patient.getLocalClinic(), DateReverser.reverseDateFormat(appointment.getDueDate()),
                            patient.getDiagnosis(), DateReverser.reverseDateFormat(patient.getLastTimeNotified())));
                }
            }
        }

        return toReturn;
    }

    /**
     * Check if an appointment has been missed and so should be carried over
     *
     * @param toCheck appointment to check
     * @return true if overdue
     */
    private boolean isCarriedOver(Appointment toCheck){
        return (toCheck.getStatus() == 0) && (toCheck.getDueDate().compareTo(LocalDate.now()) < 0);
    }

    /**
     * Get a patient by ID
     *
     * @param toGet ID of patient to get
     * @return the patient
     */
    public static Patient getPatientByID(int toGet){
        return PatientDAO.get(toGet);
    }

    /**
     * Get all contacts for a patient
     *
     * @return a list of contacts for the patient
     */
    public static List<Contact> getAllContacts(){
        return ContactDAO.getAll();
    }
}
