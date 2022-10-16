package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.WeaponException;

public class TestChainGun {

  @Test
  public void testChainGunInitialization() throws WeaponException {
    Pistol p = new Pistol();
    assertEquals(10, p.getBaseDamage());
    assertEquals(50, p.getMaxRange());
    assertEquals(2, p.getRateOfFire());
    assertEquals(10, p.getMaxAmmo());
  }
  
  @Test
  public void testChainGunFire() throws WeaponException {
    Pistol p = new Pistol();
    assertEquals(10, p.getCurrentAmmo());
    assertEquals(7, p.fire(25));
    assertEquals(9, p.getCurrentAmmo());
  }
  
  @Test
  public void testChainGunZeroDamage() throws WeaponException {
    Pistol p = new Pistol();
    assertEquals(0, p.fire(51));
    p.fire(25);
    p.fire(25);
    p.fire(25);
    p.fire(25);
    p.fire(25);
    p.fire(25);
    p.fire(25);
    p.fire(25);
    p.fire(25);
    assertEquals(0, p.fire(25));
  }
  
  @Test
  public void testChainGunReload() throws WeaponException {
    Pistol p = new Pistol();
    p.fire(25);
    p.fire(25);
    p.fire(25);
    p.fire(25);
    p.fire(25);
    p.fire(25);
    p.fire(25);
    p.fire(25);
    p.fire(25);
    p.fire(25);
    assertEquals(0, p.getCurrentAmmo());
    p.reload();
    assertEquals(10, p.getCurrentAmmo());
  }
}
