package state;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.RecoveryRateException;
import lifeform.*;
import recovery.RecoveryNone;
import weapon.Pistol;

public class testStates {
  Environment e = Environment.getEnvironment(10,10);
  Human lf = new Human("Bob", 40, 0);
  Human lf2 = new Human("Bob", 40, 0); {
  try {
    Alien lf3 = new Alien("Bob", 40);
  } catch(RecoveryRateException exception) {
    exception.printStackTrace();}}
  AIContext aic = new AIContext(lf, e);
  AIContext aic2 = new AIContext(lf2,e);
  Pistol p = new Pistol();
  
  @Test
  public void testDeadStateNoWeapon() {
    e.clearBoard();
    NoWeaponState test = new NoWeaponState(aic);
    e.addLifeForm(lf, 2, 4);
    lf.takeHit(20);
    assertEquals(20, lf.getCurrentLifePoints());
    aic.setCurrentState(aic.getDeadState());
    aic.execute();
    assertEquals(40, lf.getCurrentLifePoints());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testDeadStateWeapon() {
    e.clearBoard();
    NoWeaponState test = new NoWeaponState(aic);
    e.addLifeForm(lf, 2, 4);
    lf.pickUpWeapon(p);
    lf.takeHit(20);
    assertEquals(20, lf.getCurrentLifePoints());
    aic.setCurrentState(aic.getDeadState());
    assertEquals(p, lf.getCurrentWeapon());
    aic.execute();
    assertEquals(40, lf.getCurrentLifePoints());
    assertEquals(null, lf.getCurrentWeapon());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testHWNoTarget() {
    e.clearBoard();
    DeadState test = aic.getDeadState();
    e.addLifeForm(lf, 5, 5);
    assertEquals(5, lf.getRow());
    assertEquals(5, lf.getCol());
    assertEquals(0, lf.getCurrentDirection());
    aic.setCurrentState(aic.getHasWeaponState());
    aic.execute();
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testHWSametype() {
    e.clearBoard();
    DeadState test = aic.getDeadState();
    e.addLifeForm(lf, 5, 5);
    e.addLifeForm(lf2, 2, 5);
    assertEquals(0, lf.getCurrentDirection());
    assertEquals(40, lf.getCurrentLifePoints());
    assertEquals(5, lf.getRow());
    assertEquals(5, lf.getCol());
    assertEquals(2, lf2.getRow());
    assertEquals(5, lf2.getCol());
    assertEquals(40, lf2.getCurrentLifePoints());
    aic.setCurrentState(aic.getHasWeaponState());
    aic.execute();
    assertEquals(40, lf2.getCurrentLifePoints());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testHWDifferType() {
    
  }
}
