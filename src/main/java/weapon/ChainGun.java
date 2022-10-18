// author: Ethan J

package weapon;

import exceptions.WeaponException;

public class ChainGun extends GenericWeapon {
  /**
   * Constructor that initializes a ChainGun Sets base stats
   */
  public ChainGun() {
    baseDamage = 15;
    maxRange = 60;
    rateOfFire = 4;
    maxAmmo = 40;
    currentAmmo = 40;
    shotsLeft = rateOfFire;
  }

  /**
   * Method to fire a ChainGun
   * 
   * @param distance is how far to fire the weapon
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
    double doubleDamage = 0;
    doubleDamage = bd * (dis / mr);
    int intDamage = Double.valueOf(Math.floor(doubleDamage)).intValue();
    currentAmmo -= 1;
    return intDamage;
  }

  /**
   * Method to return a description of weapon
   */
  @Override
  public String toString() {
    return "ChainGun";
  }

}
