package commands;

import lifeform.LifeForm;
import weapon.Weapon;

/**
 * @author Ethan J
 */
public class ReloadCommand implements Command {
  Invoker inv = Invoker.invoker();;
  Weapon currentWeapon;
  LifeForm lifeForm;

  /**
   * Constructor takes in LifeForm that will reload and sets instance
   * 
   * @param l is the LifeForm
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
