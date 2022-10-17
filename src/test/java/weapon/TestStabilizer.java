package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class TestStabilizer {

  @Test
  public void testPlasmaStabil() throws AttachmentException, WeaponException {
    Weapon pc = new PlasmaCannon();
    Stabilizer p = new Stabilizer(pc);
    assertEquals(50, p.getBaseDamage());
    assertEquals(40, p.getMaxRange());
    assertEquals(62, p.fire(0));
    assertEquals(46, p.fire(5));
    assertEquals(31, p.fire(10));
    assertEquals(15, p.fire(15));
    assertEquals(62, p.fire(20));

  }
  
  @Test
  public void testPlasmaDoubleStabil() throws WeaponException, AttachmentException {
    Weapon pc = new PlasmaCannon();
    Stabilizer p = new Stabilizer(new Stabilizer(pc));
    assertEquals(50, p.getBaseDamage());
    assertEquals(40, p.getMaxRange());
    assertEquals(77, p.fire(0));
    assertEquals(57, p.fire(5));
    assertEquals(38, p.fire(10));
    assertEquals(18, p.fire(15));
    assertEquals(77, p.fire(20));
  }
  
  @Test
  public void testStabilScopePistol() throws WeaponException, AttachmentException {
    Weapon p = new Pistol();
    Scope sc = new Scope(p);
    Stabilizer s = new Stabilizer(sc);
    
    assertEquals(10, s.getBaseDamage());
    assertEquals(60, s.getMaxRange());
    assertEquals(30, s.fire(0));
    assertEquals(26, s.fire(5));
    assertEquals(22, s.fire(10));
    assertEquals(18, s.fire(15));
    assertEquals(16, s.fire(20));
    assertEquals(13, s.fire(25));
    assertEquals(11, s.fire(30));
    assertEquals(8, s.fire(35));
    assertEquals(6, s.fire(36));
    assertEquals(6, s.fire(37));
    assertEquals(6, s.fire(38));
    assertEquals(6, s.fire(39));
    assertEquals(6, s.fire(40));
    assertEquals(3, s.fire(41));
    assertEquals(3, s.fire(42));
    
    assertEquals(3, s.fire(45));
    assertEquals(8, s.fire(55));
    assertEquals(8, s.fire(60));
    assertEquals(0, s.fire(61));
   
  }
  @Test
  public void testScopeStabilPistol() throws WeaponException, AttachmentException {
    Weapon s = new Scope(new Stabilizer(new Pistol()));
    
    assertEquals(10, s.getBaseDamage());
    assertEquals(60, s.getMaxRange());
    assertEquals(30, s.fire(0));
    assertEquals(26, s.fire(5));
    assertEquals(22, s.fire(10));
    assertEquals(18, s.fire(15));
    assertEquals(16, s.fire(20));
    assertEquals(13, s.fire(25));
    assertEquals(11, s.fire(30));
    assertEquals(8, s.fire(35));
    assertEquals(6, s.fire(36));
    assertEquals(6, s.fire(37));
    assertEquals(6, s.fire(38));
    assertEquals(6, s.fire(39));
    assertEquals(6, s.fire(40));
    assertEquals(3, s.fire(41));
    assertEquals(3, s.fire(42));
    
    assertEquals(3, s.fire(45));
    assertEquals(8, s.fire(55));
    assertEquals(8, s.fire(60));
    assertEquals(0, s.fire(61));
    //FAILING INST
  }
  
  @Test
  public void testChainGunPowBStabil() throws AttachmentException, WeaponException {
    Weapon c = new ChainGun(); 
    Weapon s = new PowerBooster(new Stabilizer(c));
    assertEquals(0, s.fire(0));
    assertEquals(1, s.fire(5));
    assertEquals(15, s.fire(30));
    assertEquals(25, s.fire(45));
    assertEquals(28, s.fire(50));
    assertEquals(0, s.fire(61));
   }
  

}