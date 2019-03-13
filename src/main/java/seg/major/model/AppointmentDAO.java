package seg.major.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import seg.major.structure.Appointment;

public class AppointmentDAO implements DAOInterface<Appointment> {

  private static final String TABLE_NAME = "appointment";
  private static final String ID = "id";
  private static final String STATUS = "status";
  private static final String DUE_DATE = "due_date";
  private static final String PATIENT_ID = "patient_id";

  public AppointmentDAO() {
  }

  /** ---------- Inherited / Implemented ---------- */

  /**
   * Lookup a record by ID
   * 
   * @param toGet the ID of the record
   * @return the appointment corresponding to the record
   */
  public Appointment getById(int toGet) {
    return get(toGet);
  }

  /**
   * Remove a record by ID
   * 
   * @param toRemove the ID to remove
   */
  public void removeById(int toRemove) {
    String query = "DELETE FROM appointment WHERE id = ? LIMIT 1;";
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
   * @param toCreate the appointment to create as a record
   */
  public void create(Appointment toCreate) {
    String query = "INSERT INTO appointment (status, due_date, patient_id) VALUES (?, ?, ?)";
    PreparedStatement ps = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.setInt(1, toCreate.getStatus());
      ps.setDate(2, Date.valueOf(toCreate.getDueDate()));
      ps.setInt(3, toCreate.getPatientID());
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
   * @return the appointment corresponding to the record
   */
  public Appointment get(int toGet) {
    String query = "SELECT * FROM appointment WHERE id = ? LIMIT 1;";
    Appointment toReturn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.setInt(1, toGet);
      ps.execute("USE db");
      rs = ps.executeQuery();
      toReturn = resultSetToAppointment(rs);
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
   * @param toUpdate the appointment to update
   */
  public void update(Appointment toUpdate) {
    String query = "UPDATE appointment SET( status = ? due_date = ? patient_id = ? ) WHERE id = ?";

    Appointment toReturn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.setInt(1, toUpdate.getStatus());
      ps.setDate(2, Date.valueOf(toUpdate.getDueDate()));
      ps.setInt(3, toUpdate.getPatientID());
      ps.setInt(4, toUpdate.getID());
      ps.execute("USE db");
      rs = ps.executeQuery();
      toReturn = resultSetToAppointment(rs);
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
   * @param toRemove the appointment to remove
   */
  public void remove(Appointment toRemove) {
    removeById(toRemove.getID());
  }

  /**
   * @param toGet Map of attributes and the values to match to a record
   * @return the matched appointment
   */
  public Appointment get(Map<String, String> toGet) {

    String query = mapToSQLQuery(toGet);
    Appointment toReturn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.execute("USE db");
      rs = ps.executeQuery();
      toReturn = resultSetToAppointment(rs);
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
   * @param toGet Map of attributes and the values to match to a record
   * @return the matched appointment
   */
  public static Appointment[] getAll(Map<String, String> toGet) {

    String query = mapToSQLQuery(toGet);
    Appointment[] toReturn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.execute("USE db");
      rs = ps.executeQuery();
      toReturn = resultSetToAppointmentArray(rs);
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
   * Get all the current Appointments
   * 
   * @return a Appointment array that contains every appointment in the table
   */
  public static Appointment[] getAll() {
    String query = "SELECT * FROM appointment;";
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    Appointment[] toReturn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.execute("USE db");
      rs = ps.executeQuery();
      toReturn = resultSetToAppointmentArray(rs);

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

  private static int getLastInsertedAppointmentID() {

    String query = "SELECT MAX(id) FROM appointment";

    Appointment toReturn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;

    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.execute("USE db");
      rs = ps.executeQuery(query);

      if (rs.next()) {
        return rs.getInt(1);
      }

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
    return -1;
  }

  /**
   * Get the appointment the ResultSet pointer currently points to
   * 
   * @param toConvert the ResultSet to be read
   * @return the appointment
   */
  private static Appointment resultSetToAppointment(ResultSet toConvert) throws SQLException {

    int id = toConvert.getInt(ID);
    int status = toConvert.getInt(STATUS);
    LocalDate dueDate = toConvert.getDate(DUE_DATE).toLocalDate();
    int patientID = toConvert.getInt(PATIENT_ID);
    return new Appointment(id, status, dueDate, patientID);

  }

  /**
   * Convert a ResultSet to an array containing all records contained
   * 
   * @param toConvert the ResultSet to be read
   * @return the users
   */
  private static Appointment[] resultSetToAppointmentArray(ResultSet toConvert) throws SQLException {
    List<Appointment> toReturn = new ArrayList<Appointment>();
    while (toConvert.next()) {
      toReturn.add(resultSetToAppointment(toConvert));
    }
    return toReturn.toArray(new Appointment[toReturn.size()]);
  }

  /**
   * Build a statment of the form: "SELECT * FROM appointment WHERE (col1 = val1
   * AND col2 = val2 AND ... colN = valN);"
   * 
   * from the provided map.
   * 
   * @param toQuery the map to convery to a query
   * @return the constructed statement
   */
  private static String mapToSQLQuery(Map<String, String> toQuery) {

    // build the statement
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT * FROM appointment WHERE ( ");
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

  /**
   * Get the appointments for this week only
   * 
   * @param weekStart the start of the week
   * @return the current week's appointments
   */
  public static Appointment[] getCurrentWeek(Week curWeek) {

    String query = buildWeekQuery(curWeek);
    Appointment[] toReturn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.execute("USE db");
      rs = ps.executeQuery();
      toReturn = resultSetToAppointmentArray(rs);
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
    System.out.println(query);
    System.out.print("Size of Appointments arraylsit is: " + toReturn.length);

    return toReturn;
  }

  private static String buildWeekQuery(Week curWeek) {
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT * FROM appointment WHERE due_date BETWEEN '");
    sb.append(curWeek.getMondayDate());
    sb.append("' AND '");
    sb.append(curWeek.getFridayDate());
    sb.append("';");
    return sb.toString();
  }
}
