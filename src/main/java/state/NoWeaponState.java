package state;

import environment.Cell;
import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.LifeForm;
import random.RandInt;

public class NoWeaponState extends ActionState {

  public NoWeaponState(AIContext c) {
    super(c);
  }

  @Override
  public void executeAction() {
    Environment env = context.getEnvironment();
    LifeForm lf = context.getLifeForm();
    int row = lifeform.getRow();
    int col = lifeform.getCol();
    Cell c;

    try {
      c = env.getCell(row, col);
    } catch (EnvironmentException ex) {
      throw new RuntimeException(ex);
    }

    if (lf.getCurrentLifePoints() != 0) {

      if (c.getWeapon1() == null && c.getWeapon2() == null) {
        try {
          search();
        } catch (EnvironmentException ex) {
          throw new RuntimeException(ex);
        }
      } else if (c.getWeapon1() != null) {
        lf.pickUpWeapon(c.getWeapon1());
        context.setCurrentState(context.getHasWeapon());
      } else if (c.getWeapon2() != null) {
        lf.pickUpWeapon(c.getWeapon2());
        context.setCurrentState(context.getHasWeapon());
      }

    } else {
      context.setCurrentState(context.getDeadState());
    }
  }

  public void search() throws EnvironmentException {
    Environment env = context.getEnvironment();
    Integer direction = new RandInt(0, 3).choose();
    lifeform.setDirection(direction);
    env.move(context.getLifeForm());
  }
}