package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class PowerBooster extends Attachment {
  
  public PowerBooster(Weapon baseWeapon) throws AttachmentException {
    //makes damage = damage * (1+ (current ammo/max ammo)
     
  }
  
  public int fire(int distance) {
    
    return 0;
  }
  
  public String toString() {
    
    return "you forgot to replace this filler text!";
  }
}
