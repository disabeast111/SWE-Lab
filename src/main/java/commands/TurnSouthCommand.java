package commands;

import lifeform.LifeForm;

/**
 * @author Ethan J
 */
public class TurnSouthCommand implements Command {
  Invoker inv = Invoker.invoker();
  LifeForm lifeForm;
  
  /**
   * Constructor gets info from the focused cell
   */
  public TurnSouthCommand() {
    lifeForm = inv.focusedCell.getLifeForm();
  }

  /**
   * Execute command changes that LifeForm's direction
   */
  @Override
  public void execute() {
    lifeForm.setDirection(2);
  }

  public String toString() {
    return "TurnSouthCommand";
  }
}
