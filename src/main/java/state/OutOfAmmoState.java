package state;

import lifeform.LifeForm;

public class OutOfAmmoState extends ActionState {

  public OutOfAmmoState(AIContext c) { super(c); }

  @Override
  public void executeAction() {
    LifeForm lf = context.getLifeForm();

    if (lf.getCurrentLifePoints() != 0) {
      lifeform.getCurrentWeapon().reload();
      context.setCurrentState(context.getHasWeapon());
    } else {
      context.setCurrentState(context.getDeadState());
    }
  }
}
