package commands;

import lifeform.LifeForm;

/**
 * @author Ethan J
 */
public class TurnWestCommand implements Command {
  Invoker inv = Invoker.invoker();
  LifeForm lifeForm;
  
  /**
   * Constructor gets info from the focused cell
   */
  public TurnWestCommand() {
    lifeForm = inv.focusedCell.getLifeForm();
  }

  /**
   * Execute command changes that LifeForm's direction
   */
  @Override
  public void execute() {
    lifeForm.setDirection(3);
  }
  
  public String toString() {
    return "TurnWestCommand";
  }
}
