package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.WeaponException;

/**
 * @author Ethan J
 */
public class TestChainGun {

  @Test
  public void testChainGunInitialization() throws WeaponException {
    ChainGun c = new ChainGun();
    assertEquals(15, c.getBaseDamage());
    assertEquals(60, c.getMaxRange());
    assertEquals(4, c.getRateOfFire());
    assertEquals(40, c.getMaxAmmo());
  }
  
  @Test
  public void testChainGunFire() throws WeaponException {
    ChainGun c = new ChainGun();
    assertEquals(40, c.getCurrentAmmo());
    assertEquals(6, c.fire(25));
    assertEquals(39, c.getCurrentAmmo());
    assertEquals(15, c.fire(60));
    assertEquals(38, c.getCurrentAmmo());
  }
  
  @Test
  public void testChainGunZeroDamage() throws WeaponException {
    ChainGun c = new ChainGun();
    assertEquals(0, c.fire(61));
    while(c.currentAmmo != 0) {
    c.fire(25);
    c.updateTime(0);
    }
    assertEquals(0, c.fire(25));
  }
  
  @Test
  public void testChainGunReload() throws WeaponException {
    ChainGun c = new ChainGun();
    while(c.currentAmmo != 0) {
      c.fire(25);
      c.updateTime(0);
      }
    assertEquals(0, c.getCurrentAmmo());
    c.reload();
    assertEquals(40, c.getCurrentAmmo());
  }
  
  @Test
  public void testRateException() throws WeaponException {
    boolean caught = false;
    try {
    ChainGun c = new ChainGun();
    c.fire(-1);
    }
    catch (WeaponException e) {
      caught = true;
    }
    assertTrue(caught);
  }

  
}
