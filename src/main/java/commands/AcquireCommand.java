package commands;

import lifeform.LifeForm;

public class AcquireCommand implements Command {
  LifeForm lifeForm;
//Constructor takes in LifeForm being turned and sets instance
  public AcquireCommand(LifeForm l) {
    this.lifeForm = l;
  }

//Execute command changes that LifeForm's direction
  @Override
  public void execute() {
    lifeForm.pickUpWeapon(null);
  }

}
