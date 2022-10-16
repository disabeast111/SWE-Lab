package weapon;

import exceptions.WeaponException;

public abstract class GenericWeapon implements Weapon {
  protected int baseDamage;
  protected int currentAmmo;
  protected int maxAmmo;
  protected int maxRange;
  protected int rateOfFire;
  protected int shotsLeft;

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

  @Override
  public abstract String toString();
}
