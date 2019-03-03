package seg.major.controller;

/**
 * Interface to allow all view controllers to share the PrimaryController
 */
public interface ViewsController {
  public void setScreenParent(PrimaryController primaryController);

}