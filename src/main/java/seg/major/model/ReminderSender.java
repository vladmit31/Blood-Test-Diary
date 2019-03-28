package seg.major.model;

import seg.major.App;
import seg.major.controller.util.EmailSender;
import seg.major.model.database.AppointmentDAO;
import seg.major.model.database.ContactDAO;
import seg.major.model.database.PatientDAO;
import seg.major.model.util.EmailBuilder;
import seg.major.structure.Appointment;
import seg.major.structure.Contact;
import seg.major.structure.Patient;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Sends reminders for people that have appointments
 * in the next 2 days.
 * @author Team Pacane
 * @version 1.0
 */
public class ReminderSender {

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

                //it.remove(); // avoids a ConcurrentModificationException
            }

            StringBuilder sb = new StringBuilder();

            sb.append("Your email has been sent to: \n");

            Iterator it1 = toNotifiy.entrySet().iterator();
            while (it1.hasNext()) {
                Map.Entry pair = (Map.Entry)it1.next();
                Contact contact = (Contact)pair.getKey();
                String generatedContent = (String)pair.getValue();
                sb.append(contact.getForename() + " " + contact.getSurname() + "\n");
                //it1.remove(); // avoids a ConcurrentModificationException
            }

            System.out.println(sb.toString());
            EmailSender sender = new EmailSender(toNotifiy, "Reminder");
            sender.start();
        }
    }

    private static boolean soonEnough(Appointment appointment){
        LocalDate today = LocalDate.now();

        LocalDate appointmentDay = appointment.getDueDate();

        return today.plus(1, ChronoUnit.DAYS).compareTo(appointmentDay) == 0 ||
                today.plus(2, ChronoUnit.DAYS).compareTo(appointmentDay) == 0;

    }

    private static boolean notNotifiedRecently(Patient patient, Appointment appointment){
        return patient.getLastTimeNotified().plus(2, ChronoUnit.DAYS).
                    compareTo(appointment.getDueDate()) < 0;

    }
}
