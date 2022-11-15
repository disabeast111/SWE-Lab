package commands;

import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

public class AcquireCommand implements Command {
  LifeForm lifeForm;
  Environment enviro;
  Weapon t;
  int c;
  int r;
//Constructor takes in LifeForm being turned and sets instance
  public AcquireCommand(LifeForm l, Environment e) {
    this.lifeForm = l;
    this.enviro = e;
    c = lifeForm.getCol();
    r = lifeForm.getRow();
  }

//Execute command changes that LifeForm's direction
  @Override
  public void execute() {
    t = lifeForm.getCurrentWeapon();
    Weapon[] temp = enviro.getWeapons(r,c);
    if(temp[0] != null && t == null) {
      lifeForm.pickUpWeapon(enviro.removeWeapon(temp[0], r, c));
    }
    else if(temp[0] != null && t != null) {
      lifeForm.dropWeapon();
      lifeForm.pickUpWeapon(enviro.removeWeapon(temp[0], r, c));
      enviro.addWeapon(t, r, c);
    }
//    else if(temp[0] == null && t != null && temp[1] != null) {
//      lifeForm.dropWeapon();
//      lifeForm.pickUpWeapon(temp[1]);
//      enviro.removeWeapon(temp[1], r, c);
//      enviro.addWeapon(t, r, c);
//    }
//    else if(temp[0] == null && t == null && temp[1] != null) {
//      lifeForm.pickUpWeapon(temp[1]);
//      enviro.removeWeapon(temp[1], r, c);
//    }

    
  }

}
