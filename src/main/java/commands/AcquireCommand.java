package commands;

import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

/**
 * @author Ethan J
 */
public class AcquireCommand implements Command {
  Invoker inv;
  LifeForm lifeForm;
  Environment enviro;
  Weapon tempW;
  int col;
  int row;

  /**
   * Constructor takes in LifeForm that is acquiring and the environment it is
   * doing so in
   * 
   * @param l is the LifeForm
   * @param e is the Environment
   */
  public AcquireCommand() {
    inv = Invoker.invoker();
    lifeForm = inv.focusedCell.getLifeForm();
    tempW = lifeForm.getCurrentWeapon();
    enviro = inv.env;
    col = lifeForm.getCol();
    row = lifeForm.getRow();
  }

  /**
   * Execute command to acquire a weapon
   */
  @Override
  public void execute() {
    tempW = lifeForm.getCurrentWeapon();
    Weapon[] temp = enviro.getWeapons(row, col);
    if (temp[0] != null && tempW == null) {
      lifeForm.pickUpWeapon(enviro.removeWeapon(temp[0], row, col));
    } else if (temp[0] != null && tempW != null) {
      lifeForm.dropWeapon();
      lifeForm.pickUpWeapon(enviro.removeWeapon(temp[0], row, col));
      enviro.addWeapon(tempW, row, col);
    }

  }

}
