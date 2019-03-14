package seg.major.model;

import seg.major.structure.Contact;
import seg.major.model.database.ContactDAO;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ContactsModel {

    private static List<Contact> contactList;

    public ContactsModel() {

    }

    public static void addContact(int patientID, String forename, String surname, String relationship, String phone,
            String email) {
        ContactDAO.create(new Contact(patientID, forename, surname, relationship, phone, email));
        update(patientID);
    }

    public static void deleteContact(Contact delete) {
        ContactDAO.remove(delete);
        update(delete.getPatientID());
    }

    private static void update(int toUpdate) {
        Map<String, String> params = new HashMap<>();
        params.put("patient_id", "" + toUpdate);
        contactList = ContactDAO.getAll(params);

    }

    public static List<Contact> getContactList(int id) {
        HashMap<String, String> param = new HashMap<>();
        param.put("patient_id", id + "");
        List<Contact> toReturn = ContactDAO.getAll(param);
        System.out.println("Size of contact list in ContactsModel.java:" + toReturn.size());
        return toReturn;
    }
}
