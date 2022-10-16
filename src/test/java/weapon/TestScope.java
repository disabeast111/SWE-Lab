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
    assertEquals(20, s.fire(0));
    assertEquals(19, s.fire(5));
    assertEquals(17, s.fire(15));
    assertEquals(16, s.fire(20));
    assertEquals(15, s.fire(25));
    assertEquals(15, s.fire(30));
    assertEquals(14, s.fire(35));
    assertEquals(13, s.fire(40));
    assertEquals(12, s.fire(45));
    assertEquals(15, s.fire(55));
    assertEquals(15, s.fire(60));
    assertEquals(0, s.fire(61));
  }
  
  @Test
  public void testPistolDoubleScope() throws WeaponException, AttachmentException {
    Weapon pistolTest = new Pistol(); 
    Scope s = new Scope(new Scope(pistolTest));
    assertEquals(20, s.fire(0));
    assertEquals(19, s.fire(5));
    assertEquals(17, s.fire(15));
    assertEquals(17, s.fire(20));
    assertEquals(16, s.fire(25));
    assertEquals(15, s.fire(30));
    assertEquals(15, s.fire(35));
    assertEquals(14, s.fire(40));
    assertEquals(13, s.fire(45));
    assertEquals(15, s.fire(65));
    assertEquals(15, s.fire(70));
    assertEquals(0, s.fire(71));
  }
  
  @Test
  public void testChainGunPowBScope() {
//    assertEquals(x, s.fire(0));
//    assertEquals(x, s.fire(5));
//    assertEquals(x, s.fire(30));
//    assertEquals(x, s.fire(55));
//    assertEquals(x, s.fire(60));
  }
  
  @Test
  public void testPlasmaStabilScope() {
//    assertEquals(x, s.fire(0));
//    assertEquals(x, s.fire(5));
//    assertEquals(x, s.fire(30));
//    assertEquals(x, s.fire(55));
//    assertEquals(x, s.fire(60));
  }
  

}

