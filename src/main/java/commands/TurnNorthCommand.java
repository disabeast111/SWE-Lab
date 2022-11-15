package commands;

import lifeform.LifeForm;

/**
 * @author Ethan J
 */
public class TurnNorthCommand implements Command {
  LifeForm lifeForm;

  /**
   * Constructor takes in LifeForm being turned and sets instance
   * 
   * @param l is the LifeForm
   */
  public TurnNorthCommand(LifeForm l) {
    this.lifeForm = l;
  }

  /**
   * Execute command changes that LifeForm's direction
   */
  @Override
  public void execute() {
    lifeForm.setDirection(0);
  }

}
