package weapon;

import exceptions.WeaponException;

public class Pistol extends GenericWeapon {

  public Pistol() throws WeaponException {
    baseDamage = 10;
    maxRange = 50;
    rateOfFire = 2;
    maxAmmo = 10;
    currentAmmo = 10;
    
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
    doubleDamage = bd*((mr-dis+10)/mr);
    damageInt = Double.valueOf(Math.floor(doubleDamage)).intValue();
    currentAmmo = currentAmmo - 1;
    
    return damageInt;
  }

  @Override
  public String toString() {
    return "Pistol";
  }
  
}


