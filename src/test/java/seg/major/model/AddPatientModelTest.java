package seg.major.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import seg.major.model.database.AppointmentDAO;
import seg.major.model.database.PatientDAO;
import seg.major.structure.Patient;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddPatientModelTest {

    @Test
    public void createPatientTest() {
        AddPatientModel model = new AddPatientModel();
        model.createPatient( "Person","One", LocalDate.parse("2000-02-13"),"4252","London","",1.0,LocalDate.now().plusWeeks(1),"","","");
        Map<String,String> patientMap = new HashMap();
        patientMap.put("vnumber","4252");
        Patient patient = PatientDAO.get(patientMap);
        Assert.assertEquals(patient.getDob(), LocalDate.parse("2000-02-13"));
        Map<String,String> appointmentMap = new HashMap();
        appointmentMap.put("patient_id", "" + patient.getID());
        appointmentMap.put("due_date",LocalDate.now().plusWeeks(1).toString());
        Assert.assertNotNull(AppointmentDAO.get(appointmentMap));
        PatientDAO.removeByID(patient.getID());
    }
}