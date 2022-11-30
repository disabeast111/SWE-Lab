package state;

public class OutOfAmmoState extends ActionState {

  public OutOfAmmoState(AiContext c) { super(c); }

  @Override
  public void executeAction() {
    lifeform  = context.getLifeForm();

    if (lifeform.getCurrentLifePoints() != 0) {
      lifeform.getCurrentWeapon().reload();
      context.setCurrentState(context.getHasWeaponState());
    } else {
      context.setCurrentState(context.getDeadState());
    }
  }
}
