package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class PowerBooster extends Attachment {
  
  private double damage = 0;
  private int damageInt = 0;
  
  public PowerBooster(Weapon baseWeapon) throws AttachmentException {
    //makes damage = damage * (1+ (current ammo/max ammo)

    base = baseWeapon;
    damage = baseWeapon.getBaseDamage();

  }
  

  public int fire(int distance) throws WeaponException {
    int damageInt = 0;
    damage = base.fire(distance) * (1 + (base.getCurrentAmmo()
        / base.getMaxAmmo()));
    damageInt = Double.valueOf(Math.floor(damage)).intValue();
    return damageInt;
  }

  
  public String toString() {
    return base.toString() + " +PowerBooster";
  }
}
