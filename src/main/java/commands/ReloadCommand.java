package commands;

import lifeform.LifeForm;
import weapon.Weapon;

/**
 * @author Ethan J
 */
public class ReloadCommand implements Command {
  Invoker inv = Invoker.invoker();
  Weapon currentWeapon;
  LifeForm lifeForm;
  
  /**
   * Constructor gets info from the focused cell
   */
  public ReloadCommand() {

    lifeForm = inv.focusedCell.getLifeForm();
    currentWeapon = lifeForm.getCurrentWeapon();
  }

  /**
   * Execute command reloads the LifeForm's current weapon
   */
  @Override
  public void execute() {
    if (currentWeapon != null) {
      currentWeapon.reload();
    }

  }
  
  public String toString() {
    return "ReloadCommand";
  }

}
