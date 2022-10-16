package weapon;

import exceptions.WeaponException;

public abstract class Attachment implements Weapon {
  protected Weapon base;
  protected int shotsThisRound = 0;

  public Attachment() {

  }

  public abstract int fire(int distance) throws WeaponException;

  public int getBaseDamage() {
    return base.getBaseDamage();
  }

  public int getCurrentAmmo() {
    return base.getCurrentAmmo();
  }

  public int getMaxAmmo() {
    return base.getMaxAmmo();
  }

  public int getMaxRange() {
    return base.getMaxRange();
  }

  public int getNumAttachments() {
    return 1 + base.getNumAttachments();
  }

  public int getRateOfFire() {
    return base.getRateOfFire();
  }

  public int getShotsLeft() {
    return base.getShotsLeft();
  }

  public void reload() {
    base.reload();
  }

  public void updateTime(int time) {
    shotsThisRound = 0;
  }
}
