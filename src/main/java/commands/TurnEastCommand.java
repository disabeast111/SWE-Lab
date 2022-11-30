package commands;

import lifeform.LifeForm;

/**
 * @author Ethan J
 */
public class TurnEastCommand implements Command {
  Invoker inv = Invoker.invoker();
  LifeForm lifeForm;
  
  /**
   * Constructor gets info from the focused cell
   */
  public TurnEastCommand() {
    lifeForm = inv.focusedCell.getLifeForm();
  }

  /**
   * Execute command changes that LifeForm's direction
   */
  @Override
  public void execute() {
    lifeForm.setDirection(1);
  }
  
  public String toString() {
    return "TurnEastCommand";
  }

}
