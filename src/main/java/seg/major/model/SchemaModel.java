package seg.major.model;

import seg.major.database.DatabaseConnection;
import seg.major.structure.Appointment;
import seg.major.structure.Patient;

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

    boolean under12;

    public SchemaModel() {
        this.appointmentList = DatabaseConnection.getAppointments();


        this.patientListOver12 = DatabaseConnection.getPatients().stream()
                .filter(p -> p.getDob().plusYears(12).isAfter(LocalDate.now()) ||  p.getDob().plusYears(12).isEqual(LocalDate.now())).collect(Collectors.toList());


        this.patientListUnder12 = DatabaseConnection.getPatients().stream()
                .filter(p -> p.getDob().plusYears(12).isBefore(LocalDate.now())).collect(Collectors.toList());


        under12 = true;
    }

    private List<Appointment> getAppointmentsForDate(DayOfWeek day){
        List<Appointment> toReturn = new ArrayList<>();

        appointmentList.stream()
                .filter(a -> a.getDueDate().getDayOfWeek().equals(day))
                .forEach(a -> toReturn.add(a));

        return toReturn;
    }

    public Map<Appointment, Patient> getAppointmentsAndPatientsForDayUnder12(DayOfWeek day){
        Map<Appointment, Patient> toReturn = new HashMap<>();

        List<Appointment> appointments = getAppointmentsForDate(day);

        for(var appointment : appointments){
            for(var patient : patientListUnder12){
                if(appointment.getPatientId() == patient.getId()){
                    toReturn.put(appointment, patient);
                    //A patient can have only one appointment per day, so it's not necessary to search the whole list
                    break;
                }
            }
        }

        return toReturn;
    }


    public Map<Appointment, Patient> getAppointmentsAndPatientsForDayOver12(DayOfWeek day){
        Map<Appointment, Patient> toReturn = new HashMap<>();

        List<Appointment> appointments = getAppointmentsForDate(day);

        for(var appointment : appointments){
            for(var patient : patientListOver12){
                if(appointment.getPatientId() == patient.getId()){
                    toReturn.put(appointment, patient);
                    //A patient can have only one appointment per day, so it's not necessary to search the whole list
                    break;
                }
            }
        }

        return toReturn;
    }



}
