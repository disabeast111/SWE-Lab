package commands;

import lifeform.LifeForm;

public class TurnNorthCommand implements Command{
  LifeForm lifeForm;
  
  public TurnNorthCommand(LifeForm l) {
    this.lifeForm = l;
  }

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    // lifeform setDirection(0)
    lifeForm.setDirection(0);
  }

}
