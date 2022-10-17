package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class Stabilizer extends Attachment {

  public double damage = 0;
  
  public Stabilizer(Weapon baseWeapon) throws AttachmentException {
    
    //auto reloads if ammo is at 0 after firing. Also increases damage by 25%
    base = baseWeapon;
    damage = baseWeapon.getBaseDamage();
  }
  
  public int fire(int distance) throws WeaponException {
    
    damage = Math.floor(base.fire(distance) * 1.25);
    
    if(base.getCurrentAmmo() == 0) {
      base.reload();
    }
    int damageInt = 0;
    damageInt = Double.valueOf(Math.floor(damage)).intValue();
    return damageInt;
  }
  
  public String toString() {
    
    return base.toString() + " +Stabilizer";
  }
  
}
