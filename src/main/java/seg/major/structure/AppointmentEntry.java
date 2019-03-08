package seg.major.structure;

import java.time.LocalDate;

public class AppointmentEntry {

    private final int patientId;
    private final int appointmentId;

    private String name;
    private String vnumber;
    private int complete;
    private LocalDate dueDate;

    public AppointmentEntry(int patientId, int appointmentId, String name, String vnumber, int complete, LocalDate dueDate) {
        this.name = name;
        this.vnumber = vnumber;
        this.complete = complete;
        this.dueDate = dueDate;
        this.patientId = patientId;
        this.appointmentId = appointmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVnumber(String vnumber) {
        this.vnumber = vnumber;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public String getVnumber() {
        return vnumber;
    }

    public int getComplete() {
        return complete;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String toString(){
        return this.name + " " + this.dueDate;
    }
}
