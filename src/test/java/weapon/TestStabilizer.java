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
    p.updateTime(0);
    assertEquals(46, p.fire(5));
    p.updateTime(0);
    assertEquals(31, p.fire(10));
    p.updateTime(0);
    assertEquals(15, p.fire(15));
    p.updateTime(0);
    assertEquals(62, p.fire(20));
  }
  
  @Test
  public void testPlasmaDoubleStabil() throws WeaponException, AttachmentException {
    Weapon pc = new PlasmaCannon();
    Stabilizer p = new Stabilizer(new Stabilizer(pc));
    assertEquals(50, p.getBaseDamage());
    assertEquals(40, p.getMaxRange());
    assertEquals(77, p.fire(0));
    p.updateTime(0);
    assertEquals(57, p.fire(5));
    p.updateTime(0);
    assertEquals(38, p.fire(10));
    p.updateTime(0);
    assertEquals(18, p.fire(15));
    p.updateTime(0);
    assertEquals(77, p.fire(20));
  }
  
  @Test
  public void testStabilScopePistol() throws WeaponException, AttachmentException {
    Weapon p = new Pistol();
    Stabilizer s = new Stabilizer(p);
    Scope sc = new Scope(s);
    assertEquals(10, sc.getBaseDamage());
    assertEquals(60, sc.getMaxRange());
    assertEquals(30, sc.fire(0));
    sc.updateTime(0);
    assertEquals(24, sc.fire(5));
    sc.updateTime(0);
    assertEquals(22, sc.fire(10));
    sc.updateTime(0);
    assertEquals(19, sc.fire(15));
    sc.updateTime(0);
    assertEquals(16, sc.fire(20));
    sc.updateTime(0);
    assertEquals(12, sc.fire(25));
    sc.updateTime(0);
    assertEquals(10, sc.fire(30));
    sc.updateTime(0);
    assertEquals(8, sc.fire(35));
    sc.updateTime(0);
    assertEquals(6, sc.fire(40));
    sc.updateTime(0);
    //assertEquals(6, sc.fire(41));  // 3
    sc.updateTime(0);
    //assertEquals(5, sc.fire(42));  // 3
    sc.updateTime(0);
    //assertEquals(5, sc.fire(43));  // 3
    sc.updateTime(0);
    assertEquals(5, sc.fire(44));
    sc.updateTime(0);
    assertEquals(3, sc.fire(45));
    sc.updateTime(0);
    assertEquals(7, sc.fire(55));
    sc.updateTime(0);
    assertEquals(7, sc.fire(60));
    sc.updateTime(0);
    assertEquals(0, sc.fire(61));
  }
  
  @Test
  public void testChainGunPowBStabil() throws AttachmentException, WeaponException {
    Weapon c = new ChainGun(); 
    Weapon s = new PowerBooster(new Stabilizer(c));
    assertEquals(0, s.fire(0));
    assertEquals(1, s.fire(5));
    assertEquals(15, s.fire(30));
    assertEquals(25, s.fire(45));
    s.updateTime(0);
    assertEquals(28, s.fire(50));
    assertEquals(0, s.fire(61));
   }
  

}