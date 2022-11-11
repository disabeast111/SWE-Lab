package commands;

import lifeform.LifeForm;

public class TurnEastCommand implements Command{
  LifeForm lifeForm;
  
  public TurnEastCommand(LifeForm l){
    this.lifeForm = l;
  }

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    // lifeform setDirection(1)
    lifeForm.setDirection(1);
  }

}
