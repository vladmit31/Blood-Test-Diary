package seg.major.structure;

public class Contact {
    private String forename;
    private String surname;
    private String relationship;
    private String phone;
    private String email;

    public Contact(String forename, String surname, String relationship, String phone, String email){

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
}
