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
    s.updateTime(0);
    assertEquals(18, s.fire(10));
    assertEquals(15, s.fire(15));
    s.updateTime(0);
    assertEquals(13, s.fire(20));
    assertEquals(11, s.fire(25));
    s.updateTime(0);
    assertEquals(9, s.fire(30));
    assertEquals(7, s.fire(35));
    s.updateTime(0);
    assertEquals(5, s.fire(40));
    assertEquals(3, s.fire(45));
    s.updateTime(0);
    assertEquals(0, s.fire(50));
    assertEquals(5, s.fire(60));
    s.updateTime(0);
    assertEquals(0, s.fire(61));
  }
  
  @Test
  public void testPistolDoubleScope() throws WeaponException, AttachmentException {
    Weapon pistolTest = new Pistol(); 
    Scope s = new Scope(new Scope(pistolTest));
    assertEquals(48, s.fire(0));
    assertEquals(40, s.fire(5));
    s.updateTime(0);
    assertEquals(14, s.fire(30));
    assertEquals(4, s.fire(45));
    s.updateTime(0);
    assertEquals(2, s.fire(50));
    assertEquals(12, s.fire(61));
    s.updateTime(0);
    assertEquals(12, s.fire(70));
    assertEquals(0, s.fire(71));
    
  }
  
  @Test
  public void testChainGunPowBScope() {
//    assertEquals(x, s.fire(0));
//    assertEquals(x, s.fire(5));
//    assertEquals(x, s.fire(30));
//    assertEquals(x, s.fire(55));
//    assertEquals(x, s.fire(60));
//    assertEquals(0, s.fire(61));
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

