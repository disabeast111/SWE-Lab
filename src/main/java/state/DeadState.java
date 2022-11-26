package state;

import random.RandInt;
import weapon.Weapon;

public class DeadState extends ActionState {

  public DeadState(AIContext c) {
    super(c);
  }

  public void executeAction() { // respawn
    int r = new RandInt(0, e.getNumRows()).choose();
    int c = new RandInt(0, e.getNumCols()).choose();
    
    Weapon[] wp = e.getWeapons(r, c);
    
    if (lifeform.getCurrentWeapon() != null) {
      while (wp[0] != null && wp[1] != null) {
        r = new RandInt(0, e.getNumRows()).choose();
        c = new RandInt(0, e.getNumCols()).choose();
        wp = e.getWeapons(r, c);
      }
      
      e.addWeapon(lifeform.dropWeapon(), r, c);
    }
    context.setCurrentState(context.getNoWeapon());
  }
}
