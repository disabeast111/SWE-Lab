package state;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import lifeform.MockLifeForm;

public class TestAIContext {
  
  @Test
  public void testChangeActiveState() {
    MockLifeForm lifeform = new MockLifeForm("Dummy", 10, 1);
    Environment environment = Environment.getEnvironment(10, 10);
    AiContext context = new AiContext(lifeform, environment);
    
    assertEquals(context.getCurrentState(), context.getNoWeaponState());
    
    context.setCurrentState(context.getDeadState());
    assertEquals(context.getCurrentState(), context.getDeadState());
  }
  
  @Test
  public void testGetStates() {
    MockLifeForm lifeform = new MockLifeForm("Dummy", 10, 1);
    Environment environment = Environment.getEnvironment(10, 10);
    AiContext context = new AiContext(lifeform, environment);
    
    assertTrue(context.getDeadState() instanceof DeadState);
    assertTrue(context.getHasWeaponState() instanceof HasWeaponState);
    assertTrue(context.getNoWeaponState() instanceof NoWeaponState);
    assertTrue(context.getOutOfAmmoState() instanceof OutOfAmmoState);
  }
}
