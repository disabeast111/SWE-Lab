package weapon;

import exceptions.WeaponException;

public abstract class Attachment implements Weapon {
  protected Weapon base;
  
  public Attachment() {
    
  }
  
  public abstract int fire(int distance) throws WeaponException;
  
  public int getBaseDamage() {
    
    return 0;
  }
  
  public int getCurrentAmmo() {
    
    return 0;
  }
  
  public int getMaxAmmo() {
    
    return 0;
  }
  
  public int getMaxRange() {
    
    return 0;
  }
  
  public int getNumAttachments() {
    
    return 0;
  }
  
  public int getRateOfFire() {
    
    return 0;
  }
  
  public int getShotsLeft() {
    
    return 0;
  }
  
  public void reload() {
    
  }
  
  public void updateTime(int Time) {
    
  }
  
}
