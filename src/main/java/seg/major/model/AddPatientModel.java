package seg.major.model;
import seg.major.structure.Patient;

import java.time.LocalDate;


public class AddPatientModel {

    public AddPatientModel(){}

    public Patient createPatient(String forename, String surname, LocalDate dob, String hospitalNumber, String localClinic, LocalDate nextAppointment)
    {
        return new Patient(forename, surname, dob, hospitalNumber, localClinic, nextAppointment);
    }

}
