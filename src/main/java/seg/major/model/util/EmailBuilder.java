package seg.major.model.util;

import seg.major.model.EditNotificationEmailModel;
import seg.major.structure.Appointment;
import seg.major.structure.Contact;
import seg.major.structure.Patient;

/**
 * Creates e-mails from templates provided by
 * our client.
 * @author Team Pacane
 * @version 1.0
 */
public class EmailBuilder {
    public static String generate(Contact contact, Patient patient, Appointment appointment, EditNotificationEmailModel.EmailType type){
        return buildEmailFromFile(contact, patient, appointment, type);
    }

    private static String buildEmailFromFile(Contact contact, Patient patient, Appointment appointment, EditNotificationEmailModel.EmailType type){

        String body = "Body not found";

        if(type == EditNotificationEmailModel.EmailType.REMINDER){
            EditNotificationEmailModel.getFileContents(EditNotificationEmailModel.EmailType.REMINDER);


            body = EditNotificationEmailModel.getBodyAsString();
        }else if(type == EditNotificationEmailModel.EmailType.MISSED){
            EditNotificationEmailModel.getFileContents(EditNotificationEmailModel.EmailType.MISSED);


            body = EditNotificationEmailModel.getBodyAsString();
        }



        //System.out.println("!!!!!!!" + body + "???????");

        body = body.replaceAll("CONTACT_NAME", contact.getForename() + " " + contact.getSurname());
        body = body.replaceAll("PATIENT_NAME", patient.getForename() + " " + patient.getSurname());
        body = body.replaceAll("APPOINTMENT_DATE", appointment.getDueDate().toString());


        return body;
    }
}
