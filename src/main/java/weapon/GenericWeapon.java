// author: David W
package weapon;

import exceptions.WeaponException;
import gameplay.TimerObserver;

public abstract class GenericWeapon implements Weapon, TimerObserver {
  protected int baseDamage;
  protected int currentAmmo; // ammo in clip
  protected int maxAmmo; // max capacity of clip
  protected int maxRange;
  protected int rateOfFire;
  protected int shotsLeft; // num shots left per round

  public GenericWeapon() {
  }

  public abstract int fire(int distance) throws WeaponException;

  public int getBaseDamage() {
    return baseDamage;
  }

  public int getCurrentAmmo() {
    return currentAmmo;
  }

  public int getMaxAmmo() {
    return maxAmmo;
  }

  public int getMaxRange() {
    return maxRange;
  }

  public int getNumAttachments() {
    return 0;
  }

  public int getRateOfFire() {
    return rateOfFire;
  }

  public int getShotsLeft() {
    return shotsLeft;
  }

  public void reload() {
    currentAmmo = maxAmmo;
  }

  public void updateTime(int time) {
    shotsLeft = getRateOfFire();
  }

  public abstract String toString();
}
