package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class Scope extends Attachment {
  
  public Scope(Weapon baseWeapon) throws AttachmentException {
    
  }
  
  public int fire(int distance) throws WeaponException {
    
    return 0;
  }
  
  public int getMaxRange() {
    
    return 0;
  }
  
  public String toString() {
    
    return "filler text";
  }

}
