package seg.major.model;

import org.junit.Test;
import seg.major.App;
import seg.major.model.database.PatientDAO;
import seg.major.structure.Patient;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PatientModelTest {
    @Test
    public void testSearch() {
        List <Patient> patientList = new ArrayList<>();
        Patient p1 = new Patient("Person","One", LocalDate.parse("2000-02-13"),"4252","London","",1.0,"","","");
        Patient p2 = new Patient("Perso","None", LocalDate.parse("2000-02-13"),"4253","London","",1.0,"","","");
        Patient p3 = new Patient("Person","Three", LocalDate.parse("2000-02-13"),"4254","London","",1.0,"","","");
        Patient p4 = new Patient("London","Resident", LocalDate.parse("2000-02-13"),"a4255","London","",1.0,"","","");
        Patient p5 = new Patient("Water","Bottle", LocalDate.parse("2000-02-13"),"4256","London","",1.0,"","","");

        patientList.add(p1);
        patientList.add(p2);
        patientList.add(p3);
        patientList.add(p4);
        patientList.add(p5);

        PatientModel model = new PatientModel(patientList);

        Assert.assertEquals(model.search(""), Arrays.asList(p1,p2,p3,p4,p5));
        Assert.assertEquals(model.search("Person"), Arrays.asList(p1,p3));
        Assert.assertEquals(model.search("PERSON"), Arrays.asList(p1,p3));
        Assert.assertEquals(model.search("o"), Arrays.asList(p1,p2,p3,p4,p5));
        Assert.assertEquals(model.search("a"), Collections.singletonList(p5));
        Assert.assertEquals(model.search("Person One"), Collections.singletonList(p1));
        Assert.assertEquals(model.search("Perso None"), Collections.singletonList(p2));
        Assert.assertEquals(model.search("Z"), Collections.emptyList());
        Assert.assertEquals(model.search("Three"), Collections.singletonList(p3));

        Assert.assertEquals(model.search(""), Arrays.asList(p1,p2,p3,p4,p5));
        Assert.assertEquals(model.search("425"), Arrays.asList(p1,p2,p3,p4,p5));
        Assert.assertEquals(model.search("4253"), Collections.singletonList(p2));
        Assert.assertEquals(model.search("a4"), Collections.singletonList(p4));
    }
    @Test
    public void testOver12() {
        PatientModel model = new PatientModel();
        List <Patient> over12 = model.over12();
        for (Patient patient : over12){
            System.out.println(patient.getDob());
            Assert.assertTrue(patient.getDob().isBefore(LocalDate.now().minusYears(12)) || patient.getDob().equals(LocalDate.now().minusYears(12)));
        }
        for (Patient patient : PatientDAO.getAll()){
            if (patient.getDob().isBefore(LocalDate.now().minusYears(12)) || patient.getDob().equals(LocalDate.now().minusYears(12))){
                Assert.assertTrue(over12.contains(patient));
            }
        }
    }

    @Test
    public void testUnder12(){
        PatientModel model = new PatientModel();
        List <Patient> under12 = model.under12();
        for (Patient patient : under12){
            Assert.assertTrue(patient.getDob().isAfter(LocalDate.now().minusYears(12)));
        }
        for (Patient patient : PatientDAO.getAll()){
            if (patient.getDob().isAfter(LocalDate.now().minusYears(12))){
                Assert.assertTrue(under12.contains(patient));
            }
        }
    }
}