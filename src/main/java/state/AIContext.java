package state;

import environment.Environment;
import lifeform.LifeForm;

public class AIContext {
  ActionState currentState;
  DeadState deadState = new DeadState(this);
  HasWeaponState hasWeaponState = new HasWeaponState(this);
  NoWeaponState noWeaponState = new NoWeaponState(this);
  OutOfAmmoState outOfAmmoState = new OutOfAmmoState(this);
  LifeForm lifeForm;
  Environment environment;
  
  public AIContext(LifeForm l, Environment e) {
    lifeForm = l;
    environment = e;
  }
  
  public LifeForm getLifeForm() {
    return lifeForm;
  }
  
  public Environment getEnvironment() {
    return environment;
  }
  
  public void setCurrentState(ActionState state) {
    currentState = state;
  }
  
  public ActionState getCurrentState() {
    return currentState;
  }
  
  public DeadState getDeadState() {
    return deadState;
  }
  
  public HasWeaponState getHasWeaponState() {
    return hasWeaponState;
  }
  
  public NoWeaponState getNoWeaponState() {
    return noWeaponState;
  }
  
  public ActionState getOutOfAmmoState() {
    return outOfAmmoState;
  }
  
  public void execute() {
    currentState.executeAction();
  }
}
