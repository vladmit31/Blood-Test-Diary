package seg.major.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import seg.major.model.database.AppointmentDAO;
import seg.major.model.database.PatientDAO;
import seg.major.structure.Appointment;
import seg.major.structure.Patient;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UpdatePatientModelTest {

    @Test
    public void updatePatientTest() {
        Patient p1 = new Patient("Person","One", LocalDate.parse("2000-02-13"),"4252","London","",1.0,"","","");
        PatientDAO.create(p1);
        Map<String,String> patientMap = new HashMap();
        patientMap.put("vnumber","4252");
        Patient patient = PatientDAO.get(patientMap);
        int id = patient.getID();
        Patient p2 = new Patient(id, "Perso", "Two", LocalDate.parse("2001-02-13"),"4532","A Place", "aqaa", 1.0, "A Lab", "contact1", "a number", LocalDate.parse("2019-04-15"));
        UpdatePatientModel.updatePatient(p2);
        Patient dbPatient = PatientDAO.get(id);
        Assert.assertEquals(dbPatient.getForename(), p2.getForename());
        Assert.assertEquals(dbPatient.getSurname(), p2.getSurname());
        Assert.assertEquals(dbPatient.getDob(), p2.getDob());
        Assert.assertEquals(dbPatient.getHospitalNumber(), p2.getHospitalNumber());
        Assert.assertEquals(dbPatient.getDiagnosis(), p2.getDiagnosis());
        Assert.assertEquals(dbPatient.getLabName(), p2.getLabName());
        Assert.assertEquals(dbPatient.getLocalClinic(), p2.getLocalClinic());
        Assert.assertEquals(dbPatient.getNhsNumber(), p2.getNhsNumber());
        Assert.assertEquals(dbPatient.getLabContact(), p2.getLabContact());
        Assert.assertEquals(dbPatient.getLastTimeNotified(), p2.getLastTimeNotified());
        PatientDAO.remove(dbPatient);
    }

    @Test
    public void updateAppointmentTest() {
        Patient p1 = new Patient("Person","One", LocalDate.parse("2000-02-13"),"4252","London","",1.0,"","","");
        PatientDAO.create(p1);
        Map<String,String> patient1Map = new HashMap();
        patient1Map.put("vnumber","4252");
        Patient patient1 = PatientDAO.get(patient1Map);
        int patient1ID = patient1.getID();

        Patient p2 = new Patient("Person","One", LocalDate.parse("2000-02-13"),"4253","London","",1.0,"","","");
        PatientDAO.create(p2);
        Map<String,String> patient2Map = new HashMap();
        patient2Map.put("vnumber","4253");
        Patient patient2 = PatientDAO.get(patient2Map);
        int patient2ID = patient2.getID();

        Appointment a1 = new Appointment(0,LocalDate.parse("2019-04-10"), patient1ID);
        AppointmentDAO.create(a1);
        Map<String,String> appointmentMap = new HashMap();
        appointmentMap.put("patient_id","" + patient1ID);
        Appointment dbAppointment = AppointmentDAO.get(appointmentMap);
        int appointmentID = dbAppointment.getID();
        Appointment a2 = new Appointment(appointmentID,1,LocalDate.parse("2019-05-10"),patient2ID);
        Assert.assertNotEquals(dbAppointment.getDueDate(),a2.getDueDate());
        UpdatePatientModel.updateAppointment(a2);
        dbAppointment = AppointmentDAO.get(appointmentID);
        Assert.assertEquals(dbAppointment.getDueDate(),a2.getDueDate());
        Assert.assertEquals(dbAppointment.getStatus(),a2.getStatus());
        Assert.assertEquals(dbAppointment.getPatientID(),patient2ID);

        PatientDAO.remove(patient1);
        PatientDAO.remove(patient2);
    }
}