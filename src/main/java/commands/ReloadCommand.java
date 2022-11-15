package commands;

import lifeform.LifeForm;
import weapon.Weapon;

/**
 * @author Ethan J
 */
public class ReloadCommand implements Command {
  Weapon currentWeapon;
  LifeForm lifeForm;

  /**
   * Constructor takes in LifeForm that will reload and sets instance
   * 
   * @param l is the LifeForm
   */
  public ReloadCommand(LifeForm l) {

    this.lifeForm = l;
    this.currentWeapon = lifeForm.getCurrentWeapon();
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

}
