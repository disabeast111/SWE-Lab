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
    if (base.getNumAttachments() >= 2) {
      throw new AttachmentException("Can not have more than 2 attachments.");
    }
  }
  

  public int fire(int distance) throws WeaponException {
    currAmmo = base.getCurrentAmmo();
    damage = base.fire(distance);
    
    damage *= 1 + (currAmmo / maxAmmo);
    
    return Double.valueOf(Math.floor(damage)).intValue();
  }

  
  public String toString() {
    return base.toString() + " +PowerBooster";
  }
}
