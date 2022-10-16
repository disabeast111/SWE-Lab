package lifeform;

import exceptions.WeaponException;
import weapon.Weapon;

public abstract class LifeForm extends java.lang.Object {
  private String myName;
  protected int currentLifePoints;
  protected int attackStrength;
  protected Weapon weapon;

  /**
   * @param name
   * @param points
   */
  public LifeForm(String name, int points) {
    this(name, points, 1);
  }

  /**
   * LifeForm constructor
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
   * @param opponent (type LifeForm)
   * @throws WeaponException 
   */
  public void attack(LifeForm opponent, int distance) throws WeaponException {        //test
    if (getCurrentLifePoints() > 0) {
      if (hasWeapon()&& weapon.getCurrentAmmo() > 0) {
        opponent.takeHit(weapon.fire(distance));
      }
      else if (distance < 5) {
        opponent.takeHit(getAttackStrength());
      }
    }
  }
  
  public Weapon dropWeapon() {
    Weapon temp = weapon;
    weapon = null;
    return temp;
    }
  
  public boolean hasWeapon() {
    if (weapon != null) return true;
    return false;
  }
  
  public boolean pickUpWeapon(Weapon newWeapon) {
    if (weapon == null) {
      weapon = newWeapon;
      return true;
    }
    return false;
  }
}
