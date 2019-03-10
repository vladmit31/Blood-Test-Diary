package seg.major.structure;

import java.time.LocalDate;

public class Appointment {
  private int id;
  private int status;
  private LocalDate due_date;
  private int patient_id;

  public Appointment(int id, int status, LocalDate due_date, int patient_id) {
    this.id = id;
    this.status = status;
    this.due_date = due_date;
    this.patient_id = patient_id;
  }

  public int getID() {
    return this.id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public int getStatus() {
    return this.status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public LocalDate getDueDate() {
    return this.due_date;
  }

  public void setDueDate(LocalDate due_date) {
    this.due_date = due_date;
  }

  public int getPatientID() {
    return this.patient_id;
  }

  public void setPatientID(int patient_id) {
    this.patient_id = patient_id;
  }

}
