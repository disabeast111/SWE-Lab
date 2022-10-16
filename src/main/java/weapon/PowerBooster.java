package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class PowerBooster extends Attachment {
  
  private double damage = 0;
  
  public PowerBooster(Weapon baseWeapon) throws AttachmentException {
    //makes damage = damage * (1+ (current ammo/max ammo)

    base = baseWeapon;
    damage = baseWeapon.getBaseDamage();
    if (base.getNumAttachments() >= 2) {
      throw new AttachmentException("Can not have more than 2 attachments.");
    }
  }
  

  public int fire(int distance) throws WeaponException {
    int damageInt = 0;
    damage = base.fire(distance) * (base.getCurrentAmmo()
        / base.getMaxAmmo());
    damageInt = Double.valueOf(Math.floor(damage)).intValue();
    return damageInt;
  }

  
  public String toString() {
    return base.toString() + " +PowerBooster";
  }
}
