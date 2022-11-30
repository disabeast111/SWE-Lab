package state;

import random.RandInt;
import weapon.Weapon;

public class DeadState extends ActionState {

  public DeadState(AiContext c) {
    super(c);
  }

  /**
   *action revive
   *set weapon to rand position
   */
  public void executeAction() { // respawn
    int r = new RandInt(0, env.getNumRows()).choose();
    int c = new RandInt(0, env.getNumCols()).choose();
    
    Weapon[] wp = env.getWeapons(r, c);
    
    if (lifeform.getCurrentWeapon() != null) {
      while (wp[0] != null && wp[1] != null) {
        r = new RandInt(0, env.getNumRows()).choose();
        c = new RandInt(0, env.getNumCols()).choose();
        wp = env.getWeapons(r, c);
      }
      
      env.addWeapon(lifeform.dropWeapon(), r, c);
    }
    context.setCurrentState(context.getNoWeaponState());
    lifeform.revive();
  }
}
