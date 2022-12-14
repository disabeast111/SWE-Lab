package commands;

import environment.Environment;
import exceptions.WeaponException;
import lifeform.LifeForm;
import weapon.Weapon;

/**
 * @author Ethan J
 */
public class AttackCommand implements Command {
  Invoker inv = Invoker.invoker();
  LifeForm attacker;
  Environment enviro;
  int row;
  int col;
  int direction;
  int distance;
  LifeForm target = null;

  /**
   * Constructor gets info from the focused cell
   */
  public AttackCommand() {
    attacker = inv.focusedCell.getLifeForm();
    enviro = inv.env;
  }

  /**
   * Execute command to attack
   */
  @Override
  public void execute() throws WeaponException {
    distance = 5;
    row = attacker.getRow();
    col = attacker.getCol();
    int tempR = row + 1;
    int tempC = col + 1;
    int tempRowM = row - 1;
    int tempColM = col - 1;

    direction = attacker.getCurrentDirection();
    if (direction == 0) {
      while (tempRowM >= 0 && enviro.getLifeForm(tempRowM, col) == null) {
        tempRowM--;
        distance = distance + 5;

      }
      if (tempRowM < 0) {
        tempRowM = 0;
      }
      target = enviro.getLifeForm(tempRowM, col);

      if (row == 0) {
        target = null;
      }
    } else if (direction == 1) {
      while (tempC < enviro.getNumCols() - 1 && enviro.getLifeForm(row, tempC) == null) {
        tempC++;
        distance = distance + 5;

      }
      if (tempC >= enviro.getNumCols()) {
        tempC = enviro.getNumCols() - 1;
      }
      target = enviro.getLifeForm(row, tempC);

      if (col == enviro.getNumCols()) {
        target = null;
      }
    } else if (direction == 2) {
      while (tempR < enviro.getNumRows() - 1 && enviro.getLifeForm(tempR, col) == null) {
        tempR++;
        distance = distance + 5;

      }
      if (tempR >= enviro.getNumRows()) {
        tempR = enviro.getNumRows() - 1;
      }
      target = enviro.getLifeForm(tempR, col);

      if (row == enviro.getNumRows()) {
        target = null;
      }
    } else if (direction == 3) {
      while (tempColM >= 0 && enviro.getLifeForm(row, tempColM) == null) {
        tempColM--;
        distance = distance + 5;

      }
      if (tempColM < 0) {
        tempColM = 0;
      }
      target = enviro.getLifeForm(row, tempColM);

      if (col == 0) {
        target = null;
      }
    }
    if (target != null) {
      attacker.attack(target, distance);
    } else {
      Weapon w = attacker.getCurrentWeapon();
      w.fire(1);
    }
  }

  public String toString() {
    return "AttackCommand";
  }

}
