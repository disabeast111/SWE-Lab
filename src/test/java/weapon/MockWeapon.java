package weapon;

import exceptions.WeaponException;

public class MockWeapon extends GenericWeapon {

  public MockWeapon() throws WeaponException {
    baseDamage = 10;
    maxRange = 10;
    rateOfFire = 10;
    maxAmmo = 10;
    currentAmmo = 10;
  }

  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Cannot be a negative distance");
    }
    if (currentAmmo == 0) {
      return 0;
    }
    if (distance > maxRange) {
      currentAmmo = currentAmmo - 1;
      return 0;
    }
    double bd = baseDamage;
    double mr = maxRange;
    double dis = distance;
    double doubleDamage = bd * ((mr - dis + 10) / mr);
    currentAmmo = currentAmmo - 1;
    return Double.valueOf(Math.floor(doubleDamage)).intValue();
  }

  @Override
  public String toString() {
    return "MockWeapon";
  }

}
