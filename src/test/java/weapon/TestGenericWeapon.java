package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.WeaponException;

public class TestGenericWeapon {
  
  @Test
  public void fireUsesAmmo() throws WeaponException {
    MockWeapon weapon1 = new MockWeapon();
    assertEquals(10, weapon1.getCurrentAmmo());
    weapon1.fire(10);
    assertEquals(9, weapon1.getCurrentAmmo());
  }
  
  @Test
  public void testRateOfFire1() throws WeaponException {
    MockWeapon weapon1 = new MockWeapon();
    assertEquals(10, weapon1.getCurrentAmmo());
    assertEquals(10,weapon1.fire(9));
    assertEquals(10,weapon1.fire(9));
    assertEquals(10,weapon1.fire(9));
    assertEquals(10,weapon1.fire(9));
    assertEquals(10,weapon1.fire(9));
    assertEquals(0,weapon1.fire(9));
  }
  
  @Test
  public void testRateOfFire2() throws WeaponException {
    MockWeapon weapon1 = new MockWeapon();
    assertEquals(10, weapon1.getCurrentAmmo());
    assertEquals(10,weapon1.fire(9));
    assertEquals(10,weapon1.fire(9));
    assertEquals(10,weapon1.fire(9));
    assertEquals(10,weapon1.fire(9));
    assertEquals(10,weapon1.fire(9));
    assertEquals(0,weapon1.fire(9));
    weapon1.updateTime(0);
    assertEquals(10,weapon1.fire(9));
  }
  
  @Test
  public void testReload() throws WeaponException {
    MockWeapon weapon1 = new MockWeapon();
    assertEquals(10, weapon1.getCurrentAmmo());
    weapon1.fire(10);
    weapon1.fire(10);
    weapon1.fire(10);
    assertEquals(7, weapon1.getCurrentAmmo());
    weapon1.reload();
    assertEquals(10,weapon1.getCurrentAmmo());
  }
  
  @Test
  public void testNoDamageNoAmmo() throws WeaponException {
    MockWeapon weapon1 = new MockWeapon();
    assertEquals(10, weapon1.getCurrentAmmo());
    while(weapon1.getCurrentAmmo()>0) { 
      weapon1.fire(100);
      weapon1.updateTime(0);
    }
    assertEquals(0, weapon1.getCurrentAmmo());
    assertEquals(0,weapon1.fire(5));
  }
  
  @Test
  public void testNoDamageOutRange() throws WeaponException {
    MockWeapon weapon1 = new MockWeapon();
    assertEquals(0,weapon1.fire(15));
  }
  
  @Test
  public void testFire() throws WeaponException {
    MockWeapon weapon1 = new MockWeapon();
    assertEquals(10,weapon1.fire(5));
  }
}
