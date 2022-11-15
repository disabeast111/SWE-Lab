package commands;

import environment.Environment;

import lifeform.LifeForm;
import weapon.Weapon;

/**
 * @author Ethan J
 */
public class DropCommand implements Command {
  LifeForm lifeForm;
  Environment enviro;
  Weapon t;
  int c;
  int r;

  /**
   * Constructor takes in LifeForm dropping weapon and the Environment it is to be
   * dropped in
   * 
   * @param l is the LifeForm
   * @param e is the Environment
   */
  public DropCommand(LifeForm l, Environment e) {
    this.lifeForm = l;
    this.enviro = e;
    c = lifeForm.getCol();
    r = lifeForm.getRow();
  }

  /**
   * Execute command drops the LifeForm's weapon
   */
  @Override
  public void execute() {
    Weapon[] temp = enviro.getWeapons(r, c);
    if (temp[0] == null || temp[1] == null) {
      t = lifeForm.getCurrentWeapon();
      lifeForm.dropWeapon();
      enviro.addWeapon(t, r, c);
    }

  }

}
