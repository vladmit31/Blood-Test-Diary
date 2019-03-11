package seg.major.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import seg.major.structure.Contact;

public class ContactDAO implements DAOInterface<Contact> {

  private static final String TABLE_NAME = "contact";
  private static final String ID = "id";
  private static final String FORENAME = "forename";
  private static final String SURNAME = "surname";
  private static final String RELATIONSHIP = "relationship";
  private static final String PHONE = "phone";
  private static final String EMAIL = "email";
  private static final String PATIENT_ID = "patient_id";

  public ContactDAO() {
  }

  /** ---------- Inherited / Implemented ---------- */

  /**
   * Lookup a record by ID
   * 
   * @param toGet the ID of the record
   * @return the contact corresponding to the record
   */
  public Contact getById(int toGet) {
    return get(toGet);
  }

  /**
   * Remove a record by ID
   * 
   * @param toRemove the ID to remove
   */
  public void removeById(int toRemove) {
    String query = "DELETE FROM contact WHERE id = ? LIMIT 1;";
    PreparedStatement ps = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.setInt(1, toRemove);
      ps.execute("USE db");
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        ps.close();
      } catch (Exception e) {
      }
      try {
        conn.close();
      } catch (Exception e) {
      }
    }
  }

  /**
   * @param toCreate the contact to create as a record
   */
  public void create(Contact toCreate) {
    String query = "INSERT INTO contact (forename, surname, relationship, phone, email, patient_id) VALUES (?, ?, ?, ?, ?, ?)";
    PreparedStatement ps = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.setString(1, toCreate.getForename());
      ps.setString(2, toCreate.getSurname());
      ps.setString(3, toCreate.getRelationship());
      ps.setString(4, toCreate.getPhone());
      ps.setString(5, toCreate.getEmail());
      ps.setInt(6, toCreate.getPatientID());
      ps.execute("USE db");
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {

      try {
        ps.close();
      } catch (Exception e) {
      }
      try {
        conn.close();
      } catch (Exception e) {
      }
    }

  }

  /**
   * @param toGet the ID of the record
   * @return the contact corresponding to the record
   */
  public Contact get(int toGet) {
    String query = "SELECT * FROM contact WHERE id = ? LIMIT 1;";
    Contact toReturn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.setInt(1, toGet);
      ps.execute("USE db");
      rs = ps.executeQuery();
      toReturn = resultSetToContact(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        rs.close();
      } catch (Exception e) {
      }
      try {
        ps.close();
      } catch (Exception e) {
      }
      try {
        conn.close();
      } catch (Exception e) {
      }
    }

    return toReturn;
  }

  /**
   * @param toUpdate the contact to update
   */
  public void update(Contact toUpdate) {
    String query = "UPDATE contact SET forename = ?, surname = ?, relationship = ?, phone = ?, email = ?, patient_id = ?   WHERE id = ?";
    Contact toReturn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.setString(1, toUpdate.getForename());
      ps.setString(2, toUpdate.getSurname());
      ps.setString(3, toUpdate.getRelationship());
      ps.setString(4, toUpdate.getPhone());
      ps.setString(5, toUpdate.getEmail());
      ps.setInt(6, toUpdate.getPatientID());
      ps.setInt(7, toUpdate.getID());
      ps.execute("USE db");
      rs = ps.executeQuery();
      toReturn = resultSetToContact(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        rs.close();
      } catch (Exception e) {
      }
      try {
        ps.close();
      } catch (Exception e) {
      }
      try {
        conn.close();
      } catch (Exception e) {
      }
    }

  }

  /**
   * @param toRemove the contact to remove
   */
  public void remove(Contact toRemove) {
    removeById(toRemove.getID());
  }

  /**
   * @param toGet Map of atttributes and the values to match to a record
   * @return the matched contact
   */
  public Contact get(Map<String, String> toGet) {

    String query = mapToSQLQuery(toGet);
    Contact toReturn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.execute("USE db");
      rs = ps.executeQuery();
      toReturn = resultSetToContact(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        rs.close();
      } catch (Exception e) {
      }
      try {
        ps.close();
      } catch (Exception e) {
      }
      try {
        conn.close();
      } catch (Exception e) {
      }
    }

    return toReturn;
  }

  /**
   * @param toGet Map of atttributes and the values to match to a record
   * @return the matched contact
   */
  public Contact[] getAll(Map<String, String> toGet) {

    String query = mapToSQLQuery(toGet);
    Contact[] toReturn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.execute("USE db");
      rs = ps.executeQuery();
      toReturn = resultSetToContactArray(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        rs.close();
      } catch (Exception e) {
      }
      try {
        ps.close();
      } catch (Exception e) {
      }
      try {
        conn.close();
      } catch (Exception e) {
      }
    }

    return toReturn;
  }

  /**
   * Get all the current Contacts
   * 
   * @return a Contact array that contains every contact in the table
   */
  public Contact[] getAll() {
    String query = "SELECT * FROM contact;";
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    Contact[] toReturn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.execute("USE db");
      rs = ps.executeQuery();
      toReturn = resultSetToContactArray(rs);

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        rs.close();
      } catch (Exception e) {
      }
      try {
        ps.close();
      } catch (Exception e) {
      }
      try {
        conn.close();
      } catch (Exception e) {
      }
    }

    return toReturn;
  }

  /** ---------- Inherited / Implemented ---------- */

  /**
   * Lookup a record by contactname
   * 
   * @param toGet the contactname to lookup and fetch the record for
   * @return the contact that was found
   */
  public static Contact getByContactname(String toGet) {
    String query = "SELECT * FROM contact WHERE contactname = ? LIMIT 1;";
    Contact toReturn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.setString(1, toGet);
      ps.execute("USE db");
      rs = ps.executeQuery();
      toReturn = resultSetToContact(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        rs.close();
      } catch (Exception e) {
      }
      try {
        ps.close();
      } catch (Exception e) {
      }
      try {
        conn.close();
      } catch (Exception e) {
      }
    }

    return toReturn;
  }

  /**
   * Get the contact the ResultSet pointer currently points to
   * 
   * @param toConvert the ResultSet to be read
   * @return the contact
   */
  private static Contact resultSetToContact(ResultSet toConvert) throws SQLException {
    if (toConvert.next()) {
      int id = toConvert.getInt(ID);
      String contactname = toConvert.getString(USERNAME);
      String password = toConvert.getString(PASSWORD);
      String email = toConvert.getString(EMAIL);
      int isAdmin = toConvert.getInt(IS_ADMIN);
      return new Contact(id, contactname, email, password, isAdmin);

    } else {
      return null;
    }
  }

  /**
   * Convert a ResultSet to an array containing all records contained
   * 
   * @param toConvert the ResultSet to be read
   * @return the contacts
   */
  private static Contact[] resultSetToContactArray(ResultSet toConvert) throws SQLException {
    List<Contact> toReturn = new ArrayList<Contact>();
    while (toConvert.next()) {
      toReturn.add(resultSetToContact(toConvert));
    }

    return toReturn.toArray(new Contact[toReturn.size()]);
  }

  /**
   * Build a statment of the form: "SELECT * FROM contact WHERE (col1 = val1 AND col2
   * = val2 AND ... colN = valN);"
   * 
   * from the provided map.
   * 
   * @param toQuery the map to convery to a query
   * @return the constructed statement
   */
  private String mapToSQLQuery(Map<String, String> toQuery) {

    // build the statement
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT * FROM contact WHERE ( ");
    for (Map.Entry<String, String> entry : toQuery.entrySet()) {
      sb.append(entry.getKey());
      sb.append("='");
      sb.append(entry.getValue());
      sb.append("' AND ");
    }
    // remove the last AND then close the brackets
    sb.delete(sb.length() - 4, sb.length());
    sb.append(");");

    return sb.toString();
  }

}
