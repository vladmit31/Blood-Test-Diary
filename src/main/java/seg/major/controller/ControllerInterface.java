package seg.major.controller;

import java.util.Map;

/**
 * Interface to allow all view controllers to share the PrimaryController
 */
public interface ControllerInterface {
  public void setScreenParent(PrimaryController primaryController);

  public void setData(Map<String, String> toInject);

  public void addData(String fxID, String toAdd);

}