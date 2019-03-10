package seg.major.model;

import seg.major.structure.Patient;

import java.time.LocalDate;

public class UpdatePatientModel {

    private static PatientDAO patientDAO;

    public UpdatePatientModel() {
        patientDAO = new PatientDAO();
    }

    public void updatePatient(int id, String forename, String surname, LocalDate dob, String hospitalNumber,
            String localClinic, LocalDate nextAppointment, Double refreshRate) {
        patientDAO.update(
                new Patient(id, forename, surname, dob, hospitalNumber, localClinic, nextAppointment, refreshRate));
    }

    public static void updatePatient(Patient patient) {
        patientDAO.update(patient);
    }

    public static Patient getByID(int toGet) {
        return new PatientDAO().getByID(toGet);
    }
}
