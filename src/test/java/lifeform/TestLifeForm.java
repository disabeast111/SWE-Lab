package lifeform;

import static org.junit.Assert.*;
import org.junit.Test;

import environment.Environment;
import exceptions.RecoveryRateException;
import exceptions.WeaponException;
import weapon.Pistol;
import weapon.Weapon;

/**
 * @author David W
 */
public class TestLifeForm {

  @Test
  public void testStorePoints() {
    MockLifeForm entity = new MockLifeForm("Bob", 40);
    assertEquals(40, entity.getCurrentLifePoints());
  }

  @Test
  public void testStoreNames() {
    MockLifeForm entity = new MockLifeForm("Jim", 40);
    assertEquals("Jim", entity.getName());
  }

  @Test
  public void testTakeHit1() {
    MockLifeForm entity = new MockLifeForm("Jim", 40);
    entity.takeHit(5);
    assertEquals(35, entity.getCurrentLifePoints());
  }

  @Test
  public void testTakeHit2() {
    MockLifeForm entity = new MockLifeForm("Jim", 20);
    entity.takeHit(15);
    entity.takeHit(15);
    assertEquals(0, entity.getCurrentLifePoints());
  }

  @Test
  public void testGetStrength() {
    MockLifeForm entity = new MockLifeForm("Jim", 40);
    assertEquals(1, entity.getAttackStrength());
  }
  
  @Test
  public void testGetStrength2() {
    MockLifeForm entity = new MockLifeForm("Jim", 40, 10);
    assertEquals(10, entity.getAttackStrength());
  }

  @Test
  public void testAttack() throws RecoveryRateException, WeaponException{
  MockLifeForm entityJ = new MockLifeForm("Jim", 30, 10);
  MockLifeForm entityB = new MockLifeForm("Bob", 30, 10);
  assertEquals(10, entityB.getAttackStrength());
  entityB.attack(entityJ,0);
  assertEquals(20, entityJ.getCurrentLifePoints());
  }
  
  @Test
  public void testAttackDead() throws RecoveryRateException, WeaponException{
  MockLifeForm entityJ = new MockLifeForm("Jim", 30, 10);
  MockLifeForm entityB = new MockLifeForm("Bob", 0, 10);
  assertEquals(10, entityB.getAttackStrength());
  entityB.attack(entityJ,0);
  assertEquals(30, entityJ.getCurrentLifePoints());
  }
  
  
  // WEAPON SECTION
  
  
  @Test
  public void testPickUpWeapon() throws WeaponException {
    MockLifeForm entity = new MockLifeForm("Jim", 40, 10);
    Weapon pistol1 = new Pistol();
    assertTrue(entity.pickUpWeapon(pistol1));
    assertTrue(entity.hasWeapon());
  }
  
  @Test
  public void testOnlyOneWeapon() throws WeaponException {
    MockLifeForm entity = new MockLifeForm("Jim", 40, 10);
    Weapon pistol1 = new Pistol();
    Weapon pistol2 = new Pistol();
    assertTrue(entity.pickUpWeapon(pistol1));
    assertTrue(entity.hasWeapon());
    assertFalse(entity.pickUpWeapon(pistol2));
  }
  
  @Test
  public void testCanDropWeapon() throws WeaponException {
    MockLifeForm entity = new MockLifeForm("Jim", 40, 10);
    Weapon pistol1 = new Pistol();
    assertTrue(entity.pickUpWeapon(pistol1));
    assertTrue(entity.hasWeapon());
    assertEquals(pistol1, entity.dropWeapon());
    assertFalse(entity.hasWeapon());
  }
  
  @Test
  public void testUseWeaponWAmmoInRange() throws WeaponException {
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    MockLifeForm entity2 = new MockLifeForm("Bob", 40, 1);
    Weapon pistol = new Pistol();
    assertTrue(entity1.pickUpWeapon(pistol));
    entity1.attack(entity2, 10);
    assertEquals(30,entity2.getCurrentLifePoints());
  }
  
  @Test
  public void testUseMeleeInRange() throws WeaponException {
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    MockLifeForm entity2 = new MockLifeForm("Bob", 40, 1);
    Weapon pistol = new Pistol();
    assertTrue(entity1.pickUpWeapon(pistol));
    while(pistol.getCurrentAmmo()>0) {
      pistol.fire(100);
      pistol.updateTime(0);
      }
    assertEquals(0, pistol.getCurrentAmmo());
    entity1.attack(entity2, 2);
    assertEquals(39,entity2.getCurrentLifePoints());
  }
  
  @Test
  public void testUseMeleeOutRange() throws WeaponException {
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    MockLifeForm entity2 = new MockLifeForm("Bob", 40, 1);
    Weapon pistol = new Pistol();
    assertTrue(entity1.pickUpWeapon(pistol));
    while(pistol.getCurrentAmmo()>0) {
      pistol.fire(100);
      pistol.updateTime(0);
      }
    assertEquals(0, pistol.getCurrentAmmo());
    entity1.attack(entity2, 6);
    assertEquals(40,entity2.getCurrentLifePoints());
  }
  
  @Test
  public void testReload() throws WeaponException {
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    Weapon pistol = new Pistol();
    assertTrue(entity1.pickUpWeapon(pistol));
    while(pistol.getCurrentAmmo()>0) {
      pistol.fire(100);
      pistol.updateTime(0);
      }
    assertEquals(0, pistol.getCurrentAmmo());
    entity1.weapon.reload();
    assertEquals(10, pistol.getCurrentAmmo());
  }
  
  @Test
  public void testLocationNoEnvironment() {
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    assertEquals(-1, entity1.getCol());
    assertEquals(-1, entity1.getRow());
  }
  
  @Test
  public void testLocation() {
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    Environment e2 = Environment.getEnvironment(2, 3);
    e2.addLifeForm(entity1, 1, 01);
    entity1.setLocation(1, 1);
    assertEquals(1, entity1.getCol());
    assertEquals(1, entity1.getRow());
  }
}
