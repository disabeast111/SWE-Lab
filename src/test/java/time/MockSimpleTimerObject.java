package time;

/**
 * @author David W
 */
import gameplay.TimerObserver;

class MockSimpleTimerObserver implements TimerObserver {
  public int myTime = 0;

  public void updateTime(int time) {
    myTime = time;
  }
}