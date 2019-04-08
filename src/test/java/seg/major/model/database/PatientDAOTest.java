package seg.major.model.database;

import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import org.junit.Test;

public class PatientDAOTest {

  @Test
  public void testMapToSQLQuery() {
    String expectedString = "SELECT * FROM patient WHERE ( fname='testFname' AND sname='testSname');";

    HashMap<String, String> toTest = new HashMap<>();
    toTest.put("fname", "testFname");
    toTest.put("sname", "testSname");

    String result = PatientDAO.mapToSQLQuery(toTest);

    assertTrue(result.equals(expectedString));
  }

}
