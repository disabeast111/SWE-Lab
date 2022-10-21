package gameplay;

/**
 * @author David W
 */
public interface Timer {
  public void addTimeObserver(TimerObserver o);

  public void timeChanged();

  public void removeTimeObserver(TimerObserver o);
}
