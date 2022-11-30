package random;

import java.util.List;

import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

public class RandWeapon {
  List<Weapon> weaponChoices = List.of(new Pistol(), new ChainGun(), new PlasmaCannon());

  public Weapon choose() {
    return new FromList<Weapon>(weaponChoices).choose();
  }
}
