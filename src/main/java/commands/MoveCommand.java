package commands;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.LifeForm;

/**
 * @author Ethan J
 */
public class MoveCommand implements Command {
  LifeForm lifeForm;
  Environment enviro;

  /**
   * Constructor takes in LifeForm and Environment and sets instance
   * 
   * @param l is the LifeForm
   * @param e is the Environment
   */
  public MoveCommand(LifeForm l, Environment e) {
    this.lifeForm = l;
    this.enviro = e;
  }

  /**
   * Execute command to move LifeForm
   */
  @Override
  public void execute() {
    try {
      enviro.move(lifeForm);
      lifeForm.updateTime(0);
    } catch (EnvironmentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
