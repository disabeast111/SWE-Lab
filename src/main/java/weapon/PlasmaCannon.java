package weapon;

import exceptions.WeaponException;
import gameplay.TimerObserver;

/**
 * @author Ethan J
 */
public class PlasmaCannon extends GenericWeapon implements TimerObserver {
  /**
   * Constructor that initializes a PlasmaCannon
   * Sets base stats
   */
  public PlasmaCannon() {
    baseDamage = 50;
    maxRange = 40;
    rateOfFire = 1;
    maxAmmo = 4;
    currentAmmo = 4;
    shotsLeft = rateOfFire;
  }

  /**
   * Method to fire a PlasmaCannon
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
    double ca = currentAmmo;
    double ma = maxAmmo;
    double doubleDamage = 0;
    doubleDamage = bd * (ca / ma);
    int intDamage = Double.valueOf(Math.floor(doubleDamage)).intValue();
    currentAmmo -= 1;
    return intDamage;
  }

  /**
   * Method to return a String description of weapon
   * @return PlasmaCannon
   */
  @Override
  public String toString() {
    return "PlasmaCannon";
  }

}
