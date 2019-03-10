package seg.major.model;

import seg.major.structure.Appointment;
import seg.major.structure.AppointmentEntry;
import seg.major.structure.Patient;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SchemaModel {
    private List<Appointment> appointmentList;
    private List<Patient> patientListUnder12;
    private List<Patient> patientListOver12;
    private List<Patient> patientsList;

    private Week currentWeek;

    public SchemaModel() {

        this.currentWeek = new Week(LocalDate.now());

        updateData();

    }

    public List<AppointmentEntry> getAll() {
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();

        List<Appointment> appointments = appointmentList;
        System.out.print("Size of Appointments arraylsit is: " + appointments.size());
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

    public void updateData() {
        this.appointmentList = Arrays.asList(AppointmentDAO.getCurrentWeek());

        this.patientsList = Arrays.asList(PatientDAO.getAll());

        this.patientListUnder12 = Arrays.asList(PatientDAO.getAll()).stream()
                .filter(p -> p.getDob().plusYears(12).isAfter(LocalDate.now())
                        || p.getDob().plusYears(12).isEqual(LocalDate.now()))
                .collect(Collectors.toList());

        this.patientListOver12 = Arrays.asList(PatientDAO.getAll()).stream()
                .filter(p -> p.getDob().plusYears(12).isBefore(LocalDate.now())).collect(Collectors.toList());
    }

    private List<Appointment> getAppointmentsForDate(DayOfWeek day) {
        List<Appointment> toReturn = new ArrayList<>();

        appointmentList.stream().filter(a -> a.getDueDate().getDayOfWeek().equals(day)).forEach(a -> toReturn.add(a));

        return toReturn;
    }

    public List<AppointmentEntry> getAppointmentsAndPatientsForDayUnder12(DayOfWeek day) {
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();

        List<Appointment> appointments = getAppointmentsForDate(day);
        System.out.print("Size of Appointments arraylsit is: " + appointments.size());
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

    public List<AppointmentEntry> getAppointmentsAndPatientsForDayOver12(DayOfWeek day) {
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();

        List<Appointment> appointments = getAppointmentsForDate(day);
        System.out.print("Size of Appointments arraylsit is: " + appointments.size());

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

    public String getWeek() {
        return this.currentWeek.toString();
    }

    public void incrementWeek() {
        this.currentWeek.increment();
        updateData();
    }

    public void decrementWeek() {
        this.currentWeek.decrement();
        updateData();
    }
}
