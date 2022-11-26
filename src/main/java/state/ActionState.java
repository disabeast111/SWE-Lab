package state;

import java.lang.Object;

import environment.Environment;
import lifeform.LifeForm;

public abstract class ActionState {
  protected AIContext context;
  protected Environment e;
  protected LifeForm lifeform;

  public ActionState(AIContext c) {
    context = c;
    e = context.getEnvironment();
    lifeform = context.getLifeForm();
    }
  
  public abstract void executeAction();
}
