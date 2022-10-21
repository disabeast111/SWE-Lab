package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

/**
 * @author Spencer H
 */
public class TestStabilizer {

  @Test
  public void testPlasmaStabil() throws AttachmentException, WeaponException {
    Weapon pc = new PlasmaCannon();
    Stabilizer p = new Stabilizer(pc);
    //Testing Range
    assertEquals(40, p.getMaxRange());
    assertEquals(62, p.fire(p.getMaxRange()));
    p.updateTime(0);
    assertEquals(0, p.fire(p.getMaxRange() + 1));
    p.updateTime(0);
    //Testing Damage
    assertEquals(31, p.fire(0));
    p.updateTime(0);
    assertEquals(15, p.fire(30));
  }
  
  @Test
  public void testPlasmaDoubleStabil() throws WeaponException, AttachmentException {
    Weapon pc = new PlasmaCannon();
    Stabilizer p = new Stabilizer(new Stabilizer(pc));
    //Testing Range
    assertEquals(40, p.getMaxRange());
    assertEquals(77, p.fire(p.getMaxRange()));
    p.updateTime(0);
    assertEquals(0, p.fire(p.getMaxRange() + 1));
    p.updateTime(0);
    //Testing Damage
    assertEquals(38, p.fire(0));
    p.updateTime(0);
    assertEquals(18, p.fire(30));
  }
  
  @Test
  public void testStabilScopePistol() throws WeaponException, AttachmentException {
    Weapon p = new Scope(new Stabilizer(new Pistol()));
    assertEquals(10, p.getBaseDamage());
    assertEquals(60, p.getMaxRange());
    //Testing Range
    assertEquals(60, p.getMaxRange());
    assertEquals(7, p.fire(p.getMaxRange()));
    assertEquals(0, p.fire(p.getMaxRange() + 1));
    p.updateTime(0);
    //Testing Damage
    assertEquals(30, p.fire(0));
    assertEquals(10, p.fire(30));
  }
  
  @Test
  public void testChainGunPowBStabil() throws AttachmentException, WeaponException {
    Weapon c = new ChainGun(); 
    Weapon pb = new PowerBooster(new Stabilizer(c));
    for(int i = 0; i < 5; i++) {
      pb.fire(i);
      pb.fire(i);
      pb.fire(i);
      pb.fire(i);
      pb.updateTime(0);
    }
    //Testing Range
    assertEquals(60, pb.getMaxRange());
    assertEquals(27, pb.fire(pb.getMaxRange()));
    assertEquals(0, pb.fire(pb.getMaxRange() + 1));
    //Testing Damage
    assertEquals(0, pb.fire(0));
    assertEquals(11, pb.fire(30));
   }
}