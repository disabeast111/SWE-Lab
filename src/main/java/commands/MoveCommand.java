package commands;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.LifeForm;

/**
 * @author Ethan J
 */
public class MoveCommand implements Command {
  Invoker inv = Invoker.invoker();
  LifeForm lifeForm;
  Environment enviro;

  /**
   * Constructor takes in LifeForm and Environment and sets instance
   * 
   * @param l is the LifeForm
   * @param e is the Environment
   */
  public MoveCommand() {
    lifeForm = inv.focusedCell.getLifeForm();
    enviro = inv.env;
  }

  /**
   * Execute command to move LifeForm
   */
  @Override
  public void execute() {

    try {
      enviro.move(lifeForm);
      inv.focusedCell = enviro.getCell(lifeForm.getRow(), lifeForm.getCol());
    } catch (EnvironmentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    //lifeForm.updateTime(0);
    if (lifeForm.hasWeapon()) {
      lifeForm.getCurrentWeapon().updateTime(0);
    }
  }
  
  public String toString() {
    return "MoveCommand";
  }
}
