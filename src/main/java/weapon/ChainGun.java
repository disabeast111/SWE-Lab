package weapon;

import exceptions.WeaponException;

public class ChainGun extends GenericWeapon {

  public ChainGun() {
    baseDamage = 15;
    maxRange = 60;
    rateOfFire = 4;
    maxAmmo = 40;
    currentAmmo = 40;
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
    double doubleDamage = 0;
    doubleDamage = bd * (dis / mr);
    return Double.valueOf(Math.floor(doubleDamage)).intValue();
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "ChainGun";
  }

}
