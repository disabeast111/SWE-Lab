package lifeform;

import exceptions.WeaponException;
import gameplay.TimerObserver;
import weapon.Weapon;

/**
 * Lab 4, 6
 * @author David W 
 * Lab 5
 * @author Ethan J
 */
public abstract class LifeForm extends java.lang.Object implements TimerObserver{
  private String myName;
  protected int currentLifePoints;
  protected int attackStrength;
  protected Weapon weapon;
  protected int col = -1;
  protected int row = -1;
  private int currentDirection = 0;
  protected int maxSpeed = 0;
  protected int movesLeft = 0;

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
    movesLeft = maxSpeed;
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

  /**
   * Setter for LifeForm location
   * 
   * @param r is row
   * @param c is column
   */
  public void setLocation(int r, int c) {
    if (r >= 0 && c >= 0) {
      row = r;
      col = c;
    }
  }

  /**
   * Getter for LifeForm column
   * 
   * @return column
   */
  public int getCol() {
    return col;
  }

  /**
   * Getter for LifeForm row
   * 
   * @return row
   */
  public int getRow() {
    return row;
  }
  
  /**
   * Getter for currentDirection
   * 
   * @return currentDirection
   */
  public int getCurrentDirection() {
    return currentDirection;
  }
  
  /**
   * Getter for maxSpeed
   * 
   * @return maxSpeed
   */
  public int getMaxSpeed() {
    return maxSpeed;
  }
  
  /**
   * Getter for movesLeft
   * 
   * @return movesLeft
   */
  public int getMovesLeft() {
    return movesLeft;
  }
  
  /**
   * Sets movesLeft to input param
   * 
   * @param movesLeft
   */
  public void setMovesLeft(int mL) {
    movesLeft = mL;
  }
  
  /**
   * Sets direction to input param
   * 
   * @param direction
   */
  public void setDirection(int dir) {
    if (dir >= 0 && dir <= 3) {
      currentDirection = dir;
    }
  }

  @Override
  public void updateTime(int time) {
    movesLeft = maxSpeed;
  }
  //Setup this getter to retrieve current weapon in command
  public Weapon getCurrentWeapon() {
    return weapon;
  }
}
