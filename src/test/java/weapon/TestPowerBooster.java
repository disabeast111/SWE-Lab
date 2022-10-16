package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class TestPowerBooster {

  @Test
  public void testChainGunPowB() throws AttachmentException, WeaponException {
    Weapon chainGunTest = new ChainGun(); 
    PowerBooster powBooster = new PowerBooster(chainGunTest);
    assertEquals(15, powBooster.getBaseDamage());
    for(int i = 0; i < 20; i++) {
      powBooster.fire(i);
    }
    assertEquals(22, powBooster.fire(0));
    
  }
  
  @Test
  public void testChainGunDoublePB() {
    
        
    
  }
  
  @Test
  public void testPistolScopePowB() throws AttachmentException, WeaponException {
    Weapon pistolTest = new Pistol(); 
    Scope s = new Scope(pistolTest);
    assertEquals(10, s.getBaseDamage());
    assertEquals(60, s.getMaxRange());
    assertEquals(20, s.fire(0));
    assertEquals(19, s.fire(5));
    assertEquals(15, s.fire(30));
    assertEquals(12, s.fire(45));
    assertEquals(15, s.fire(55));
    assertEquals(15, s.fire(60));
  }
    
  @Test
  public void testPlasmaStabilPB() {
    
  }
  

}
