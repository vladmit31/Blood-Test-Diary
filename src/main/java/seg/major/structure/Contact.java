package seg.major.structure;
/**
 * Models contact objects in order to keep them in
 * memory in a managed way.
 * @author Team Pacane
 * @version 1.0
 */
public class Contact {
    private int id;
    private final int patientID;
    private String forename;
    private String surname;
    private String relationship;
    private String phone;
    private String email;

    public Contact(int patientID, String forename, String surname, String relationship, String phone, String email) {
        this.patientID = patientID;
        this.forename = forename;
        this.surname = surname;
        this.relationship = relationship;
        this.phone = phone;
        this.email = email;
    }

    public Contact(int patientID, int id, String forename, String surname, String relationship, String phone,
            String email) {
        this.patientID = patientID;
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.relationship = relationship;
        this.phone = phone;
        this.email = email;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public int getID() {
        return id;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return id + " " + forename + " " + surname + " " + relationship + " " + phone + " " + " " + email;
    }
}
