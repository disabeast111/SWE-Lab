package commands;

import environment.Environment;
import exceptions.WeaponException;
import lifeform.LifeForm;
import weapon.Weapon;

/**
 * @author Ethan J
 */
public class AttackCommand implements Command {
  LifeForm attacker;
  Environment enviro;
  int r;
  int c;
  int direction;
  int distance;
  LifeForm target = null;

  /**
   * Constructor takes in LifeForm that is attacking and Environment to attack in
   * 
   * @param l is the LifeForm
   * @param e is the Environment
   */
  public AttackCommand(LifeForm l, Environment e) {
    this.attacker = l;
    this.enviro = e;
  }

  /**
   * Execute command to attack
   */
  @Override
  public void execute() throws WeaponException {
    distance = 5;
    r = attacker.getRow();
    c = attacker.getCol();
    int tempR = r + 1;
    int tempC = c + 1;
    int tempRM = r - 1;
    int tempCM = c - 1;
    if (r == 0) {
      tempRM = -1;
    }
    if (c == 0) {
      tempCM = -1;
    }
    direction = attacker.getCurrentDirection();
    if (direction == 0) {
      while (tempRM >= 0 && enviro.getLifeForm(tempRM, c) == null) {
        tempRM--;
        distance = distance + 5;

      }
      if (tempRM < 0) {
        tempRM = 0;
      }
      target = enviro.getLifeForm(tempRM, c);
    } else if (direction == 1) {
      while (tempC < enviro.getNumCols() - 1 && enviro.getLifeForm(r, tempC) == null) {
        tempC++;
        distance = distance + 5;

      }
      if (tempC > enviro.getNumCols() - 1) {
        tempC = enviro.getNumCols() - 1;
      }
      target = enviro.getLifeForm(r, tempC);
    } else if (direction == 2) {
      while (tempR < enviro.getNumRows() - 1 && enviro.getLifeForm(tempR, c) == null) {
        tempR++;
        distance = distance + 5;

      }
      if (tempR > enviro.getNumRows() - 1) {
        tempR = enviro.getNumRows() - 1;
      }
      target = enviro.getLifeForm(tempR, c);
    } else if (direction == 3) {
      while (tempCM >= 0 && enviro.getLifeForm(r, tempCM) == null) {
        tempCM--;
        distance = distance + 5;

      }
      if (tempCM < 0) {
        tempCM = 0;
      }
      target = enviro.getLifeForm(r, tempCM);
    }
    if (target != null) {
      attacker.attack(target, distance);
    } else {
      Weapon w = attacker.getCurrentWeapon();
      w.fire(1);
    }
  }

}
