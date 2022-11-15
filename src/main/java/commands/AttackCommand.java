package commands;

import environment.Environment;
import exceptions.WeaponException;
import lifeform.LifeForm;
import weapon.Weapon;

public class AttackCommand implements Command {
  LifeForm attacker;
  Environment enviro;
  int r;
  int c;
  int direction;
  int distance;
  LifeForm target = null;
  
  public AttackCommand(LifeForm l, Environment e) {
    this.attacker = l;
    this.enviro = e;
    //r = attacker.getRow();
    //c = attacker.getCol();
    //direction = attacker.getCurrentDirection();
    //distance = 5;
  }

  @Override
  public void execute() throws WeaponException {
    distance = 5;
    r = attacker.getRow();
    c = attacker.getCol();
    int tempR = r + 1;
    int tempC = c + 1;
    int tempRM = r - 1;
    int tempCM = c - 1;
    direction = attacker.getCurrentDirection();
    if(direction == 0) {
      while(tempRM > 0 && enviro.getLifeForm(tempRM, c) == null) {
        tempRM--;
        distance = distance + 5;
        
      }
      //tempRM++;
      if(tempRM < 0) {
        tempRM = 0;
      }
      target = enviro.getLifeForm(tempRM, c);
    }
    else if(direction == 1) {
      while(tempC < enviro.getNumCols() && enviro.getLifeForm(r, tempC) == null) {
        tempC++;
        distance = distance + 5;
    
      }
      //tempC--;
      if(tempC > enviro.getNumCols()) {
        tempC = enviro.getNumCols() - 1;
      }
      target = enviro.getLifeForm(r, tempC);
    }
    else if(direction == 2) {
      while(enviro.getLifeForm(tempR, c) == null && tempR <= enviro.getNumRows()) {
        tempR++;
        distance = distance + 5;
        
      }
      //tempR--;
      if(tempR > enviro.getNumRows()) {
        tempR = enviro.getNumRows() - 1;
      }
      target = enviro.getLifeForm(tempR, c);
    }
    else if(direction == 3) {
      while(enviro.getLifeForm(r, tempCM) == null && tempCM >= 0) {
        tempCM--;
        distance = distance + 5;
        
      }
      if(tempCM < 0) {
        tempCM = 0;
      }
      target = enviro.getLifeForm(r, tempCM);
    }
    //tempCM++;
    if(target != null) {
      attacker.attack(target, distance);
    }
    if(target == null) {
      Weapon w = attacker.getCurrentWeapon();
      w.fire(1);
    }
  }

}
