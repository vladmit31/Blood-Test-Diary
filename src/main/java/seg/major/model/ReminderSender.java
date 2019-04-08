package seg.major.model;

import seg.major.controller.util.EmailSender;
import seg.major.model.database.AppointmentDAO;
import seg.major.model.database.ContactDAO;
import seg.major.model.database.PatientDAO;
import seg.major.model.util.EmailBuilder;
import seg.major.structure.Appointment;
import seg.major.structure.Contact;
import seg.major.structure.Patient;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;


/**
 * Sends reminders for people that have appointments
 * in the next 2 days.
 * @author Team Pacane
 * @version 1.0
 */
public class ReminderSender {

    /**
     * Send out reminder emails to all patients set to notify
     */
    public static void sendRemainders(){
        List<Patient> patients = PatientDAO.getAll();
        List<Appointment> appointments = AppointmentDAO.getAll();
        HashMap<Contact, String> toNotifiy = new HashMap<>();
        for(Patient patient : patients){
            for(Appointment appointment : appointments){
                if(patient.getID() == appointment.getPatientID() &&
                        notNotifiedRecently(patient, appointment) && soonEnough(appointment)){
                    List<Contact> patientContacts = ContactDAO.getByPatientId(patient.getID());
                    for(Contact contact : patientContacts){
                        toNotifiy.put(contact, EmailBuilder.generate(contact, patient, appointment, EditNotificationEmailModel.EmailType.REMINDER));
                    }
                    patient.setLastTimeNotified(LocalDate.now());
                    PatientDAO.update(patient);
                }
            }
        }

        if(toNotifiy.size() != 0){

            Iterator it = toNotifiy.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
            }

            StringBuilder sb = new StringBuilder();

            sb.append("Your email has been sent to: \n");

            Iterator it1 = toNotifiy.entrySet().iterator();
            while (it1.hasNext()) {
                Map.Entry pair = (Map.Entry)it1.next();
                Contact contact = (Contact)pair.getKey();
                String generatedContent = (String)pair.getValue();
                sb.append(contact.getForename() + " " + contact.getSurname() + "\n");
            }

            EmailSender sender = new EmailSender(toNotifiy, "Reminder");
            sender.start();
        }
    }

    /**
     * Check if the appointment is soon enough to not be an issue
     *
     * @param appointment the appointment to check
     * @return true if the appointment is soon
     */
    private static boolean soonEnough(Appointment appointment){
        LocalDate today = LocalDate.now();

        LocalDate appointmentDay = appointment.getDueDate();

        return today.plus(1, ChronoUnit.DAYS).compareTo(appointmentDay) == 0 ||
                today.plus(2, ChronoUnit.DAYS).compareTo(appointmentDay) == 0;

    }

    /**
     * Check if the patient was recently notified as they should not be spammed
     *
     * @param patient the patient to check
     * @param appointment the appointment to check
     * @return true if they have not been notified recently
     */
    private static boolean notNotifiedRecently(Patient patient, Appointment appointment){
        return patient.getLastTimeNotified().plus(2, ChronoUnit.DAYS).
                    compareTo(appointment.getDueDate()) < 0;

    }
}
