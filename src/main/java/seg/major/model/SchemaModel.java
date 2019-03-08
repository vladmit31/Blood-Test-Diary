package seg.major.model;

import seg.major.database.DatabaseConnection;
import seg.major.structure.Appointment;
import seg.major.structure.AppointmentEntry;
import seg.major.structure.Patient;

import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SchemaModel {
    private List<Appointment> appointmentList;
    private List<Patient> patientListUnder12;
    private List<Patient> patientListOver12;

    private Week currentWeek;

    public SchemaModel() {

        this.currentWeek = new Week(LocalDate.now());

        updateData();

    }

    public void updateData() {
        this.appointmentList = DatabaseConnection.getAppointmentsForTheWeek(currentWeek.getMondayDate(),
                currentWeek.getFridayDate());

        this.patientListUnder12 = DatabaseConnection.getPatients().stream()
                .filter(p -> p.getDob().plusYears(12).isAfter(LocalDate.now()) ||  p.getDob().plusYears(12).isEqual(LocalDate.now())).collect(Collectors.toList());


        this.patientListOver12 = DatabaseConnection.getPatients().stream()
                .filter(p -> p.getDob().plusYears(12).isBefore(LocalDate.now())).collect(Collectors.toList());
    }

    private List<Appointment> getAppointmentsForDate(DayOfWeek day){
        List<Appointment> toReturn = new ArrayList<>();

        appointmentList.stream()
                .filter(a -> a.getDueDate().getDayOfWeek().equals(day))
                .forEach(a -> toReturn.add(a));

        return toReturn;
    }

    public List<AppointmentEntry> getAppointmentsAndPatientsForDayUnder12(DayOfWeek day){
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();

        List<Appointment> appointments = getAppointmentsForDate(day);

        for(var appointment : appointments){
            for(var patient : patientListUnder12){
                if(appointment.getPatientId() == patient.getId()){
                    String name = patient.getForename() + " " + patient.getSurname();
                    AppointmentEntry entry = new AppointmentEntry(patient.getId(), appointment.getAppId(),
                                                                    name, patient.getHospitalNumber(), appointment.getStatus(), appointment.getDueDate());
                    toReturn.add(entry);
                    System.out.println(entry);
                    //A patient can have only one appointment per day, so it's not necessary to search the whole list
                    break;
                }
            }
        }

        return toReturn;
    }


    public List<AppointmentEntry> getAppointmentsAndPatientsForDayOver12(DayOfWeek day){
        List<AppointmentEntry> toReturn = new ArrayList<AppointmentEntry>();

        List<Appointment> appointments = getAppointmentsForDate(day);

        for(var appointment : appointments){
            for(var patient : patientListOver12){
                if(appointment.getPatientId() == patient.getId()){
                    String name = patient.getForename() + " " + patient.getSurname();
                    AppointmentEntry entry = new AppointmentEntry(patient.getId(), appointment.getAppId(),
                            name, patient.getHospitalNumber(), appointment.getStatus(), appointment.getDueDate());
                    toReturn.add(entry);
                    System.out.println(entry);
                    //A patient can have only one appointment per day, so it's not necessary to search the whole list
                    break;
                }
            }
        }

        return toReturn;
    }

    public String getWeek(){
        return this.currentWeek.toString();
    }

    public void incrementWeek(){
        this.currentWeek.increment();
        updateData();
    }

    public void decrementWeek(){
        this.currentWeek.decrement();
        updateData();
    }
}
