package commands;

import environment.Environment;

import lifeform.LifeForm;
import weapon.Weapon;

/**
 * @author Ethan J
 */
public class DropCommand implements Command {
  Invoker inv = Invoker.invoker();
  LifeForm lifeForm;
  Environment enviro;
  Weapon tempW;
  int col;
  int row;
  
  /**
   * Constructor gets info from the focused cell
   */
  public DropCommand() {
    lifeForm = inv.focusedCell.getLifeForm();
    enviro = inv.env;
    col = lifeForm.getCol();
    row = lifeForm.getRow();
  }

  /**
   * Execute command drops the LifeForm's weapon
   */
  @Override
  public void execute() {
    Weapon[] temp = enviro.getWeapons(row, col);
    if (temp[0] == null || temp[1] == null) {
      tempW = lifeForm.getCurrentWeapon();
      lifeForm.dropWeapon();
      enviro.addWeapon(tempW, row, col);
    }

  }
  
  public String toString() {
    return "DropCommand";
  }

}
