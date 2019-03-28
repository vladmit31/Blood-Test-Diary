package seg.major.structure;

import java.time.LocalDate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * Models appointment entries for easy display
 * in the tables from the application.
 * @author Team Pacane
 * @version 1.0
 */
public class AppointmentEntry {

    private final SimpleIntegerProperty patientId;
    private final SimpleIntegerProperty appointmentId;

    private SimpleStringProperty name;
    private SimpleStringProperty vnumber;
    private SimpleStringProperty complete;
    private LocalDate dueDate;

    private SimpleStringProperty dateString;

    public AppointmentEntry(int patientId, int appointmentId, String name, String vnumber, String complete,
            LocalDate dueDate) {
        this.name = new SimpleStringProperty(name);
        this.vnumber = new SimpleStringProperty(vnumber);
        this.complete = new SimpleStringProperty(complete);
        this.dueDate = dueDate;
        this.patientId = new SimpleIntegerProperty(patientId);
        this.appointmentId = new SimpleIntegerProperty(appointmentId);
        this.dateString = new SimpleStringProperty(dueDate.toString());
    }

    public void setName(String toSet) {
        name.set(toSet);
    }

    public void setVnumber(String toSet) {
        vnumber.set(toSet);
    }

    public void setComplete(String toSet) {
        complete.set(toSet);
    }

    public void setDueDate(LocalDate toSet) {
        this.dueDate = toSet;
    }

    public String getName() {
        return name.get();
    }

    public String getVnumber() {
        return vnumber.get();
    }

    public String getComplete() {
        return complete.get();
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getPatientId() {
        return patientId.get();
    }

    public int getAppointmentId() {
        return appointmentId.get();
    }

    public String toString() {
        return this.name + " " + this.dueDate;
    }

    public String getDateString() {
        return dateString.get();
    }

    public void setDateString(String toSet) {
        dateString.set(toSet);
    }
}
