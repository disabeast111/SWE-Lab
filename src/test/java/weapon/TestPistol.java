package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.WeaponException;


public class TestPistol {
  @Test
  public void testPistolInitialization() throws WeaponException {
    Pistol p = new Pistol();
    assertEquals(10, p.getBaseDamage());
    assertEquals(50, p.getMaxRange());
    assertEquals(2, p.getRateOfFire());
    assertEquals(10, p.getMaxAmmo());
  }
  
  @Test
  public void testPistolFire() throws WeaponException {
    Pistol p = new Pistol();
    assertEquals(10, p.getCurrentAmmo());
    assertEquals(7, p.fire(25));
    assertEquals(9, p.getCurrentAmmo());
    assertEquals(10, p.fire(10));
  }
  
  @Test
  public void testPistolZeroDamage() throws WeaponException {
    Pistol p = new Pistol();
    assertEquals(0, p.fire(51));
    p.fire(25);
    p.updateTime(0);
    p.fire(25);
    p.fire(25);
    p.updateTime(0);
    p.fire(25);
    p.fire(25);
    p.updateTime(0);
    p.fire(25);
    p.fire(25);
    p.updateTime(0);
    p.fire(25);
    p.fire(25);
    p.updateTime(0);
    assertEquals(0, p.fire(25));
  }
  
  @Test
  public void testPistolReload() throws WeaponException {
    Pistol p = new Pistol();
    p.fire(25);
    p.fire(25);
    p.updateTime(0);
    p.fire(25);
    p.fire(25);
    p.updateTime(0);
    p.fire(25);
    p.fire(25);
    p.updateTime(0);
    p.fire(25);
    p.fire(25);
    p.updateTime(0);
    p.fire(25);
    p.fire(25);
    assertEquals(0, p.getCurrentAmmo());
    p.reload();
    assertEquals(10, p.getCurrentAmmo());
  }
  
  @Test
  public void testRateException() throws WeaponException {
    boolean caught = false;
    try {
    Pistol p = new Pistol();
    p.fire(-1);
    }
    catch (WeaponException e) {
      caught = true;
    }
    assertTrue(caught);
  }

}
