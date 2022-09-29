package gameplay;

public interface Timer {
  public void addTimeObserver(TimerObserver o);

  public void timeChanged();

  public void removeTimeObserver(TimerObserver o);
}
