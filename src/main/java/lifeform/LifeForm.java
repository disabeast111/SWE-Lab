package lifeform;

import exceptions.WeaponException;
import weapon.Weapon;

/**
 * @author David W
 */
public abstract class LifeForm extends java.lang.Object {
  private String myName;
  protected int currentLifePoints;
  protected int attackStrength;
  protected Weapon weapon;
  protected int col = -1;
  protected int row = -1;

  /**
   * @param name
   * @param points
   */
  public LifeForm(String name, int points) {
    this(name, points, 1);
  }

  /**
   * LifeForm constructor
   * 
   * @param name
   * @param points
   * @param attack
   */
  public LifeForm(String name, int points, int attack) {
    myName = name;
    if (points > 0) {
      currentLifePoints = points;
    }
    if (attack >= 0) {
      attackStrength = attack;
    }
  }

  public String getName() {
    return myName;
  }

  public int getCurrentLifePoints() {
    return currentLifePoints;
  }

  /**
   * @param damage
   */
  public void takeHit(int damage) {
    currentLifePoints -= damage;
    if (currentLifePoints < 0) {
      currentLifePoints = 0;
    }
  }

  public int getAttackStrength() {
    return attackStrength;
  }

  /**
   * attack
   * 
   * @param opponent (type LifeForm)
   * @throws WeaponException
   */
  public void attack(LifeForm opponent, int distance) throws WeaponException { // test
    if (getCurrentLifePoints() > 0) {
      if (hasWeapon() && weapon.getCurrentAmmo() > 0) {
        opponent.takeHit(weapon.fire(distance));
      } else if (distance < 5) {
        opponent.takeHit(getAttackStrength());
      }
    }
  }

  /**
   * @return current weapon
   */
  public Weapon dropWeapon() {
    Weapon temp = weapon;
    weapon = null;
    return temp;
  }

  /**
   * @return bool if has weapon
   */
  public boolean hasWeapon() {
    if (weapon != null) {
      return true;
    }
    return false;
  }

  /**
   * @param newWeapon
   * @return bool if pickup successful
   */
  public boolean pickUpWeapon(Weapon newWeapon) {
    if (weapon == null) {
      weapon = newWeapon;
      return true;
    }
    return false;
  }
  
  public void setLocation(int r, int c) {
    if(r >= -1 || c >= -1) {
      row = r;
      col = c;
    }
  }
  
  public int getCol() {
    return col;
  }
  
  public int getRow() {
    return row;
  }
}
