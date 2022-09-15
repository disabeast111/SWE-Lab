package lifeform;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestLifeForm {
  
  @Test
  public void testStorePoints(){
  MockLifeForm entity = new MockLifeForm("Bob", 40);
  assertEquals(40, entity.getCurrentLifePoints());
  }
  
  @Test
  public void testStoreNames(){
  MockLifeForm entity = new MockLifeForm("Jim", 40);
  assertEquals("Jim", entity.getName());
  }
  
  @Test
  public void testTakeHit(){
  MockLifeForm entity = new MockLifeForm("Jim", 40);
  entity.takeHit(5);
  assertEquals(35, entity.getCurrentLifePoints());
  }
  
  @Test
  public void testTakeHit2(){
  MockLifeForm entity = new MockLifeForm("Jim", 20);
  entity.takeHit(15);
  entity.takeHit(15);
  assertEquals(0, entity.getCurrentLifePoints());
  }
}
