package state;

import environment.Environment;
import gameplay.GameBoard;
import gameplay.TimerObserver;
import lifeform.LifeForm;

public class AiContext implements TimerObserver {
  ActionState currentState;
  DeadState deadState;
  HasWeaponState hasWeaponState;
  NoWeaponState noWeaponState;
  OutOfAmmoState outOfAmmoState;
  LifeForm lifeForm;
  Environment environment;
  
  /**
   * 
   * @param l the LifeForm given to the context
   * @param e the reference to the Singleton Environments
   */
  public AiContext(LifeForm l, Environment e) {
    lifeForm = l;
    environment = e;
    deadState = new DeadState(this);
    hasWeaponState = new HasWeaponState(this);
    noWeaponState = new NoWeaponState(this);
    outOfAmmoState = new OutOfAmmoState(this);
    currentState = noWeaponState;
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

  @Override
  public void updateTime(int time) {
    int tempRow = lifeForm.getRow();
    int tempCol = lifeForm.getCol();

    this.execute();
    int tempRow1 = lifeForm.getRow();
    int tempCol1 = lifeForm.getCol();
    GameBoard.getInstance().updateCell(tempRow1, tempCol1);
    GameBoard.getInstance().updateCell(tempRow, tempCol);
    GameBoard.getInstance().updateStats();
    
  }
}
