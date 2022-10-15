package weapon;

import exceptions.WeaponException;

public class ChainGun extends GenericWeapon {

  public ChainGun() {
    baseDamage = 15;
    maxRange = 60;
    rateOfFire = 4;
    maxAmmo = 40;
  }

  @Override
  public void updateTime(int time) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int fire(int distance) throws WeaponException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return null;
  }
  
  

}


