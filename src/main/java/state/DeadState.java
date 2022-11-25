package state;

import environment.Environment;
import lifeform.LifeForm;
import random.RandInt;
import weapon.Weapon;

public class DeadState extends ActionState {

  public DeadState(AIContext c) {
    super(c);
  }

  public void executeAction() {
    Environment e1 = context.getEnvironment();
    LifeForm lf = context.getLifeForm();
    
    int r = new RandInt(0, e1.getNumRows()).choose();
    int c = new RandInt(0, e1.getNumCols()).choose();
    
    Weapon[] wp = e1.getWeapons(r, c);
    
    if (lf.getCurrentWeapon() != null) {
      while (wp[0] != null && wp[1] != null) {
        r = new RandInt(0, e1.getNumRows()).choose();
        c = new RandInt(0, e1.getNumCols()).choose();
        wp = e1.getWeapons(r, c);
      }
      
      e1.addWeapon(lf.dropWeapon(), r, c);
    }
    context.setCurrentState(context.getNoWeapon());
  }
}
