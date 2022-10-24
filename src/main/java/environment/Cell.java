package environment;

import lifeform.LifeForm;

import weapon.Weapon;

/**
 * Lab 4
 * @author David W
 * Lab 5
 * @author Spencer H
 */
public class Cell {

  LifeForm life1;
  Weapon weapon1 = null;
  Weapon weapon2 = null;
  int count = 0;

  /**
   * addLifeForm
   * 
   * @param entity the LifeForm
   * @return boolean
   */
  public boolean addLifeForm(LifeForm entity) {
    if (getLifeForm() == null) {
      life1 = entity;
      return true;
    }
    return false;
  }
  
  /**
   * Adds up to two weapons to the Cell
   * @param weapon the weapon to be added.
   * @return true for a weapon added, else false
   */
  public boolean addWeapon(Weapon weapon) {
    if (weapon1 == null && weapon2 != weapon) {
      weapon1 = weapon;
      count += 1;
      return true;
    } else if (weapon2 == null && weapon1 != weapon) {
      weapon2 = weapon;
      count += 1;
      return true;
    }
    return false;
  }
  
  /**
   * LifeForm getter
   * @return the LifeForm in the Cell
   */
  public LifeForm getLifeForm() {
    return life1;
  }
  
  /**
   * Gets the weapon stored first, Weapon 1
   * @return Weapon 1
   */
  public Weapon getWeapon1() {
    return weapon1;
  }
  
  /**
   * Gets the weapon stored second, Weapon 2
   * @return Weapon 2
   */
  public Weapon getWeapon2() {
    return weapon2;
  }
  
  /**
   * Number of Weapons in the Cell getter
   * @return Number of Weapons in the Cell
   */
  public int getWeaponsCount() {
    return count;
  }
  
  /**
   * Removes the LifeForm in the Cell
   */
  public void removeLifeForm() {
    life1 = null;
  }
  
  /**
   * Removes a specific Weapon from the Cell,
   * if it currently exists there.
   * @param weapon The Weapon to be removed
   * @return The Weapon removed
   */
  public Weapon removeWeapon(Weapon weapon) {
    Weapon removed;
    if (getWeaponsCount() > 0) { 
      if (weapon1 == weapon) {
        removed = weapon1;
        weapon1 = null;
        count -= 1;
        return removed;
      } else if (weapon2 == weapon) {
        removed = weapon2;
        weapon2 = null;
        count -= 1;
        return removed;
      }
    }
    return null;
  }
}
