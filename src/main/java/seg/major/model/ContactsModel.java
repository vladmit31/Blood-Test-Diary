package seg.major.model;

import seg.major.model.database.ContactDAO;
import seg.major.structure.Contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Model class for ContactsController class.
 * Provides communication between controller and DAOs if needed.
 * @author Team Pacane
 * @version 1.0
 */
public class ContactsModel {

    private static List<Contact> contactList;

    /**
     * Createa a new contact in the database
     *
     * @param patientID
     * @param forename
     * @param surname
     * @param relationship
     * @param phone
     * @param email
     */
    public static void addContact(int patientID, String forename, String surname, String relationship, String phone,
            String email) {
        ContactDAO.create(new Contact(patientID, forename, surname, relationship, phone, email));
        update(patientID);
    }

    /**
     * Delete a contact from the database
     * @param toDelete the contact to be deleted
     */
    public static void deleteContact(Contact toDelete) {
        ContactDAO.remove(toDelete);
        update(toDelete.getPatientID());
    }

    /**
     * Update a contact in the database
     *
     * @param toUpdate
     */
    private static void update(int toUpdate) {
        Map<String, String> params = new HashMap<>();
        params.put("patient_id", "" + toUpdate);
        contactList = ContactDAO.getAll(params);

    }

    /**
     * Get the contacts for a patient
     *
     * @param toGet the patient ID to get contacts for
     * @return the list of contacts
     */
    public static List<Contact> getContactList(int toGet) {
        HashMap<String, String> param = new HashMap<>();
        param.put("patient_id", toGet + "");
        List<Contact> toReturn = ContactDAO.getAll(param);

        return toReturn;
    }
}
