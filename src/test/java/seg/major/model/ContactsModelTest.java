package seg.major.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import seg.major.model.database.ContactDAO;
import seg.major.model.database.PatientDAO;
import seg.major.structure.Contact;
import seg.major.structure.Patient;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ContactsModelTest {

    private int patientID;

    @Before
    public void setUp() throws Exception {
        Patient p1 = new Patient("Person","One", LocalDate.parse("2000-02-13"),"4252","London","",1.0,"","","");
        PatientDAO.create(p1);
        Map<String,String> patientMap = new HashMap();
        patientMap.put("vnumber","4252");
        Patient patient = PatientDAO.get(patientMap);
        patientID = patient.getID();
    }

    @After
    public void tearDown() throws Exception {
        PatientDAO.removeByID(patientID);
    }

    @Test
    public void contactsTest() {
        ContactsModel.addContact(patientID,"contact","one","parent","11441431241", "email@email.email");
        ContactsModel.addContact(patientID,"contac","on","paren","1141431241", "mail@email.email");
        ContactsModel.addContact(patientID,"cntact","oe","parnt","1141431241", "emil@email.email");
        ContactsModel.addContact(patientID,"cnact","ne","paent","1144143141", "emal@email.email");
        ContactsModel.addContact(patientID,"ontact","e","part","114411241", "eail@email.email");

        Assert.assertEquals(ContactsModel.getContactList(patientID).size(),5);

        Assert.assertEquals(ContactsModel.getContactList(patientID).get(0).getForename(), "contact");

        Map<String,String> contactMap = new HashMap();
        contactMap.put("patient_id","" + patientID);
        List<Contact> contacts = ContactDAO.getAll(contactMap);

        for (Contact contact : contacts){
            ContactsModel.deleteContact(contact);
        }

        Assert.assertEquals(ContactsModel.getContactList(patientID).size(),0);
    }
}