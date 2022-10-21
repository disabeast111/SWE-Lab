package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

/**
 * @author Spencer H
 */
public class TestPowerBooster {

  @Test
  public void testChainGunPowB() throws AttachmentException, WeaponException {
    Weapon chainGunTest = new ChainGun(); 
    PowerBooster pb = new PowerBooster(chainGunTest);
    assertEquals(15, pb.getBaseDamage());
    for(int i = 0; i < 5; i++) {
      pb.fire(i);
      pb.fire(i);
      pb.fire(i);
      pb.fire(i);
      pb.updateTime(0);
    }
    //Testing Range
    assertEquals(60, pb.getMaxRange());
    assertEquals(22, pb.fire(pb.getMaxRange()));
    assertEquals(0, pb.fire(pb.getMaxRange() + 1));
    //Testing Damage
    assertEquals(0, pb.fire(0));
    assertEquals(9, pb.fire(30));
  }
  
  @Test
  public void testChainGunDoublePB() throws AttachmentException, WeaponException {
    Weapon chainGunTest = new ChainGun(); 
    PowerBooster pb = new PowerBooster(new PowerBooster(chainGunTest));
    assertEquals(15, pb.getBaseDamage());
    for(int i = 0; i < 5; i++) {
      pb.fire(i);
      pb.fire(i);
      pb.fire(i);
      pb.fire(i);
      pb.updateTime(0);
    }
    //Testing Range
    assertEquals(60, pb.getMaxRange());
    assertEquals(33, pb.fire(pb.getMaxRange()));
    assertEquals(0, pb.fire(pb.getMaxRange() + 1));
    //Testing Damage
    assertEquals(0, pb.fire(0));
    assertEquals(12, pb.fire(30));
  }
  
  @Test
  public void testPistolScopePowB() throws AttachmentException, WeaponException {
    Weapon pistolTest = new Pistol(); 
    Weapon s = new Scope(pistolTest);
    Weapon pb = new PowerBooster(s);
    //Testing Range
    assertEquals(60, pb.getMaxRange());
    assertEquals(14, pb.fire(pb.getMaxRange()));
    assertEquals(0, pb.fire(pb.getMaxRange() + 1));
    pb.updateTime(0);
    //Testing Damage
    assertEquals(43, pb.fire(0));
    assertEquals(15, pb.fire(30));
  }
   
  @Test
  public void testPlasmaStabilPB() throws AttachmentException, WeaponException {
    Weapon plasmaTest = new PlasmaCannon(); 
    Weapon s = new Stabilizer(plasmaTest);
    Weapon pb = new PowerBooster(s);
    //Testing Range
    assertEquals(40, pb.getMaxRange());
    assertEquals(124, pb.fire(pb.getMaxRange()));
    pb.updateTime(0);
    assertEquals(0, pb.fire(pb.getMaxRange() + 1));
    pb.updateTime(0);
    //Testing Damage
    assertEquals(46, pb.fire(0));
    pb.updateTime(0);
    assertEquals(18, pb.fire(30));
  }
}
