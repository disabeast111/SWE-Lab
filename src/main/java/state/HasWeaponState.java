package state;

import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.LifeForm;
import random.RandBool;
import random.RandInt;
import weapon.Weapon;

public class HasWeaponState extends ActionState {

  public HasWeaponState(AIContext c) {
    super(c);
  }

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

    if (row == 0) {
      tempRowM = -1;
    }
    if (col == 0) {
      tempColM = -1;
    }
    
    if (direction == 0) {
      while (tempRowM >= 0 && e.getLifeForm(tempRowM, col) == null) {
        tempRowM--;
        distance = distance + 5;

      }
      if (tempRowM > 0) {
        target = e.getLifeForm(tempRowM, col);
      }
    } else if (direction == 1) {
      while (tempC < e.getNumCols() - 1 && e.getLifeForm(row, tempC) == null) {
        tempC++;
        distance = distance + 5;

      }
      if (tempC < e.getNumCols() - 1) {
        target = e.getLifeForm(row, tempC);
      }
    } else if (direction == 2) {
      while (tempR < e.getNumRows() - 1 && e.getLifeForm(tempR, col) == null) {
        tempR++;
        distance = distance + 5;

      }
      if (tempR < e.getNumRows() - 1) {
        target = e.getLifeForm(tempR, col);
      }
    } else if (direction == 3) {
      while (tempColM >= 0 && e.getLifeForm(row, tempColM) == null) {
        tempColM--;
        distance = distance + 5;

      }
      if (tempColM > 0) {
        target = e.getLifeForm(row, tempColM);
      }
    }
    if (target != null && lifeform.getClass() != target.getClass()) {
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
        
        direction = lifeform.getCurrentDirection();
        distance = 5;
        row = lifeform.getRow();
        col = lifeform.getCol();
        tempR = row + 1;
        tempC = col + 1;
        tempRowM = row - 1;
        tempColM = col - 1;
        
        if (direction == 0) {
          while (tempRowM >= 0 && e.getLifeForm(tempRowM, col) == null) {
            tempRowM--;
            distance = distance + 5;

          }
          if (tempRowM > 0) {
            target = e.getLifeForm(tempRowM, col);
          }
        } else if (direction == 1) {
          while (tempC < e.getNumCols() - 1 && e.getLifeForm(row, tempC) == null) {
            tempC++;
            distance = distance + 5;

          }
          if (tempC < e.getNumCols() - 1) {
            target = e.getLifeForm(row, tempC);
          }
        } else if (direction == 2) {
          while (tempR < e.getNumRows() - 1 && e.getLifeForm(tempR, col) == null) {
            tempR++;
            distance = distance + 5;

          }
          if (tempR < e.getNumRows() - 1) {
            target = e.getLifeForm(tempR, col);
          }
        } else if (direction == 3) {
          while (tempColM >= 0 && e.getLifeForm(row, tempColM) == null) {
            tempColM--;
            distance = distance + 5;

          }
          if (tempColM > 0) {
            target = e.getLifeForm(row, tempColM);
          }
        }
        if (target != null && lifeform.getClass() != target.getClass()) {
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
          dead();
        }
        
      } catch (EnvironmentException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  private void dead() {
    context.setCurrentState(context.getDeadState());
  }
  
  private void search() throws EnvironmentException {
    Integer direction = new RandInt(0, 3).choose();
    lifeform.setDirection(direction);
    if (new RandBool().choose()) {
      e.move(lifeform);
    }
  }
}
