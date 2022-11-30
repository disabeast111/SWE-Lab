package state;

import environment.Environment;
import lifeform.LifeForm;

public abstract class ActionState extends java.lang.Object {
  protected AiContext context;
  protected Environment env;
  protected LifeForm lifeform;

  /**
   * Constructor for ActionState
   * @param aiContext
   */
  public ActionState(AiContext c) {
    context = c;
    env = context.getEnvironment();
    lifeform = context.getLifeForm();
  }
  
  public abstract void executeAction();
}
