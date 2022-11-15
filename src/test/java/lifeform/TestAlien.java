package lifeform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.RecoveryRateException;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;
import gameplay.SimpleTimer;

/**
 * @author David W
 */
public class TestAlien {
  
  
  
  @Test
  public void testInitialization() throws RecoveryRateException {
    Alien entity = new Alien("Bob", 40);
    assertEquals("Bob", entity.getName());
    assertEquals(40, entity.getCurrentLifePoints());
    assertEquals(2, entity.getMaxSpeed());
  }

  @Test
  public void testLinear() throws RecoveryRateException{
    Alien entity = new Alien("Bob", 40, new RecoveryLinear(5));
    assertEquals("Bob", entity.getName());
    entity.takeHit(10);
    entity.recover();
    assertEquals(35, entity.getCurrentLifePoints());
  }
  
  @Test
  public void testFractional() throws RecoveryRateException{
    Alien entity = new Alien("Bob", 40, new RecoveryFractional(.5));
    entity.takeHit(10);
    entity.recover();
    assertEquals(40, entity.getCurrentLifePoints());
  }

  @Test
  public void testDefaultAttack() throws RecoveryRateException {
    Alien entity = new Alien("Bob", 10);
    assertEquals(10, entity.getAttackStrength());
  }
  
  @Test
  public void testRecoverRate0() throws RecoveryRateException {
    Alien entity = new Alien("Bob", 100, new RecoveryLinear(1), 0);
    entity.takeHit(50);
    entity.recover();
    assertEquals(51, entity.getCurrentLifePoints());
  }
  
  @Test
  public void testRecoverByRound1() throws RecoveryRateException {
    Alien entity = new Alien("Bob", 100, new RecoveryLinear(1), 5);
    SimpleTimer s = new SimpleTimer(50);
    s.addTimeObserver(entity);
    entity.takeHit(50);
    s.run();
    assertEquals(60, entity.getCurrentLifePoints());
  }
  
  @Test
  public void testRecoverByRound2() throws RecoveryRateException {
    Alien entity = new Alien("Bob", 100, new RecoveryLinear(5), 10);
    SimpleTimer s = new SimpleTimer(50);
    s.addTimeObserver(entity);
    entity.takeHit(50);
    s.run();
    assertEquals(75, entity.getCurrentLifePoints());
  }
  
  @Test
  public void testTrackTime() throws InterruptedException {
    SimpleTimer st = new SimpleTimer(1000);
    st.start();
    Thread.sleep(250); // So we are 1/4th a second different
    for (int x = 0; x < 5; x++) {
      assertEquals(x, st.getRound()); // assumes round starts at 0
      Thread.sleep(1000); // wait for the next time change
    }
  }
  
  @Test
  public void testTimeRemove() throws RecoveryRateException {
    Alien entity = new Alien("Bob", 100, new RecoveryLinear(1), 5);
    SimpleTimer s = new SimpleTimer(50);
    entity.takeHit(50);
    s.addTimeObserver(entity);
    s.removeTimeObserver(entity);
    assertEquals(50, entity.getCurrentLifePoints());
  }
  
  @Test
  public void testRateException() throws RecoveryRateException {
    boolean caught = false;
    try {
    Alien entity = new Alien("Bob", 100, new RecoveryLinear(1), -5);
    entity.takeHit(50);
    }
    catch (RecoveryRateException e) {
      caught = true;
    }
    assertTrue(caught);
  }
}
