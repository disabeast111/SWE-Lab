package weapon;

public abstract class GenericWeapon implements Weapon{
  protected int basedDamage;
  protected int currentAmmo;
  protected int maxAmmo;
  protected int maxRange;
  protected int rateOfFire;
  protected int shotsLeft;
  
  public GenericWeapon() {
  }
  
  public int fire(int distance) {return 0; /*implement*/}
  public int getBaseDamage() {return 0; /*implement*/}
  public int getCurrentAmmo() {return 0; /*implement*/}
  public int getMaxAmmo() {return 0; /*implement*/}
  public int getMaxRange() {return 0; /*implement*/}
  public int getNumAttachments() {return 0; /*implement*/}
  public int getRateOfFire() {return 0; /*implement*/}
  public int getShotsLeft() {return 0; /*implement*/}
  public void reload() {/*implement*/}
  
  @Override
  public String toString() {return "0"; /*implement*/}
}
