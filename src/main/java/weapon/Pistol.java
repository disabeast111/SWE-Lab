package weapon;

import exceptions.WeaponException;
import gameplay.TimerObserver;

/**
 * @author Ethan J
 */
public class Pistol extends GenericWeapon implements TimerObserver {
  /**
   * Constructor that initializes a Pistol Sets base stats
   */
  public Pistol() {
    baseDamage = 10;
    maxRange = 50;
    rateOfFire = 2;
    maxAmmo = 10;
    currentAmmo = 10;
    shotsLeft = rateOfFire;
  }

  /**
   * Method to fire a Pistol
   * @param distance is how far to fire the weapon
   * @return damage after calculation
   */
  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Cannot be a negative distance");
    }
    if (currentAmmo == 0 || shotsLeft <= 0) {
      return 0;
    }

    shotsLeft -= 1;

    if (distance > maxRange) {
      currentAmmo -= 1;
      return 0;
    }
    double bd = baseDamage;
    double mr = maxRange;
    double dis = distance;
    double doubleDamage = bd * ((mr - dis + 10) / mr);
    int intDamage = Double.valueOf(Math.floor(doubleDamage)).intValue();
    currentAmmo -= 1;
    return intDamage;
  }

  /**
   * Method to return a String description of weapon
   * @return Pistol
   */
  @Override
  public String toString() {
    return "Pistol";
  }

}
