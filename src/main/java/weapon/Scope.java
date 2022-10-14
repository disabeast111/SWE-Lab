package weapon;

import exceptions.AttachmentException;

public class Scope extends Attachment {
  
  public Scope(Weapon baseWeapon) throws AttachmentException {
    //increases max range of a weapon by 10
    //also new damage = damage*(1+((new max range-distance)/new max range))
    //if old range < target distance <= new range + 10, then damage is 5 + 
    //base damage at max range 
  }
  
  public int fire(int distance) {
    
    return 0;
  }
  
  public int getMaxRange() {
    
    return 0;
  }
  
  public String toString() {
    
    return "filler text";
  }

}
