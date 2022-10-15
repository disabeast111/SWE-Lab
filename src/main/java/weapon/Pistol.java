package weapon;

import exceptions.WeaponException;

public class Pistol extends GenericWeapon {

  public Pistol() {
    baseDamage = 10;
    maxRange = 50;
    rateOfFire = 2;
    maxAmmo = 10;
    
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


