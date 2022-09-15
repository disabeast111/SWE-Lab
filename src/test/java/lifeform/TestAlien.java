package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.RecoveryRateException;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;

public class TestAlien {
  
  @Test
  public void testInitialization() throws RecoveryRateException {
    Alien entity = new Alien("Bob", 40);
    assertEquals("Bob", entity.getName());
    assertEquals(40, entity.getCurrentLifePoints());
  }

  @Test
  public void testLinear() throws RecoveryRateException{
    Alien entity = new Alien("Bob", 40, new RecoveryLinear(5));
    assertEquals("Bob", entity.getName());
    entity.takeHit(10);
    assertEquals(40, entity.getCurrentLifePoints());
  }
  
  @Test
  public void testFractional() throws RecoveryRateException{
    Alien entity = new Alien("Bob", 40, new RecoveryFractional(.5));
    assertEquals("Bob", entity.getName());
    entity.takeHit(10);
    assertEquals(40, entity.getCurrentLifePoints());
  }

}
