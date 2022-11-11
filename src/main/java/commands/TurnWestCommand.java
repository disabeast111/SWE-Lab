package commands;

import lifeform.LifeForm;

public class TurnWestCommand implements Command{
  LifeForm lifeForm;
  
  public TurnWestCommand(LifeForm l) {
    this.lifeForm = l;
  }
  @Override
  public void execute() {
    // TODO Auto-generated method stub
    // lifeform setDirection(3)
    lifeForm.setDirection(3);
  }

}
