package seg.major.model.database;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import org.junit.Test;

import seg.major.model.Week;

public class AppointmentDAOTest {

  @Test
  public void testMapToSQLQuery() {
    String expectedString = "SELECT * FROM appointment WHERE ( patient_id='32' AND status='2' );";

    HashMap<String, String> toTest = new HashMap<>();
    toTest.put("status", "2");
    toTest.put("patient_id", "32");

    String result = AppointmentDAO.mapToSQLQuery(toTest);

    assertTrue(result.equals(expectedString));
  }

  @Test
  public void testBuildWeekQuery() {
    String expectedString = "SELECT * FROM appointment WHERE due_date BETWEEN '2019-03-25' AND '2019-03-29';";
    Week testWeek = new Week(LocalDate.of(2019, 03, 25));

    String result = AppointmentDAO.buildWeekQuery(testWeek);

    assertTrue("Not the same", result.equals(expectedString));
  }

}
