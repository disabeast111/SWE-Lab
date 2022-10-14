package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class PowerBooster extends Attachment {
  
  public PowerBooster(Weapon baseWeapon) throws AttachmentException {
    int base = baseWeapon.getBaseDamage();
  }
  
  public int fire(int distance) throws WeaponException {
    
    return 0;
  }
  
  public String toString() {
    
    return "you forgot to replace this filler text!";
  }
}
