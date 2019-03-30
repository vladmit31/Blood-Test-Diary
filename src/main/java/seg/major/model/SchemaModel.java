package seg.major.model;

import seg.major.model.util.DateReverser;
import seg.major.structure.Appointment;
import seg.major.structure.AppointmentEntry;
import seg.major.structure.Patient;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import seg.major.model.database.PatientDAO;
import seg.major.model.database.AppointmentDAO;

/**
 * Model class for SchemaController class. Provides communication between
 * controller and DAOs if needed.
 * 
 * @author Team Pacane
 * @version 1.0
 */
public class SchemaModel {

    private List<Appointment> appointmentList;
    private List<Patient> patientListUnder12;
    private List<Patient> patientListOver12;

    private Week currentWeek;

    public SchemaModel() {
        currentWeek = new Week(LocalDate.now());
        updateData();
    }

    public void updateData() {
        this.appointmentList = AppointmentDAO.getCurrentWeek(currentWeek);

        this.patientListUnder12 = PatientDAO.getAll().stream()
                .filter(p -> p.getDob().plusYears(12).isAfter(LocalDate.now())
                        || p.getDob().plusYears(12).isEqual(LocalDate.now()))
                .collect(Collectors.toList());

        this.patientListOver12 = PatientDAO.getAll().stream()
                .filter(p -> p.getDob().plusYears(12).isBefore(LocalDate.now())).collect(Collectors.toList());
    }

    public List<AppointmentEntry> getAll() {
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();
        List<Appointment> appointments = AppointmentDAO.getCurrentWeek(currentWeek);
        List<Patient> patientsList = PatientDAO.getAll();
        for (var appointment : appointments) {
            for (var patient : patientsList) {
                if (appointment.getPatientID() == patient.getID()) {
                    String name = patient.getForename() + " " + patient.getSurname();
                    String status = "Incomplete";
                    if (appointment.getStatus() == 1) {
                        status = "Complete";
                    }
                    if (appointment.getStatus() == 2) {
                        status = "Under review";
                    }
                    if (appointment.getStatus() == 3) {
                        status = "Not received yet";
                    }
                    AppointmentEntry entry = new AppointmentEntry(patient.getID(), appointment.getID(), name,
                            patient.getHospitalNumber(), status, appointment.getDueDate());

                    String newDate = DateReverser.reverseDateFormat(entry.getDueDate());

                    entry.setDateString(newDate);

                    toReturn.add(entry);
                    // A patient can have only one appointment per day, so it's not necessary to
                    // search the whole list
                    break;
                }
            }
        }

        return toReturn;
    }

    public List<AppointmentEntry> getPatientsOver12() {
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();

        List<Appointment> appointments = appointmentList;

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (var appointment : appointments) {
            for (var patient : patientListOver12) {
                if (appointment.getPatientID() == patient.getID()) {
                    String name = patient.getForename() + " " + patient.getSurname();
                    String status = "Incomplete";
                    if (appointment.getStatus() == 1) {
                        status = "Complete";
                    }
                    if (appointment.getStatus() == 2) {
                        status = "Under review";
                    }
                    if (appointment.getStatus() == 3) {
                        status = "Not received yet";
                    }
                    AppointmentEntry entry = new AppointmentEntry(patient.getID(), appointment.getID(), name,
                            patient.getHospitalNumber(), status, appointment.getDueDate());

                    String newDate = DateReverser.reverseDateFormat(entry.getDueDate());

                    entry.setDateString(newDate);

                    toReturn.add(entry);
                    // A patient can have only one appointment per day, so it's not necessary to
                    // search the whole list
                    break;
                }
            }
        }

        return toReturn;
    }

    public List<AppointmentEntry> getPatientsUnder12() {
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();

        List<Appointment> appointments = appointmentList;

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (var appointment : appointments) {
            for (var patient : patientListUnder12) {
                if (appointment.getPatientID() == patient.getID()) {
                    String name = patient.getForename() + " " + patient.getSurname();
                    String status = "Incomplete";
                    if (appointment.getStatus() == 1) {
                        status = "Complete";
                    }
                    if (appointment.getStatus() == 2) {
                        status = "Under review";
                    }
                    if (appointment.getStatus() == 3) {
                        status = "Not received yet";
                    }
                    AppointmentEntry entry = new AppointmentEntry(patient.getID(), appointment.getID(), name,
                            patient.getHospitalNumber(), status, appointment.getDueDate());

                    String newDate = DateReverser.reverseDateFormat(entry.getDueDate());

                    entry.setDateString(newDate);

                    toReturn.add(entry);
                    // A patient can have only one appointment per day, so it's not necessary to
                    // search the whole list
                    break;
                }
            }
        }

        return toReturn;
    }

    private List<Appointment> getAppointmentsForDate(DayOfWeek day) {
        List<Appointment> toReturn = new ArrayList<>();

        appointmentList.stream().filter(a -> a.getDueDate().getDayOfWeek().equals(day)).forEach(a -> toReturn.add(a));

        return toReturn;
    }

    public List<AppointmentEntry> getAllAppointmentsEntry() {
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();
        List<Appointment> appointments = AppointmentDAO.getAll();
        List<Patient> patientsList = PatientDAO.getAll();
        for (var appointment : appointments) {
            for (var patient : patientsList) {
                if (appointment.getPatientID() == patient.getID()) {
                    String name = patient.getForename() + " " + patient.getSurname();
                    String status = "Incomplete";
                    if (appointment.getStatus() == 1) {
                        status = "Complete";
                    }
                    if (appointment.getStatus() == 2) {
                        status = "Under review";
                    }
                    if (appointment.getStatus() == 3) {
                        status = "Not received yet";
                    }
                    AppointmentEntry entry = new AppointmentEntry(patient.getID(), appointment.getID(), name,
                            patient.getHospitalNumber(), status, appointment.getDueDate());

                    String newDate = DateReverser.reverseDateFormat(entry.getDueDate());

                    entry.setDateString(newDate);

                    toReturn.add(entry);
                    // A patient can have only one appointment per day, so it's not necessary to
                    // search the whole list
                    break;
                }
            }
        }

        return toReturn;
    }

    public List<AppointmentEntry> getCarriedOverAppointments() {
        List<AppointmentEntry> allAppointments = getAllAppointmentsEntry();

        List<AppointmentEntry> toReturn = new ArrayList<>();
        for (AppointmentEntry appointmentEntry : allAppointments) {
            System.out.println(appointmentEntry.getName() + "------------ */");
            System.out.println(appointmentEntry.getDueDate().compareTo(LocalDate.now()));
            if (appointmentEntry.getComplete().equals("Incomplete")
                    && appointmentEntry.getDueDate().compareTo(LocalDate.now()) < 0) {
                toReturn.add(appointmentEntry);

            }
        }

        return toReturn;
    }

    public List<AppointmentEntry> getAppointmentsAndPatientsForDayUnder12(DayOfWeek day) {
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();

        List<Appointment> appointments = getAppointmentsForDate(day);

        for (var appointment : appointments) {
            for (var patient : patientListUnder12) {
                if (appointment.getPatientID() == patient.getID()) {
                    String name = patient.getForename() + " " + patient.getSurname();
                    String status = "Incomplete";
                    if (appointment.getStatus() == 1) {
                        status = "Complete";
                    }
                    if (appointment.getStatus() == 2) {
                        status = "Under review";
                    }
                    if (appointment.getStatus() == 3) {
                        status = "Not received yet";
                    }
                    AppointmentEntry entry = new AppointmentEntry(patient.getID(), appointment.getID(), name,
                            patient.getHospitalNumber(), status, appointment.getDueDate());

                    String newDate = DateReverser.reverseDateFormat(entry.getDueDate());

                    entry.setDateString(newDate);

                    toReturn.add(entry);
                    // A patient can have only one appointment per day, so it's not necessary to
                    // search the whole list
                    break;
                }
            }
        }
        return toReturn;
    }

    public List<AppointmentEntry> getAppointmentsAndPatientsForDayOver12(DayOfWeek day) {
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();

        List<Appointment> appointments = getAppointmentsForDate(day);

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (var appointment : appointments) {
            for (var patient : patientListOver12) {
                if (appointment.getPatientID() == patient.getID()) {
                    String name = patient.getForename() + " " + patient.getSurname();
                    String status = "Incomplete";
                    if (appointment.getStatus() == 1) {
                        status = "Complete";
                    }
                    if (appointment.getStatus() == 2) {
                        status = "Under review";
                    }
                    if (appointment.getStatus() == 3) {
                        status = "Not received yet";
                    }
                    AppointmentEntry entry = new AppointmentEntry(patient.getID(), appointment.getID(), name,
                            patient.getHospitalNumber(), status, appointment.getDueDate());

                    String newDate = DateReverser.reverseDateFormat(entry.getDueDate());

                    entry.setDateString(newDate);

                    toReturn.add(entry);
                    // A patient can have only one appointment per day, so it's not necessary to
                    // search the whole list
                    break;
                }
            }
        }

        return toReturn;
    }

    public String getWeek() {
        return currentWeek.toString();
    }

    public void incrementWeek() {
        currentWeek.increment();
        updateData();

    }

    public void decrementWeek() {
        currentWeek.decrement();
        updateData();

    }
}
