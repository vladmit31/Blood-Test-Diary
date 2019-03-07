package seg.major.structure;

import java.time.LocalDate;

public class Appointment {

    private int appId;

    private int status;

    private LocalDate dueDate;

    private int patientId;

    public Appointment(int status, LocalDate dueDate, int patientId) {
        this.status = status;
        this.dueDate = dueDate;
        this.patientId = patientId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getAppId() {
        return appId;
    }

    public int getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getPatientId() {
        return patientId;
    }
}
