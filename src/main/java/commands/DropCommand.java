package commands;

import lifeform.LifeForm;

public class DropCommand implements Command{
  LifeForm lifeForm;
//Constructor takes in LifeForm being turned and sets instance
  public DropCommand(LifeForm l) {
    this.lifeForm = l;
  }

//Execute command drops the LifeForm's weapon
  @Override
  public void execute() {
    lifeForm.dropWeapon();
  }

}
