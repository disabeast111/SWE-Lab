package lifeform;

import static org.junit.Assert.*;
import org.junit.Test;

import exceptions.RecoveryRateException;
import exceptions.WeaponException;

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
}
