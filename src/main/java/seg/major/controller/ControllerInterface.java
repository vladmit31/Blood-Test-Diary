package seg.major.controller;

import java.util.HashMap;

/**
 * Interface to allow all view controllers to share the PrimaryController
 */
public interface ControllerInterface {
  public void setScreenParent(PrimaryController primaryController);

  public void setData(HashMap<String, String[]> toInject);

}