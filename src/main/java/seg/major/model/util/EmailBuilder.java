package seg.major.model.util;

import seg.major.model.EditNotificationEmailModel;
import seg.major.structure.Appointment;
import seg.major.structure.Contact;
import seg.major.structure.Patient;

public class EmailBuilder {
    public static String generate(Contact contact, Patient patient, Appointment appointment){
        return buildEmailFromFile(contact, patient, appointment);
    }

    private static String buildEmail(Contact contact, Patient patient, Appointment appointment){
        StringBuilder builder = new StringBuilder();

        builder.append("Dear ");
        builder.append(contact.getForename() + " " + contact.getSurname() + ", \n");
        builder.append("This is a reminder email for " +
                patient.getForename() + " " + patient.getSurname());
        builder.append("'s appointment on " + appointment.getDueDate() + " at " + patient.getLabName() + "\n");
        builder.append("Please write us an email confirming the patient's attendence \n");
        builder.append("Kind regards, \n");

        return builder.toString();
    }

    private static String buildEmailFromFile(Contact contact, Patient patient, Appointment appointment){
        EditNotificationEmailModel.clearContents();

        EditNotificationEmailModel.getFileContents();


        String body = EditNotificationEmailModel.getBodyAsString();

        //System.out.println("!!!!!!!" + body + "???????");

        body = body.replaceAll("CONTACT_NAME", contact.getForename() + " " + contact.getSurname());
        body = body.replaceAll("PATIENT_NAME", patient.getForename() + " " + patient.getSurname());
        body = body.replaceAll("APPOINTMENT_DATE", appointment.getDueDate().toString());


        return body;
    }
}
