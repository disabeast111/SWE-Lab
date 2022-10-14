package weapon;

import gameplay.TimerObserver;

public interface Weapon extends TimerObserver {
  public int fire(int distance);
  public int getBaseDamage();
  public int getCurrentAmmo();
  public int getMaxAmmo();
  public int getMaxRange();
  public int getNumAttachments();
  public int getRateOfFire();
  public int getShotsLeft();
  public void reload();
  public String toString();
}
