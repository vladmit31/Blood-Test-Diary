package seg.major.model;
import seg.major.structure.Patient;

import java.time.LocalDate;


public class UpdatePatientModel {

    public UpdatePatientModel(){}

    public Patient updatePatient(String forename, String surname, LocalDate dob, String hospitalNumber, String localClinic)
    {
        return new Patient(forename, surname, dob, hospitalNumber, localClinic);
    }

}
