// author: David W
package environment;

import lifeform.LifeForm;

public class Cell {

  LifeForm life1;

  /**
   * addLifeForm
   * 
   * @param entity the lifeform
   * @return boolean
   */
  public boolean addLifeForm(LifeForm entity) {
    if (getLifeForm() == null) {
      life1 = entity;
      return true;
    }
    return false;
  }

  public void removeLifeForm() {
    life1 = null;
  }

  public LifeForm getLifeForm() {
    return life1;
  }
}
