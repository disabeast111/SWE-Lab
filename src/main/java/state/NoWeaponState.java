package state;

import environment.Cell;
import exceptions.EnvironmentException;
import random.RandInt;

public class NoWeaponState extends ActionState {

  public NoWeaponState(AiContext c) {
    super(c);
  }

  @Override
  public void executeAction() {
    int row = lifeform.getRow();
    int col = lifeform.getCol();
    Cell c;

    try {
      c = env.getCell(row, col);
    } catch (EnvironmentException ex) {
      throw new RuntimeException(ex);
    }

    if (lifeform.getCurrentLifePoints() != 0) {

      if (c.getWeaponsCount() == 0) {
        try {
          search();
        } catch (EnvironmentException ex) {
          throw new RuntimeException(ex);
        }
      } else if (c.getWeapon1() != null) {
        if (lifeform.pickUpWeapon(c.getWeapon1())) {
          c.removeWeapon(c.getWeapon1());
        }
        context.setCurrentState(context.getHasWeaponState());
      } else if (c.getWeapon2() != null) {
        if (lifeform.pickUpWeapon(c.getWeapon2())) {
          c.removeWeapon(c.getWeapon2());
        }
        context.setCurrentState(context.getHasWeaponState());
      }

    } else {
      context.setCurrentState(context.getDeadState());
    }
  }

  private void search() throws EnvironmentException {
    Integer direction = new RandInt(0, 4).choose();
    lifeform.setDirection(direction);
    env.move(context.getLifeForm());
  }
}