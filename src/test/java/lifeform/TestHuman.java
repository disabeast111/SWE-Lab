package lifeform;

import static org.junit.Assert.*;
import org.junit.Test;

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
  
}
