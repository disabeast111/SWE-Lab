package state;

import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.LifeForm;
import random.RandBool;
import random.RandInt;
import weapon.Weapon;

public class HasWeaponState extends ActionState {

  public HasWeaponState(AiContext c) {
    super(c);
  }

  /**
   * if dead move to dead state move till find target attack
   */
  public void executeAction() {
    int direction = lifeform.getCurrentDirection();
    int distance = 5;
    int row = lifeform.getRow();
    int col = lifeform.getCol();
    int tempR = row + 1;
    int tempC = col + 1;
    int tempRowM = row - 1;
    int tempColM = col - 1;
    LifeForm target = null;
    Weapon w = lifeform.getCurrentWeapon();

    if (lifeform.getCurrentLifePoints() <= 0) {
      dead();
    }

    if (direction == 0) {
      while (tempRowM >= 0 && env.getLifeForm(tempRowM, col) == null) {
        tempRowM--;
        distance = distance + 5;

      }
      if (tempRowM < 0) {
        tempRowM = 0;
      }
      target = env.getLifeForm(tempRowM, col);

      if (row == 0) {
        target = null;
      }
    } else if (direction == 1) {
      while (tempC < env.getNumCols() - 1 && env.getLifeForm(row, tempC) == null) {
        tempC++;
        distance = distance + 5;

      }
      if (tempC >= env.getNumCols()) {
        tempC = env.getNumCols() - 1;
      }
      target = env.getLifeForm(row, tempC);

      if (col == env.getNumCols()) {
        target = null;
      }
    } else if (direction == 2) {
      while (tempR < env.getNumRows() - 1 && env.getLifeForm(tempR, col) == null) {
        tempR++;
        distance = distance + 5;

      }
      if (tempR >= env.getNumRows()) {
        tempR = env.getNumRows() - 1;
      }
      target = env.getLifeForm(tempR, col);

      if (row == env.getNumRows()) {
        target = null;
      }
    } else if (direction == 3) {
      while (tempColM >= 0 && env.getLifeForm(row, tempColM) == null) {
        tempColM--;
        distance = distance + 5;

      }
      if (tempColM < 0) {
        tempColM = 0;
      }
      target = env.getLifeForm(row, tempColM);

      if (col == 0) {
        target = null;
      }
    }
    if (target != null && lifeform.getClass() != target.getClass()
        && target.getCurrentLifePoints() > 0) {
      try {
        lifeform.attack(target, distance);
        if (w.getCurrentAmmo() == 0) {
          context.setCurrentState(context.getOutOfAmmoState());
        }
      } catch (WeaponException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {

      try {
        search();
      } catch (EnvironmentException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      direction = lifeform.getCurrentDirection();
      distance = 5;
      row = lifeform.getRow();
      col = lifeform.getCol();
      tempR = row + 1;
      tempC = col + 1;
      tempRowM = row - 1;
      tempColM = col - 1;

      if (direction == 0) {
        while (tempRowM >= 0 && env.getLifeForm(tempRowM, col) == null) {
          tempRowM--;
          distance = distance + 5;

        }
        if (tempRowM < 0) {
          tempRowM = 0;
        }
        target = env.getLifeForm(tempRowM, col);

        if (row == 0) {
          target = null;
        }
      } else if (direction == 1) {
        while (tempC < env.getNumCols() - 1 && env.getLifeForm(row, tempC) == null) {
          tempC++;
          distance = distance + 5;

        }
        if (tempC >= env.getNumCols()) {
          tempC = env.getNumCols() - 1;
        }
        target = env.getLifeForm(row, tempC);

        if (col == env.getNumCols()) {
          target = null;
        }
      } else if (direction == 2) {
        while (tempR < env.getNumRows() - 1 && env.getLifeForm(tempR, col) == null) {
          tempR++;
          distance = distance + 5;

        }
        if (tempR >= env.getNumRows()) {
          tempR = env.getNumRows() - 1;
        }
        target = env.getLifeForm(tempR, col);

        if (row == env.getNumRows()) {
          target = null;
        }
      } else if (direction == 3) {
        while (tempColM >= 0 && env.getLifeForm(row, tempColM) == null) {
          tempColM--;
          distance = distance + 5;

        }
        if (tempColM < 0) {
          tempColM = 0;
        }
        target = env.getLifeForm(row, tempColM);

        if (col == 0) {
          target = null;
        }
      }
      if (target != null && lifeform.getClass() != target.getClass()
          && target.getCurrentLifePoints() > 0) {
        try {
          lifeform.attack(target, distance);
          if (w.getCurrentAmmo() == 0) {
            context.setCurrentState(context.getOutOfAmmoState());
          }
        } catch (WeaponException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }

  private void dead() {
    context.setCurrentState(context.getDeadState());
  }

  private void search() throws EnvironmentException {
    Integer direction = new RandInt(1, 4).choose();
    direction = (lifeform.getCurrentDirection() + direction) % 4;
    lifeform.setDirection(direction);
    if (new RandBool().choose()) {
      env.move(lifeform);
    }
  }
}
