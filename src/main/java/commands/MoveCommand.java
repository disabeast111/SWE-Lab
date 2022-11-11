package commands;

import environment.Environment;
import lifeform.LifeForm;

public class MoveCommand implements Command {
  int speed;
  LifeForm lifeForm;
  Environment enviro;
  
  public MoveCommand(LifeForm l, Environment e) {
    this.lifeForm = l;
    this.enviro = e;
  }
  

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    speed = lifeForm.getMaxSpeed();
    enviro.move(lifeForm);
    
  }

}
