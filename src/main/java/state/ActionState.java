package state;

import environment.Environment;
import lifeform.LifeForm;

public abstract class ActionState extends java.lang.Object {
  protected AiContext context;
  protected Environment e;
  protected LifeForm lifeform;

  public ActionState(AiContext c) {
    context = c;
    e = context.getEnvironment();
    lifeform = context.getLifeForm();
    }
  
  public abstract void executeAction();
}
