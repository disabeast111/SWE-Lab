package commands;

import weapon.GenericWeapon;

public class ReloadCommand implements Command{
  GenericWeapon weapon;
  
  public ReloadCommand(GenericWeapon w) {
    this.weapon = w;
  }

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    weapon.reload();
    
  }

}
