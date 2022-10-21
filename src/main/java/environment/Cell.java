// author: David W

package environment;

import lifeform.LifeForm;
import weapon.Weapon;

/**
 * @author David W, Spencer H
 */
public class Cell {

  LifeForm life1;
  Weapon weapon1;
  Weapon weapon2;
  int count = 0;

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
  
  public boolean addWeapon(Weapon weapon) {
    if(getWeaponsCount() == 0) {
      weapon1 = weapon;
      count += 1;
      return true;
    } else if(getWeaponsCount() == 1) {
      weapon2 = weapon;
      count += 1;
      return true;
    }
      return false;
  }
  
  public LifeForm getLifeForm() {
    return life1;
  }
  
  public Weapon getWeapon1() {
    return weapon1;
  }
  
  public Weapon getWeapon2() {
    return weapon2;
  }
  
  public int getWeaponsCount() {
    return count;
  }
  
  public void removeLifeForm() {
    life1 = null;
  }
  
  public Weapon removeWeapon(Weapon weapon) {
    if(getWeaponsCount() > 0) { 
      if(weapon1 == weapon) {
        count -= 1;
        return weapon1;
      } else if(weapon2 == weapon) {
        count -= 1;
        return weapon;
      }
    }
    return null;
  }
}
