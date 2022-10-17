package weapon;

import exceptions.WeaponException;

public class Pistol extends GenericWeapon {

  public Pistol() throws WeaponException {
    baseDamage = 10;
    maxRange = 50;
    rateOfFire = 2;
    maxAmmo = 10;
    currentAmmo = 10;
    shotsLeft = rateOfFire;
  }

  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Cannot be a negative distance");
    }
    if (currentAmmo == 0) {
      return 0;
    }
    currentAmmo -= 1;
    shotsLeft -= 1;
    if (distance > maxRange) {
      return 0;
    }
    double bd = baseDamage;
    double mr = maxRange;
    double dis = distance;
    double doubleDamage = bd * ((mr - dis + 10) / mr);
    return Double.valueOf(Math.floor(doubleDamage)).intValue();
  }

  @Override
  public String toString() {
    return "Pistol";
  }

}
