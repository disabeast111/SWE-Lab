package gameplay;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David W
 */
public class SimpleTimer extends Thread implements Timer {
  private List<TimerObserver> theObservers;
  private int round;
  private int sleepTime;

  /**
   * SimpleTimer constructor
   */
  public SimpleTimer() {
    this(1000);
  }

  /**
   * SimpleTimer constructor
   * @param sleep (time in milliseconds)
   */
  public SimpleTimer(int sleep) {
    theObservers = new ArrayList<>();
    round = 0;
    sleepTime = sleep;
  }

  /**
   * addTimeObserver
   * @param o (TimerObserver)
   */
  public void addTimeObserver(TimerObserver o) {
    if (!theObservers.contains(o)) {
      theObservers.add(o);
    }
  }

  public void timeChanged() {
    round++;
    theObservers.forEach(ob -> ob.updateTime(getRound()));
  }

  /**
   * removeTimeObserver
   * @param o (TimerObserver)
   */
  public void removeTimeObserver(TimerObserver o) {
    if (theObservers.size() > 0) {
      theObservers.remove(o);
    }
  }

  public int getNumObservers() {
    return theObservers.size();
  }

  public int getRound() {
    return round;
  }

  @Override
  public void run() {
    Thread t = new Thread();
    t.start();
    for (int x = 0; x < 50; x++) {
      try {
        Thread.sleep(sleepTime);
        timeChanged();
      } catch (InterruptedException e) {
        System.out.println("Something bad happened.");
      }
    }
  }

}
