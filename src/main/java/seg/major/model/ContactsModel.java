package seg.major.model;

import seg.major.structure.Contact;

import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

public class ContactsModel {

    private List<Contact> contactList;
    private static ContactDAO contactDAO;

    public ContactsModel() {
        contactDAO = new ContactDAO();

    }

    public void addContact(int patientID, String forename, String surname, String relationship, String phone,
            String email) {
        contactDAO.create(new Contact(patientID, forename, surname, relationship, phone, email));
        update(patientID);
    }

    public void deleteContact(Contact delete) {
        contactDAO.remove(delete);
        update(delete.getPatientID());
    }

    private void update(int toUpdate) {
        Map<String, String> params = new HashMap<>();
        params.put("patient_id", "" + toUpdate);
        contactList = Arrays.asList(contactDAO.getAll(params));

    }

    public List<Contact> getContactList(int id) {
        HashMap<String, String> param = new HashMap<>();
        param.put("patient_id", id + "");
        return Arrays.asList(contactDAO.getAll(param));
    }
}
