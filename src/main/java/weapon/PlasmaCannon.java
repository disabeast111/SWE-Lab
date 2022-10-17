package weapon;

import exceptions.WeaponException;

public class PlasmaCannon extends GenericWeapon {

  public PlasmaCannon() {
    baseDamage = 50;
    maxRange = 40;
    rateOfFire = 1;
    maxAmmo = 4;
    currentAmmo = 4;
    shotsLeft = rateOfFire;
<<<<<<< HEAD

=======
>>>>>>> branch 'main' of https://gitlab.engr.ship.edu/swe200-2022f/lab4-team1.git
  }

  @Override
  public void updateTime(int time) {
    // TODO Auto-generated method stub
    shotsLeft = rateOfFire;
  }

  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Cannot be a negative distance");
    }
    if (currentAmmo == 0) {
      return 0;
    }
<<<<<<< HEAD
    if(shotsLeft <= 0) {
      return 0;
    }
    shotsLeft -= 1;

=======
    currentAmmo -= 1;
    shotsLeft -= 1;
>>>>>>> branch 'main' of https://gitlab.engr.ship.edu/swe200-2022f/lab4-team1.git
    if (distance > maxRange) {
      return 0;
    }
    double bd = baseDamage;
    double ca = currentAmmo;
    double ma = maxAmmo;
    double doubleDamage = 0;
    doubleDamage = bd * (ca / ma);
    return Double.valueOf(Math.floor(doubleDamage)).intValue();
  }

  @Override
  public String toString() {
    return "PlasmaCannon";
  }

}
