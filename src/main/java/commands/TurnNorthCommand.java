package commands;

import lifeform.LifeForm;

/**
 * @author Ethan J
 */
public class TurnNorthCommand implements Command {
  Invoker inv = Invoker.invoker();;
  LifeForm lifeForm;

  /**
   * Constructor takes in LifeForm being turned and sets instance
   * 
   * @param l is the LifeForm
   */
  public TurnNorthCommand() {
    lifeForm = inv.focusedCell.getLifeForm();
  }

  /**
   * Execute command changes that LifeForm's direction
   */
  @Override
  public void execute() {
    lifeForm.setDirection(0);
  }

}
