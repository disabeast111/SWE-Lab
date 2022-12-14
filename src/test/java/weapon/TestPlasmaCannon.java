package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.WeaponException;

/**
 * @author Ethan J
 */
public class TestPlasmaCannon {
  
  @Test
  public void testPlasmaCannonInitialization() throws WeaponException {
    PlasmaCannon pc = new PlasmaCannon();
    assertEquals(50, pc.getBaseDamage());
    assertEquals(40, pc.getMaxRange());
    assertEquals(1, pc.getRateOfFire());
    assertEquals(4, pc.getMaxAmmo());
  }
  
  @Test
  public void testPlasmaCannonFire() throws WeaponException {
    PlasmaCannon pc = new PlasmaCannon();
    assertEquals(4, pc.getCurrentAmmo());
    assertEquals(50, pc.fire(25));
    pc.updateTime(0);
    assertEquals(3, pc.getCurrentAmmo());
    assertEquals(37, pc.fire(15));
    pc.updateTime(0);
    assertEquals(2, pc.getCurrentAmmo());
    assertEquals(25, pc.fire(20));
    pc.updateTime(0);
    assertEquals(1, pc.getCurrentAmmo());
  }
  
  @Test
  public void testPlasmaCannonZeroDamage() throws WeaponException {
    PlasmaCannon pc = new PlasmaCannon();
    assertEquals(0, pc.fire(41));
    pc.fire(25);
    pc.updateTime(0);
    pc.fire(25);
    pc.updateTime(0);
    pc.fire(25);
    pc.updateTime(0);
    pc.fire(25);
    pc.updateTime(0);
    assertEquals(0, pc.fire(25));
  }
  
  @Test
  public void testPlasmaCannonReload() throws WeaponException {
    PlasmaCannon pc = new PlasmaCannon();
    pc.fire(25);
    pc.updateTime(0);
    pc.fire(25);
    pc.updateTime(0);
    pc.fire(25);
    pc.updateTime(0);
    pc.fire(25);
    pc.updateTime(0);
    assertEquals(0, pc.getCurrentAmmo());
    pc.reload();
    assertEquals(4, pc.getCurrentAmmo());
  }
  
  @Test
  public void testRateException() throws WeaponException {
    boolean caught = false;
    try {
    PlasmaCannon pc = new PlasmaCannon();
    pc.fire(-1);
    }
    catch (WeaponException e) {
      caught = true;
    }
    assertTrue(caught);
  }

  
}
