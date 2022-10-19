package lifeform;

import static org.junit.Assert.*;
import org.junit.Test;

import exceptions.WeaponException;

/**
 * @author David W
 */
public class TestHuman {
  
  @Test
  public void testInitialization(){
    Human entity = new Human("Bob", 40, -10);
    assertEquals("Bob", entity.getName());
    assertEquals(40, entity.getCurrentLifePoints());
    assertEquals(0, entity.getArmorPoints());
  }
  
  @Test
  public void testSetArmor(){
    Human entity = new Human("Jim", 10, 10);
    assertEquals(10, entity.getArmorPoints());
    entity.setArmorPoints(5);
    assertEquals(5, entity.getArmorPoints());
   }
  
  @Test
  public void testGetArmor(){
    Human entity = new Human("Jim", 10, 10);
    assertEquals(10, entity.getArmorPoints());
  }
  
  @Test
  public void testBelowZero(){
    Human entity = new Human("Bob", 40, -10);
    assertEquals(0, entity.getArmorPoints());
    entity.setArmorPoints(5);
    assertEquals(5, entity.getArmorPoints());
    entity.setArmorPoints(-10);
    assertEquals(0, entity.getArmorPoints());
  }
  
  @Test
  public void testDefault() {
    Human entityB = new Human("Bob", 40, 0);
    assertEquals(5, entityB.getAttackStrength());
  }
  
  @Test
  public void testArmorMore() {
    Human entityB = new Human("Bob", 40, 10);
    entityB.takeHit(5);
    assertEquals(40, entityB.getCurrentLifePoints());
  }
  
  @Test
  public void testArmorLess() {
    Human entityB = new Human("Bob", 40, 5);
    entityB.takeHit(10);
    assertEquals(35, entityB.getCurrentLifePoints());
  }
  
  @Test
  public void testArmorEqual() {
    Human entityB = new Human("Bob", 40, 10);
    entityB.takeHit(10);
    assertEquals(40, entityB.getCurrentLifePoints());
  }
  
  @Test
  public void testAttack() throws WeaponException {
    Human entityB = new Human("Baba", 40, 0);
    Human entityA = new Human("Annella", 40, 10);
    entityB.attackStrength = 20;
    assertEquals(20, entityB.getAttackStrength());
    entityB.attack(entityA,0);
    assertEquals(30, entityA.getCurrentLifePoints());
  }
}
