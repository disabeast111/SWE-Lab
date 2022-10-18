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
    if (base.getNumAttachments() >= 2) {
      throw new AttachmentException("Can not have more than 2 attachments.");
    }
  }
  
  public int fire(int distance) throws WeaponException {
    if(base.getMaxRange() < distance && distance <= maxRange) {
      return base.fire(base.getMaxRange()) + 5;
    } else if(distance <= base.getMaxRange()) {
        targetDistance = distance;
        damage = base.fire(distance);
        
        damage *= 1 + ((maxRange - targetDistance) / maxRange);
        
        return Double.valueOf(Math.floor(damage)).intValue();
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
