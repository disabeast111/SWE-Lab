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
  public void testPistolScopeStabil() throws WeaponException, AttachmentException {
    Weapon p = new Pistol();
    Scope s = new Scope(p);
    Stabilizer sc = new Stabilizer(s);
    assertEquals(10, sc.getBaseDamage());
    assertEquals(60, sc.getMaxRange());
    assertEquals(30, sc.fire(0));
    p.updateTime(0);
    assertEquals(26, sc.fire(5));
    sc.updateTime(0);
    assertEquals(22, sc.fire(10));
    p.updateTime(0);
    assertEquals(18, sc.fire(15));
    sc.updateTime(0);
    assertEquals(16, sc.fire(20));
    p.updateTime(0);
    assertEquals(13, sc.fire(25));
    sc.updateTime(0);
    assertEquals(11, sc.fire(30));
    p.updateTime(0);
    assertEquals(8, sc.fire(35));
    sc.updateTime(0);
    assertEquals(6, sc.fire(40));
    p.updateTime(0);
    assertEquals(3, sc.fire(45));
    sc.updateTime(0);
    assertEquals(8, sc.fire(55));
    p.updateTime(0);
    assertEquals(8, sc.fire(60));
    sc.updateTime(0);
    assertEquals(0, sc.fire(61));
  }
  
  @Test
  public void testChainGunPowBStabil() {
    
  }
  

}