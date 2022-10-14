package weapon;

import exceptions.AttachmentException;

public class Stabilizer extends Attachment {

  public Stabilizer(Weapon baseWeapon) throws AttachmentException {
    
    //auto reloads if ammo is at 0 after firing. Also increases damage by 25%
  }
  
  public int fire(int distance) {
    
    return 0;
  }
  
  public String toString() {
    
    return "filler text";
  }
  
}
