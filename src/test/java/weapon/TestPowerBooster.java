package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class TestPowerBooster {

  @Test
  public void testChainGunPowB() throws AttachmentException, WeaponException {
    Weapon chainGunTest = new ChainGun(); // Eventually after the individual weapons are implemented this should be of type ChainGun
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
  public void testPistolScopePowB() throws AttachmentException {
    Weapon pistolTest = new Pistol(); 
    Scope s = new Scope(pistolTest);
    assertEquals(15, s.getBaseDamage());
    for(int i = 0; i < 20; i++) {
      s.fire(i);
    }
    assertEquals(22, s.fire(0));
  }
    
  @Test
  public void testPlasmaStabilPB() {
    
  }
  

}
