package seg.major.model;

import seg.major.database.DatabaseConnection;
import seg.major.structure.Contact;
import seg.major.structure.Patient;

import java.util.List;

public class ContactsModel {

    private final Patient patient;
    private List<Contact> contactList;

    public ContactsModel(Patient patient){
        this.patient = patient;
        contactList = DatabaseConnection.getContactsByPatientId(patient.getId());
    }

    public void addContact(Contact newContact){
        DatabaseConnection.addContactToPatient(patient.getId(), newContact);
        update();
    }

    public void deleteContact(Contact delete){
        DatabaseConnection.deleteContact(patient.getId(), delete);
        update();
    }

    private void update(){
        contactList = DatabaseConnection.getContactsByPatientId(patient.getId());
    }

    public List<Contact> getContactList() {
        return this.contactList;
    }
}
