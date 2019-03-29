package seg.major.model.database;

import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import org.junit.Test;

public class UserDAOTest {

  @Test
  public void testMapToSQLQuery() {
    String expectedString = "SELECT * FROM user WHERE ( password='testPassword' AND username='testUsername' );";

    HashMap<String, String> toTest = new HashMap<>();
    toTest.put("username", "testUsername");
    toTest.put("password", "testPassword");

    String result = UserDAO.mapToSQLQuery(toTest);

    assertTrue(result.equals(expectedString));
  }

}
