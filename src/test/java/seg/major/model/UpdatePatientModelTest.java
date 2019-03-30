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

    private int patient1ID;
    private int patient2ID;

    @Before
    public void setUp() throws Exception {
        Patient p1 = new Patient("Person","One", LocalDate.parse("2000-02-13"),"4252","London","",1.0,"","","");
        PatientDAO.create(p1);
        Map<String,String> patientMap = new HashMap<>();
        patientMap.put("vnumber","4252");
        Patient patient = PatientDAO.get(patientMap);
        patient1ID = patient.getID();

        Patient p2 = new Patient("Person","One", LocalDate.parse("2000-02-13"),"4253","London","",1.0,"","","");
        PatientDAO.create(p2);
        Map<String,String> patient2Map = new HashMap<>();
        patient2Map.put("vnumber","4253");
        Patient patient2 = PatientDAO.get(patient2Map);
        patient2ID = patient2.getID();
    }

    @After
    public void tearDown() throws Exception {
        PatientDAO.removeByID(patient1ID);
        PatientDAO.removeByID(patient2ID);
    }

    @Test
    public void updatePatientTest() {
        Patient p3 = new Patient(patient1ID, "Perso", "Three", LocalDate.parse("2001-02-13"),"4532","A Place", "aqaa", 1.0, "A Lab", "contact1", "a number", LocalDate.parse("2019-04-15"));
        UpdatePatientModel.updatePatient(p3);
        Patient dbPatient = PatientDAO.get(patient1ID);
        Assert.assertEquals(dbPatient.getForename(), p3.getForename());
        Assert.assertEquals(dbPatient.getSurname(), p3.getSurname());
        Assert.assertEquals(dbPatient.getDob(), p3.getDob());
        Assert.assertEquals(dbPatient.getHospitalNumber(), p3.getHospitalNumber());
        Assert.assertEquals(dbPatient.getDiagnosis(), p3.getDiagnosis());
        Assert.assertEquals(dbPatient.getLabName(), p3.getLabName());
        Assert.assertEquals(dbPatient.getLocalClinic(), p3.getLocalClinic());
        Assert.assertEquals(dbPatient.getNhsNumber(), p3.getNhsNumber());
        Assert.assertEquals(dbPatient.getLabContact(), p3.getLabContact());
        Assert.assertEquals(dbPatient.getLastTimeNotified(), p3.getLastTimeNotified());
    }

    @Test
    public void updateAppointmentTest() {

        Appointment a1 = new Appointment(0,LocalDate.parse("2019-04-10"), patient1ID);
        AppointmentDAO.create(a1);
        Map<String,String> appointmentMap = new HashMap<>();
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

    }
}