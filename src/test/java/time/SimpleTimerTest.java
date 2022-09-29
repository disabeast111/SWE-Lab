package time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gameplay.SimpleTimer;
import gameplay.Timer;

public class SimpleTimerTest {
  @Test
  public void testInitialization() {
    SimpleTimer s = new SimpleTimer();
    assertEquals(0, s.getRound());
    assertEquals(0, s.getNumObservers());
    assertTrue(s instanceof Timer);
  }

  @Test
  public void testAddObserver() {
    SimpleTimer s = new SimpleTimer();
    MockSimpleTimerObserver o = new MockSimpleTimerObserver();
    s.addTimeObserver(o);
    assertEquals(1, s.getNumObservers());
  }

  @Test
  public void testRemoveObserver() {
    SimpleTimer s = new SimpleTimer();
    MockSimpleTimerObserver o = new MockSimpleTimerObserver();
    assertEquals(0, s.getNumObservers());
    s.addTimeObserver(o);
    assertEquals(1, s.getNumObservers());
    s.removeTimeObserver(o);
    assertEquals(0, s.getNumObservers());
  }
  
  @Test
  public void testRemoveObserver2() {
    SimpleTimer s = new SimpleTimer();
    MockSimpleTimerObserver o1 = new MockSimpleTimerObserver();
    MockSimpleTimerObserver o2 = new MockSimpleTimerObserver();
    MockSimpleTimerObserver o3 = new MockSimpleTimerObserver();
    assertEquals(0, s.getNumObservers());
    s.addTimeObserver(o1);
    s.addTimeObserver(o2);
    s.addTimeObserver(o3);
    s.addTimeObserver(o1);
    assertEquals(3, s.getNumObservers());
    s.removeTimeObserver(o1);
    assertEquals(2, s.getNumObservers());
  }

  @Test
  public void testChangewO() {
    SimpleTimer s = new SimpleTimer();
    MockSimpleTimerObserver o = new MockSimpleTimerObserver();
    s.addTimeObserver(o);
    assertEquals(0, s.getRound());
    assertEquals(1, s.getNumObservers());
    s.timeChanged();
    assertEquals(1, s.getRound());
  }

  @Test
  public void testChangeWNoO() {
    SimpleTimer s = new SimpleTimer();
    assertEquals(0, s.getRound());
    assertEquals(0, s.getNumObservers());
    s.timeChanged();
    assertEquals(1, s.getRound());
  }

  @Test
  public void testSimpleTimerAsThread() throws InterruptedException {
    SimpleTimer st = new SimpleTimer(1000);
    st.start();
    Thread.sleep(250); // So we are 1/4th a second different
    for (int x = 0; x < 5; x++) {
      assertEquals(x, st.getRound()); // assumes round starts at 0
      Thread.sleep(1000); // wait for the next time change
    }
  }

}
