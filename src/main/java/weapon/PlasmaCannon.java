package weapon;

import exceptions.WeaponException;

public class PlasmaCannon extends GenericWeapon {

  public PlasmaCannon() {
    baseDamage = 50;
    maxRange = 40;
    rateOfFire = 1;
    maxAmmo = 4;
    currentAmmo = 4;

  }

  @Override
  public void updateTime(int time) {
    // TODO Auto-generated method stub

  }

  @Override
  public int fire(int distance) throws WeaponException {
    if(distance < 0) {
      throw new WeaponException("Cannot be a negative distance");
    }
    if(currentAmmo == 0) {
      return 0;
    }
    if (distance > maxRange) {
      currentAmmo = currentAmmo - 1;
      return 0;
    }
    double bd = baseDamage;
    double ca = currentAmmo;
    double ma = maxAmmo;
    double doubleDamage = 0;
    int damageInt = 0;
    doubleDamage = bd * (ca / ma);
    damageInt = Double.valueOf(Math.floor(doubleDamage)).intValue();
    currentAmmo = currentAmmo - 1;
    return damageInt;
  }

  @Override
  public String toString() {
    return "PlasmaCannon";
  }

}
