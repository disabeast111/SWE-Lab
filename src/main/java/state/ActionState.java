package state;

import java.lang.Object;

import environment.Environment;
import lifeform.LifeForm;

public abstract class ActionState {
  protected AIContext context;
  protected Environment e;
  protected LifeForm lifeform;

  public ActionState(AIContext c) {context = c;}
  
  public abstract void executeAction();
}
