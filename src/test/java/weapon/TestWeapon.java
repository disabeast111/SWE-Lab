package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.WeaponException;

public class TestWeapon {
  
  @Test
  public void testWeaponInitialization() throws WeaponException {
    Pistol p = new Pistol();
    assertEquals(10, p.getBaseDamage());
    assertEquals(50, p.getMaxRange());
    assertEquals(2, p.getRateOfFire());
    assertEquals(10, p.getMaxAmmo());
    
    ChainGun c = new ChainGun();
    assertEquals(15, c.getBaseDamage());
    assertEquals(60, c.getMaxRange());
    assertEquals(4, c.getRateOfFire());
    assertEquals(40, c.getMaxAmmo());
   
    PlasmaCannon pl = new PlasmaCannon();
    assertEquals(50, pl.getBaseDamage());
    assertEquals(40, pl.getMaxRange());
    assertEquals(1, pl.getRateOfFire());
    assertEquals(4, pl.getMaxAmmo());
    
  }
  
}

