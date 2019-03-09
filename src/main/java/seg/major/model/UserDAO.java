package seg.major.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import seg.major.structure.User;

public class UserDAO implements DAOInterface<User> {

  private static final String TABLE_NAME = "user";
  private static final String ID = "id";
  private static final String USERNAME = "username";
  private static final String EMAIL = "email";
  private static final String PASSWORD = "password";
  private static final String IS_ADMIN = "is_admin";

  public UserDAO() {
  }

  /** ---------- Inherited / Implemented ---------- */

  /**
   * Lookup a record by ID
   * 
   * @param toGet the ID of the record
   * @return the user corresponding to the record
   */
  public User getById(int toGet) {
    return get(toGet);
  }

  /**
   * Remove a record by ID
   * 
   * @param toRemove the ID to remove
   */
  public void removeById(int toRemove) {
    String query = "DELETE FROM user WHERE id = ? LIMIT 1;";
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
   * @param toCreate the user to create as a record
   */
  public void create(User toCreate) {
    String query = "INSERT INTO user (username, email, password, is_admin) VALUES (?, ?, ?, ?)";
    PreparedStatement ps = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.setString(1, toCreate.getUsername());
      ps.setString(2, toCreate.getEmail());
      ps.setString(3, toCreate.getPassword());
      ps.setInt(4, toCreate.getIsAdmin());
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
   * @return the user corresponding to the record
   */
  public User get(int toGet) {
    String query = "SELECT * FROM user WHERE id = ? LIMIT 1;";
    User toReturn = null;
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
   * @param toUpdate the user to update
   */
  public void update(User toUpdate) {
    String query = "UPDATE user SET username = ?, email = ?, password = ?, is_admin ?  WHERE id = ? AND email = ?";
    User toReturn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.setString(1, toUpdate.getUsername());
      ps.setString(2, toUpdate.getEmail());
      ps.setString(3, toUpdate.getPassword());
      ps.setInt(4, toUpdate.getIsAdmin());
      ps.setInt(5, toUpdate.getID());
      ps.setString(6, toUpdate.getEmail());
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
   * @param toRemove the user to remove
   */
  public void remove(User toRemove) {
    removeById(toRemove.getID());
  }

  /**
   * @param toGet Map of atttributes and the values to match to a record
   * @return the matched user
   */
  public User get(Map<String, String> toGet) {
    return null;
  }

  /**
   * Get all the current Users
   * 
   * @return a User array that contains every user in the table
   */
  public User[] getAll() {
    String query = "SELECT * FROM user;";
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    User[] toReturn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.execute("USE db");
      rs = ps.executeQuery();
      toReturn = resultSetToUserArray(rs);

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
   * Lookup a record by username
   * 
   * @param toGet the username to lookup and fetch the record for
   * @return the user that was found
   */
  public static User getByUsername(String toGet) {
    String query = "SELECT * FROM user WHERE username = ? LIMIT 1;";
    User toReturn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = null;
    try {
      conn = DAOConnection.getConnection();
      ps = conn.prepareStatement(query);
      ps.setString(1, toGet);
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
   * Get the user the ResultSet pointer currently points to
   * 
   * @param toConvert the ResultSet to be read
   * @return the user
   */
  private static User resultSetToUser(ResultSet toConvert) throws SQLException {
    if (toConvert.next()) {
      int id = toConvert.getInt(ID);
      String username = toConvert.getString(USERNAME);
      String password = toConvert.getString(PASSWORD);
      String email = toConvert.getString(EMAIL);
      int isAdmin = toConvert.getInt(IS_ADMIN);
      return new User(id, username, email, password, isAdmin);

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
  private User[] resultSetToUserArray(ResultSet toConvert) throws SQLException {
    User[] toReturn = new User[toConvert.getFetchSize()];
    int i = 0;
    while (toConvert.next()) {
      toReturn[i] = resultSetToUser(toConvert);
      i++;
    }

    return toReturn;
  }

}
