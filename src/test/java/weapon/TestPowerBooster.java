package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class TestPowerBooster {

  @Test
  public void testChainGunPowB() throws AttachmentException, WeaponException {
    Weapon chainGunTest = new Weapon(); // Eventually after the individual weapons are implemented this should be of type ChainGun
    PowerBooster powBooster = new PowerBooster(chainGunTest);
    assertEquals(10, powBooster.fire(10));
  }
  
  @Test
  public void testChainGunDoublePB() {
    
  }
  
  @Test
  public void testPistolScopePowB() {
    
  }
    
  @Test
  public void testPlasmaStabilPB() {
    
  }
  

}
