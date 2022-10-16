package weapon;

import exceptions.WeaponException;

public class ChainGun extends GenericWeapon {

  public ChainGun() {
    baseDamage = 15;
    maxRange = 60;
    rateOfFire = 4;
    maxAmmo = 40;
    currentAmmo = 40;
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
    double mr = maxRange;
    double dis = distance;
    double doubleDamage = 0;
    int damageInt = 0;
    doubleDamage = bd*(dis/mr);
    damageInt = Double.valueOf(Math.floor(doubleDamage)).intValue();
    currentAmmo = currentAmmo - 1;
    return damageInt;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "ChainGun";
  }
  
  

}


