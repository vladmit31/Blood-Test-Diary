package seg.major.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.time.LocalDate;

import seg.major.structure.Appointment;

public class AppointmentDAO implements DAOInterface<Appointment> {

  private static final String TABLE_NAME = "appointment";
  private static final String ID = "id";
  private static final String STATUS = "status";
  private static final String DUE_DATE = "due_date";
  private static final String PATIENT_ID = "appointment_id";

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
    String query = "INSERT INTO appointment (id, status, due_date, patient_id) VALUES (?, ?, ?,?)";
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
      toReturn = resultSetToUser(rs);
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
    String query = "UPDATE appointment SET ? status = ? due_date = ? patient_id = ? WHERE id = ?";

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
      toReturn = resultSetToUser(rs);
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
   * @param toGet Map of atttributes and the values to match to a record
   * @return the matched appointment
   */
  public Appointment get(Map<String, String> toGet) {
    return null;
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
  private static Appointment resultSetToUser(ResultSet toConvert) throws SQLException {
    if (toConvert.next()) {
      int id = toConvert.getInt(ID);
      int status = toConvert.getInt(STATUS);
      LocalDate dueDate = toConvert.getDate(DUE_DATE).toLocalDate();
      int patientID = toConvert.getInt(PATIENT_ID);
      return new Appointment(id, status, dueDate, patientID);

    } else {
      return null;
    }
  }

  /**
   * Convert a ResultSet to an array containing all records contained
   * 
   * @param toConvert the ResultSet to be read
   * @return the users
   */
  private Appointment[] resultSetToUserArray(ResultSet toConvert) throws SQLException {
    Appointment[] toReturn = new Appointment[toConvert.getFetchSize()];
    int i = 0;
    while (toConvert.next()) {
      toReturn[i] = resultSetToUser(toConvert);
      i++;
    }

    return toReturn;
  }

}
