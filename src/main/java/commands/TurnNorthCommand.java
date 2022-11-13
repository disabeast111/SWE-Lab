package commands;

import lifeform.LifeForm;

public class TurnNorthCommand implements Command {
  LifeForm lifeForm;

//Constructor takes in LifeForm being turned and sets instance
  public TurnNorthCommand(LifeForm l) {
    this.lifeForm = l;
  }

//Execute command changes that LifeForm's direction
  @Override
  public void execute() {
    lifeForm.setDirection(0);
  }

}
