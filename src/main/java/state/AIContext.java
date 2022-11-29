package state;

import environment.Environment;
import lifeform.LifeForm;

public class AIContext {
  ActionState currentState;
  DeadState deadState;
  HasWeaponState hasWeaponState;
  NoWeaponState noWeaponState;
  OutOfAmmoState outOfAmmoState;
  LifeForm lifeForm;
  Environment environment;
  
  public AIContext(LifeForm l, Environment e) {
    lifeForm = l;
    environment = e;
    deadState = new DeadState(this);
    hasWeaponState = new HasWeaponState(this);
    noWeaponState = new NoWeaponState(this);
    outOfAmmoState = new OutOfAmmoState(this);
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
  
  public OutOfAmmoState getOutOfAmmoState() {
    return outOfAmmoState;
  }
  
  public void execute() {
    currentState.executeAction();
  }
}
