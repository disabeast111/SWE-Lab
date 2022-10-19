package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

/**
 * @author Spencer H
 */
public class TestScope {

  @Test
  public void testPistolScope() throws AttachmentException, WeaponException {
    Weapon pistolTest = new Pistol(); 
    Weapon s = new Scope(pistolTest);
    assertEquals(10, s.getBaseDamage());
    
    //Testing Range
    assertEquals(60, s.getMaxRange());
    assertEquals(7, s.fire(s.getMaxRange()));
    assertEquals(0, s.fire(s.getMaxRange() + 1));
    s.updateTime(0);
    //Testing Damage
    assertEquals(24, s.fire(0));
    assertEquals(21, s.fire(5));
  }
  
  @Test
  public void testPistolDoubleScope() throws WeaponException, AttachmentException {
    Weapon pistolTest = new Pistol(); 
    Scope s = new Scope(new Scope(pistolTest));
    
    //Testing Range
    assertEquals(70, s.getMaxRange());
    assertEquals(12, s.fire(s.getMaxRange()));
    assertEquals(0, s.fire(s.getMaxRange() + 1));
    s.updateTime(0);
    //Testing Damage
    assertEquals(48, s.fire(0));
    assertEquals(40, s.fire(5));
  }
  
  @Test
  public void testChainGunPowBScope() throws AttachmentException, WeaponException {
    Weapon cg = new ChainGun();
    PowerBooster cgpb = new PowerBooster(cg);
    Scope s = new Scope (cgpb);
    
    for(int i = 0; i < 5; i++) {
      s.fire(i);
      s.fire(i);
      s.fire(i);
      s.fire(i);
      s.updateTime(0);
    }
    
    //Testing Range
    assertEquals(70, s.getMaxRange());
    assertEquals(27, s.fire(s.getMaxRange()));
    assertEquals(0, s.fire(s.getMaxRange() + 1));
    //Testing Damage
    assertEquals(0, s.fire(0));
    assertEquals(14, s.fire(30));
  }
  
  @Test
  public void testPlasmaStabilScope() throws AttachmentException, WeaponException {
    Weapon plasma = new PlasmaCannon();
    Stabilizer plst = new Stabilizer(plasma);
    Scope p = new Scope (plst);
    
    //Testing Range
    assertEquals(50, p.getMaxRange());
    assertEquals(67, p.fire(p.getMaxRange()));
    p.updateTime(0);
    assertEquals(0, p.fire(p.getMaxRange() + 1));
    p.updateTime(0);
    //Testing Damage
    assertEquals(62, p.fire(0));
    p.updateTime(0);
    assertEquals(21, p.fire(30));
  }
}

