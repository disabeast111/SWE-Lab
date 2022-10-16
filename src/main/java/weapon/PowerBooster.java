package weapon;

import exceptions.AttachmentException;

public class PowerBooster extends Attachment {
  
  public double damage = 0;
  
  public PowerBooster(Weapon baseWeapon) throws AttachmentException {
    //makes damage = damage * (1+ (current ammo/max ammo)
    if(baseWeapon.getNumAttachments() <= 2) {
      base = baseWeapon;
      damage = baseWeapon.getBaseDamage() * (baseWeapon.getCurrentAmmo()
          / baseWeapon.getMaxAmmo());
    } else {
      throw new AttachmentException("Already 2 Attachments!");
    }
  }
  
  public int fire(int distance) {
    int damageInt = 0;
    damageInt = Double.valueOf(Math.floor(damage)).intValue();
    return damageInt;
  }
  
  public String toString() {
    
    return base.toString() + " +PowerBooster";
  }
}
