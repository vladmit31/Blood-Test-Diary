package seg.major.model.util;

import seg.major.structure.Appointment;
import seg.major.structure.Contact;
import seg.major.structure.Patient;

public class EmailBuilder {
    public static String generate(Contact contact, Patient patient, Appointment appointment){
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
}
