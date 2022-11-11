package commands;

import lifeform.LifeForm;

public class TurnSouthCommand implements Command{
  LifeForm lifeForm;
  
  public TurnSouthCommand(LifeForm l) {
    this.lifeForm = l;
  }

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    // lifeform setDirection(2)
    lifeForm.setDirection(2);
  }

}
