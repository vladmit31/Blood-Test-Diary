package seg.major.model;

import seg.major.structure.Appointment;
import seg.major.structure.AppointmentEntry;
import seg.major.structure.Patient;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SchemaModel {

    private static Week currentWeek = new Week(LocalDate.now());

    public SchemaModel() {

    }

    public static List<AppointmentEntry> getAll() {
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();
        List<Appointment> appointments = AppointmentDAO.getCurrentWeek(currentWeek);
        List<Patient> patientsList = PatientDAO.getAll();
        System.out.println("Size of schema:" + appointments.size());
        for (var appointment : appointments) {
            for (var patient : patientsList) {
                if (appointment.getPatientID() == patient.getID()) {
                    String name = patient.getForename() + " " + patient.getSurname();
                    AppointmentEntry entry = new AppointmentEntry(patient.getID(), appointment.getID(), name,
                            patient.getHospitalNumber(), appointment.getStatus(), appointment.getDueDate());
                    toReturn.add(entry);
                    // A patient can have only one appointment per day, so it's not necessary to
                    // search the whole list
                    break;
                }
            }
        }

        return toReturn;
    }

    public static void updateData() {
        // appointmentList = );

        // patintsList = ;

        // patientListUnder12 =

        // patientListOver12 =
    }

    private static List<Appointment> getAppointmentsForDate(DayOfWeek day) {
        List<Appointment> toReturn = new ArrayList<>();

        AppointmentDAO.getAll().stream().filter(a -> a.getDueDate().getDayOfWeek().equals(day))
                .forEach(a -> toReturn.add(a));

        return toReturn;
    }

    public static List<AppointmentEntry> getAppointmentsAndPatientsForDayUnder12(DayOfWeek day) {
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();

        List<Appointment> appointments = getAppointmentsForDate(day);
        List<Patient> patientListUnder12 = PatientDAO.getAll().stream()
                .filter(p -> p.getDob().plusYears(12).isAfter(LocalDate.now())
                        || p.getDob().plusYears(12).isEqual(LocalDate.now()))
                .collect(Collectors.toList());
        for (var appointment : appointments) {
            for (var patient : patientListUnder12) {
                if (appointment.getPatientID() == patient.getID()) {
                    String name = patient.getForename() + " " + patient.getSurname();
                    AppointmentEntry entry = new AppointmentEntry(patient.getID(), appointment.getID(), name,
                            patient.getHospitalNumber(), appointment.getStatus(), appointment.getDueDate());
                    toReturn.add(entry);
                    // A patient can have only one appointment per day, so it's not necessary to
                    // search the whole list
                    break;
                }
            }
        }

        return toReturn;
    }

    public static List<AppointmentEntry> getAppointmentsAndPatientsForDayOver12(DayOfWeek day) {
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();

        List<Appointment> appointments = getAppointmentsForDate(day);
        List<Patient> patientListOver12 = PatientDAO.getAll().stream()
                .filter(p -> p.getDob().plusYears(12).isBefore(LocalDate.now())).collect(Collectors.toList());

        for (var appointment : appointments) {
            for (var patient : patientListOver12) {
                if (appointment.getPatientID() == patient.getID()) {
                    String name = patient.getForename() + " " + patient.getSurname();
                    AppointmentEntry entry = new AppointmentEntry(patient.getID(), appointment.getID(), name,
                            patient.getHospitalNumber(), appointment.getStatus(), appointment.getDueDate());
                    toReturn.add(entry);
                    // A patient can have only one appointment per day, so it's not necessary to
                    // search the whole list
                    // break;
                }
            }
        }

        return toReturn;
    }

    public static String getWeek() {
        return currentWeek.toString();
    }

    public static void incrementWeek() {
        currentWeek.increment();
        updateData();
    }

    public static void decrementWeek() {
        currentWeek.decrement();
        updateData();
    }
}
