package commands;

import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

/**
 * @author Ethan J
 */
public class AcquireCommand implements Command {
  LifeForm lifeForm;
  Environment enviro;
  Weapon t;
  int c;
  int r;

  /**
   * Constructor takes in LifeForm that is acquiring and the environment it is
   * doing so in
   * 
   * @param l is the LifeForm
   * @param e is the Environment
   */
  public AcquireCommand(LifeForm l, Environment e) {
    this.lifeForm = l;
    this.enviro = e;
    c = lifeForm.getCol();
    r = lifeForm.getRow();
  }

  /**
   * Execute command to acquire a weapon
   */
  @Override
  public void execute() {
    t = lifeForm.getCurrentWeapon();
    Weapon[] temp = enviro.getWeapons(r, c);
    if (temp[0] != null && t == null) {
      lifeForm.pickUpWeapon(enviro.removeWeapon(temp[0], r, c));
    } else if (temp[0] != null && t != null) {
      lifeForm.dropWeapon();
      lifeForm.pickUpWeapon(enviro.removeWeapon(temp[0], r, c));
      enviro.addWeapon(t, r, c);
    }

  }

}
