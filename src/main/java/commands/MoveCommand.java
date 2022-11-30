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
   * Constructor to set instance
   * 
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
      e.printStackTrace();
    }

    if (lifeForm.hasWeapon()) {
      lifeForm.getCurrentWeapon().updateTime(0);
    }
  }
  
  public String toString() {
    return "MoveCommand";
  }
}
