package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class PowerBooster extends Attachment {
  
  private double damage = 0;
  private double currAmmo = 0;
  private double maxAmmo = 0;
  
  public PowerBooster(Weapon baseWeapon) throws AttachmentException {
    //makes damage = damage * (1+ (current ammo/max ammo)

    base = baseWeapon;
    damage = baseWeapon.getBaseDamage();
    maxAmmo = baseWeapon.getMaxAmmo();
  }
  

  public int fire(int distance) throws WeaponException {
    int damageInt = 0;
   
    currAmmo = base.getCurrentAmmo();
    damage = base.fire(distance);
    
    damage *= 1 + (currAmmo / maxAmmo);
    damageInt = Double.valueOf(Math.floor(damage)).intValue();
    return damageInt;
  }

  
  public String toString() {
    return base.toString() + " +PowerBooster";
  }
}
