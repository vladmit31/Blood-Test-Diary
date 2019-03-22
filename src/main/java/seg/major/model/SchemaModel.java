package seg.major.model;

import seg.major.structure.Appointment;
import seg.major.structure.AppointmentEntry;
import seg.major.structure.Patient;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import seg.major.model.database.PatientDAO;
import seg.major.model.database.AppointmentDAO;

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
                .filter(p -> p.getDob().plusYears(12).isAfter(LocalDate.now()) ||
                        p.getDob().plusYears(12).isEqual(LocalDate.now())).collect(Collectors.toList());


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
                    if(appointment.getStatus() == 1) {
                        status = "Complete";
                    }
                    AppointmentEntry entry = new AppointmentEntry(patient.getID(), appointment.getID(), name,
                            patient.getHospitalNumber(), status, appointment.getDueDate());
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

        appointmentList.stream().filter(a -> a.getDueDate().getDayOfWeek().equals(day))
                .forEach(a -> toReturn.add(a));

        return toReturn;
    }

    public List<AppointmentEntry> getCarriedOverAppointments() {
        List<AppointmentEntry> allAppointments = getAll();

        List<AppointmentEntry> toReturn = new ArrayList<>();
        for(AppointmentEntry appointmentEntry : allAppointments) {
            if(appointmentEntry.getComplete().equals("Incomplete")
                    && appointmentEntry.getDueDate().getDayOfWeek().compareTo(LocalDate.now().getDayOfWeek()) < 0) {
                toReturn.add(appointmentEntry);
            }
        }

        return toReturn;
    }


    public List<AppointmentEntry> getAppointmentsAndPatientsForDayUnder12(DayOfWeek day) {
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();

        List<Appointment> appointments = getAppointmentsForDate(day);

/*        List<Patient> patientListUnder12 = PatientDAO.getAll().stream()
                .filter(p -> p.getDob().plusYears(12).isAfter(LocalDate.now()) ||
                        p.getDob().plusYears(12).isEqual(LocalDate.now())).collect(Collectors.toList());*/

        for(var appointment : appointments){
            for(var patient : patientListUnder12){
                if(appointment.getPatientID() == patient.getID()){
                    String name = patient.getForename() + " " + patient.getSurname();
                    String status = "Incomplete";
                    if(appointment.getStatus() == 1) {
                        status = "Complete";
                    }
                    AppointmentEntry entry = new AppointmentEntry(patient.getID(), appointment.getID(),
                            name, patient.getHospitalNumber(), status, appointment.getDueDate());
                    toReturn.add(entry);
                    //System.out.println(entry);
                    //A patient can have only one appointment per day, so it's not necessary to search the whole list
                    break;
                }
            }
        }
        return toReturn;
    }

    public List<AppointmentEntry> getAppointmentsAndPatientsForDayOver12(DayOfWeek day) {
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();

        List<Appointment> appointments = getAppointmentsForDate(day);

        /*List<Patient> patientListOver12 = PatientDAO.getAll().stream()
                .filter(p -> p.getDob().plusYears(12).isBefore(LocalDate.now())).collect(Collectors.toList());*/

        for(var appointment : appointments){
            for(var patient : patientListOver12){
                if(appointment.getPatientID() == patient.getID()){
                    String name = patient.getForename() + " " + patient.getSurname();String status = "Incomplete";
                    if(appointment.getStatus() == 1) {
                        status = "Complete";
                    }
                    AppointmentEntry entry = new AppointmentEntry(patient.getID(), appointment.getID(),
                            name, patient.getHospitalNumber(), status, appointment.getDueDate());
                    toReturn.add(entry);
                    //System.out.println(entry);
                    //A patient can have only one appointment per day, so it's not necessary to search the whole list
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
