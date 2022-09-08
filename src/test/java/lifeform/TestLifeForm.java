package lifeform;

import static org.junit.Assert.*;
import org.junit.Test;


public class TestLifeForm {
  
  @Test
  public void testInitialization1(){
  LifeForm entity;
  entity = new LifeForm("Bob", 40);
  assertEquals("Bob", entity.getName());
  assertEquals(40, entity.getCurrentLifePoints());
  }
  
  @Test
  public void testInitialization2(){
  LifeForm entity;
  entity = new LifeForm("Jim", 34);
  assertEquals("Jim", entity.getName());
  assertEquals(34, entity.getCurrentLifePoints());
  }
}
