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
    // TODO Auto-generated method stub
    int damage = 0;
    if(currentAmmo == 0) {
      return damage;
    }
    if (distance > maxRange) {
      currentAmmo = currentAmmo - 1;
      return damage;
    }
    double bd = baseDamage;
    double ca = currentAmmo;
    double ma = maxAmmo;
    ;
    double doubleDamage = 0;
    doubleDamage = bd * (ca / ma);
    damage = Double.valueOf(Math.floor(doubleDamage)).intValue();
    currentAmmo = currentAmmo - 1;
    return damage;
  }

  @Override
  public String toString() {
    return "PlasmaCannon";
  }

}
