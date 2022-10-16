package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class Scope extends Attachment {
  
  public double damage = 0;
  public double maxRange = 0;
  public double targetDistance = 0;

  
  public Scope(Weapon baseWeapon) throws AttachmentException {
    //increases max range of a weapon by 10 *done*
    //also new damage = damage*(1+((new max range-distance)/new max range))
    //if old range < target distance <= new range + 10, then damage is 5 + 
    //base damage at max range 
    base = baseWeapon;
    damage = baseWeapon.getBaseDamage();
    maxRange = getMaxRange();
    
  }
  
  public int fire(int distance) throws WeaponException {
    if(base.getMaxRange() < distance && distance <= base.getMaxRange() + 10) {
      return base.fire(base.getMaxRange()) + 5;
    } else if(distance <= base.getMaxRange()) {
        int damageInt = 0;
        targetDistance = distance;
        damage = base.fire(distance);
        damage *= 1 + ((maxRange - targetDistance) / maxRange);
        damageInt = Double.valueOf(Math.floor(damage)).intValue();
//        damage = base.fire(distance);
        return damageInt;
    } else {
      return 0;
    }
  }
 
  public int getMaxRange() {
    return base.getMaxRange() + 10;
  }
  
  public String toString() {
    
    return base.toString() + " +Scope";
  }

}
