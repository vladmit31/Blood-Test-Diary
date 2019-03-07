package seg.major.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
   * Execute a command through the data source
   * 
   * @param command the command to be executed
   */
  public void execute(String command) {
    DAOConnection.execute(command);
  };

  /**
   * @return how many records are stored
   */
  public int size() {
    return 0;
  };

  /**
   * Lookup a record by ID
   * 
   * @param toGet the ID to lookup and fetch the record for
   */
  public User getById(int toGet) {
    return null;
  };

  /**
   * Remove a record by ID
   * 
   * @param toGet the ID to remove
   */
  public void removeById(int toRemove) {
  };

  /**
   * @param toCreate the user to create as a record
   */
  public void create(User toCreate) {
  };

  /**
   * @param toGet the ID of the record
   * @return the user corresponding to the record
   */
  public User get(int toGet) {
    return null;
  };

  /**
   * @param toUpdate the user to update
   */
  public void update(User toUpdate) {
  }

  /**
   * @param toRemove the user to remove
   */
  public void remove(User toRemove) {
  };

  /**
   * @param toGet Map of atttributes and the values to match to a record
   * @return the matched user
   */
  public User get(Map<String, String> toGet) {
    return null;
  };

  /** ---------- Inherited / Implemented ---------- */

  /**
   * Lookup a record by username
   * 
   * @param toGet the username to lookup and fetch the record for
   * @return the user that was found
   */
  public static User getByUsername(String toGet) {
    String query = " SELECT * FROM user WHERE username = ?";

    try {
      PreparedStatement s = DAOConnection.getConnection().prepareStatement(query);
      s.setString(1, toGet);
      s.execute("USE db");
      ResultSet res = s.executeQuery();

      while (res.next()) {
        int id = res.getInt(ID);
        String username = res.getString(USERNAME);
        String password = res.getString(PASSWORD);
        String email = res.getString(EMAIL);
        int is_admin = res.getInt(IS_ADMIN);
        return new User(id, username, email, password, is_admin);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }

}
