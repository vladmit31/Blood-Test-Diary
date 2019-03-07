package seg.major.model;

import java.util.Map;

/**
 * DAOInterface is a basic interface that all DAO's should use. It has many
 * basic operations that allow it to work as a simple data structure for the
 * models to manipulate and work with. Each DAO shares the same connection, this
 * can be directly interacted by using the execute() method.
 */
public interface DAOInterface<T> {
  /**
   * @param toCreate the entity to create as a record
   */
  public void create(T toCreate);

  /**
   * @param toGet the ID of the record
   * @return the entity corresponding to the record
   */
  public T get(int toGet);

  /**
   * @param toUpdate the entity to update
   */
  public void update(T toUpdate);

  /**
   * @param toRemove the entity to remove
   */
  public void remove(T toRemove);

  /**
   * @param toGet Map of atttributes and the values to match to a record
   * @return the matched entiry
   */
  public T get(Map<String, String> toGet);
}