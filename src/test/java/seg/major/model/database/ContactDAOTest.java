package seg.major.model.database;

import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import org.junit.Test;

public class ContactDAOTest {

  @Test
  public void testMapToSQLQuery() {
    String expectedString = "SELECT * FROM contact WHERE ( forename='testForename' AND surname='testSurname' );";

    HashMap<String, String> toTest = new HashMap<>();
    toTest.put("forename", "testForename");
    toTest.put("surname", "testSurname");

    String result = ContactDAO.mapToSQLQuery(toTest);

    assertTrue(result.equals(expectedString));
  }

}
