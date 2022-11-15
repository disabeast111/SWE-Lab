package weapon;

import exceptions.WeaponException;

/**
 * @author Ethan J
 * Made this to have just 5 range for testing commands
 */
public class MockWeaponLR extends GenericWeapon {

  public MockWeaponLR() throws WeaponException {
    baseDamage = 10;
    maxRange = 5;
    rateOfFire = 5;
    maxAmmo = 10;
    currentAmmo = 10;
    shotsLeft = rateOfFire;
  }

  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Cannot be a negative distance");
    }
    if (currentAmmo == 0 || shotsLeft <= 0) {
      return 0;
    }
    shotsLeft -= 1;
    currentAmmo -= 1;
    if (distance > maxRange) {
      return 0;
    }
    return baseDamage;
  }

  @Override
  public String toString() {
    return "MockWeapon";
  }

}