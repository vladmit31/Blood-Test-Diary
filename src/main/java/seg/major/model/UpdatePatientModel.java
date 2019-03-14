package seg.major.model;

import seg.major.structure.Patient;
import seg.major.model.database.PatientDAO;
import java.time.LocalDate;

public class UpdatePatientModel {

    public UpdatePatientModel() {
    }

    public void updatePatient(int id, String forename, String surname, LocalDate dob, String hospitalNumber,
            String localClinic, LocalDate nextAppointment, Double refreshRate) {
        PatientDAO.update(
                new Patient(id, forename, surname, dob, hospitalNumber, localClinic, nextAppointment, refreshRate));
    }

    public static void updatePatient(Patient patient) {
        PatientDAO.update(patient);
    }

    public static Patient getByID(int toGet) {
        return PatientDAO.getByID(toGet);
    }
}
