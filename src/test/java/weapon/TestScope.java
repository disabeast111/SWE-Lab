package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class TestScope {

  @Test
  public void testPistolScope() throws AttachmentException, WeaponException {
    Weapon pistolTest = new Pistol(); 
    Weapon s = new Scope(pistolTest);
    assertEquals(10, s.getBaseDamage());
    assertEquals(60, s.getMaxRange());
    assertEquals(24, s.fire(0));
    assertEquals(21, s.fire(5));
    assertEquals(18, s.fire(10));
    assertEquals(15, s.fire(15));
    assertEquals(13, s.fire(20));
    assertEquals(11, s.fire(25));
    assertEquals(9, s.fire(30));
    assertEquals(7, s.fire(35));
    assertEquals(5, s.fire(40));
    assertEquals(3, s.fire(45));
    assertEquals(0, s.fire(50));
    assertEquals(5, s.fire(60));
    assertEquals(0, s.fire(61));
  }
  
  @Test
  public void testPistolDoubleScope() throws WeaponException, AttachmentException {
    Weapon pistolTest = new Pistol(); 
    Scope s = new Scope(new Scope(pistolTest));
    assertEquals(48, s.fire(0));
    assertEquals(40, s.fire(5));
    assertEquals(14, s.fire(30));
    assertEquals(4, s.fire(45));
    assertEquals(2, s.fire(50));
    assertEquals(12, s.fire(61));
    assertEquals(12, s.fire(70));
    assertEquals(0, s.fire(71));
    
  }
  
  @Test
  public void testChainGunPowBScope() throws AttachmentException, WeaponException {
    Weapon cg = new ChainGun();
    PowerBooster cgpb = new PowerBooster(cg);
    Scope cgpbsc = new Scope (cgpb);
    
    assertEquals(0, cgpbsc.fire(0));
    assertEquals(1, cgpbsc.fire(5));
    assertEquals(20, cgpbsc.fire(30));
    assertEquals(30, cgpbsc.fire(55));
    assertEquals(32, cgpbsc.fire(60));
    assertEquals(33, cgpbsc.fire(61));
    assertEquals(32, cgpbsc.fire(70));
    assertEquals(0, cgpbsc.fire(71));
  }
  
  @Test
  public void testPlasmaStabilScope() throws AttachmentException, WeaponException {
    Weapon plasma = new PlasmaCannon();
    Stabilizer plst = new Stabilizer(plasma);
    Scope plstsc = new Scope (plst);
    
    assertEquals(124, plstsc.fire(0));
    assertEquals(87, plstsc.fire(5));
    assertEquals(43, plstsc.fire(30));
    assertEquals(20, plstsc.fire(50));
    assertEquals(86, plstsc.fire(30));
    assertEquals(64, plstsc.fire(30));
    assertEquals(0, plstsc.fire(51));
  }
  

}

