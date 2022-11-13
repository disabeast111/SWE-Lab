package commands;

import lifeform.LifeForm;
import weapon.Weapon;

public class ReloadCommand implements Command{
  Weapon currentWeapon;
  LifeForm lifeForm;
//Constructor takes in LifeForm that will reload and sets instance
  public ReloadCommand(LifeForm l) {
    
    this.lifeForm = l;
    this.currentWeapon = lifeForm.getCurrentWeapon();
  }
// Execute command reloads the LifeForm's current weapon
  @Override
  public void execute() {
    currentWeapon.reload();
    
  }

}
