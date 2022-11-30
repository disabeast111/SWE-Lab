package state;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import environment.Cell;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import environment.Environment;
import exceptions.RecoveryRateException;
import lifeform.*;
import weapon.Pistol;
import weapon.PlasmaCannon;

public class TestStates {
  Environment env = Environment.getEnvironment(10, 10);
  Human lf = new Human("Bob", 40, 0);
  Human lf2 = new Human("Bob", 40, 0);
  AiContext aic = new AiContext(lf, env);
  Pistol p = new Pistol();
  PlasmaCannon pc = new PlasmaCannon();
  Cell c = new Cell();
  
  
  @Test
  public void testDeadStateNoWeapon() {
    env.clearBoard();
    NoWeaponState test = new NoWeaponState(aic);
    env.addLifeForm(lf, 2, 4);
    lf.takeHit(20);
    assertEquals(20, lf.getCurrentLifePoints());
    aic.setCurrentState(aic.getDeadState());
    aic.execute();
    assertEquals(40, lf.getCurrentLifePoints());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testDeadStateWeapon() {
    env.clearBoard();
    NoWeaponState test = new NoWeaponState(aic);
    env.addLifeForm(lf, 2, 4);
    lf.pickUpWeapon(p);
    lf.takeHit(20);
    assertEquals(20, lf.getCurrentLifePoints());
    aic.setCurrentState(aic.getDeadState());
    assertEquals(p, lf.getCurrentWeapon());
    aic.execute();
    assertEquals(40, lf.getCurrentLifePoints());
    assertNull(lf.getCurrentWeapon());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testHWNoTarget() {
    env.clearBoard();
    HasWeaponState test = aic.getHasWeaponState();
    env.addLifeForm(lf, 5, 5);
    lf.pickUpWeapon(p);
//  pass locally with but not gradle test
    assertEquals(5, lf.getRow());
    assertEquals(5, lf.getCol());
    assertEquals(0, lf.getCurrentDirection());
    aic.setCurrentState(aic.getHasWeaponState());
    int dir = lf.getCurrentDirection();
    aic.execute();
    assertNotEquals(dir, lf.getCurrentDirection());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testHWSametype() {
    env.clearBoard();
    HasWeaponState test = aic.getHasWeaponState();
    env.addLifeForm(lf, 5, 5);
    env.addLifeForm(lf2, 2, 5);
    lf.pickUpWeapon(p);
    assertEquals(0, lf.getCurrentDirection());
    assertEquals(40, lf.getCurrentLifePoints());
//  pass locally with but not gradle test
    assertEquals(5, lf.getRow());
    assertEquals(5, lf.getCol());
    assertEquals(2, lf2.getRow());
    assertEquals(5, lf2.getCol());
    assertEquals(40, lf2.getCurrentLifePoints());
    aic.setCurrentState(aic.getHasWeaponState());
    int dir = lf.getCurrentDirection();
    aic.execute();
    assertNotEquals(dir, lf.getCurrentDirection());
    assertEquals(40, lf2.getCurrentLifePoints());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testHWDifferType() throws RecoveryRateException {
    env.clearBoard();
    Alien lf3 = new Alien("Bob", 40);
    HasWeaponState test = aic.getHasWeaponState();
    env.addLifeForm(lf, 5, 5);
    env.addLifeForm(lf3, 2, 5);
    lf.pickUpWeapon(p);
    assertEquals(0, lf.getCurrentDirection());
    assertEquals(40, lf.getCurrentLifePoints());
    assertEquals(40, lf3.getCurrentLifePoints());
    assertEquals(5, lf.getRow());
    assertEquals(5, lf.getCol());
    assertEquals(2, lf3.getRow());
    assertEquals(5, lf3.getCol());
    aic.setCurrentState(aic.getHasWeaponState());
    aic.execute();
    assertEquals(31, lf3.getCurrentLifePoints());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testHWLastShot() throws RecoveryRateException {
    env.clearBoard();
    Alien lf3 = new Alien("Bob", 40);
    OutOfAmmoState test = aic.getOutOfAmmoState();
    env.addLifeForm(lf, 5, 5);
    env.addLifeForm(lf3, 2, 5);
    lf.pickUpWeapon(p);
    try {
      for (int i = 0; i < 9; i++) {
        lf.getCurrentWeapon().fire(20);
        lf.getCurrentWeapon().updateTime(i);
      }
    } catch (WeaponException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    assertEquals(1, lf.getCurrentWeapon().getCurrentAmmo());
    assertEquals(0, lf.getCurrentDirection());
    assertEquals(40, lf.getCurrentLifePoints());
    assertEquals(40, lf3.getCurrentLifePoints());
//  pass locally with but not gradle test
    assertEquals(5, lf.getRow());
    assertEquals(5, lf.getCol());
    assertEquals(2, lf3.getRow());
    assertEquals(5, lf3.getCol());
    aic.setCurrentState(aic.getHasWeaponState());
    aic.execute();
    assertEquals(31, lf3.getCurrentLifePoints());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testHWOutOfRange() throws RecoveryRateException {
    env.clearBoard();
    Alien lf3 = new Alien("Bob", 40);
    HasWeaponState test = aic.getHasWeaponState();
    env.addLifeForm(lf, 9, 5);
    env.addLifeForm(lf3, 0, 5);
    lf.pickUpWeapon(pc);
    assertEquals(0, lf.getCurrentDirection());
    assertEquals(40, lf.getCurrentLifePoints());
    assertEquals(40, lf3.getCurrentLifePoints());
//    pass locally with but not gradle test
    assertEquals(9, lf.getRow());
    assertEquals(5, lf.getCol());
    assertEquals(0, lf3.getRow());
    assertEquals(5, lf3.getCol());
    aic.setCurrentState(aic.getHasWeaponState());
    int dir = lf.getCurrentDirection();
    aic.execute();
    assertEquals(40, lf3.getCurrentLifePoints());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
    assertNotEquals(dir, lf.getCurrentDirection());
    assertEquals(40, lf3.getCurrentLifePoints());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testHWIfDead() throws RecoveryRateException {
    env.clearBoard();
    lf.takeHit(40);
    Alien lf3 = new Alien("Bob", 40);
    DeadState test = aic.getDeadState();
    env.addLifeForm(lf, 5, 5);
    env.addLifeForm(lf3, 2, 5);
    lf.pickUpWeapon(p);
    try {
      for (int i = 0; i < 9; i++) {
        lf.getCurrentWeapon().fire(20);
        lf.getCurrentWeapon().updateTime(i);
      }
    } catch (WeaponException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    assertEquals(1, lf.getCurrentWeapon().getCurrentAmmo());
    assertEquals(0, lf.getCurrentDirection());
    assertEquals(0, lf.getCurrentLifePoints());
    assertEquals(40, lf3.getCurrentLifePoints());
//  pass locally with but not gradle test
    assertEquals(5, lf.getRow());
    assertEquals(5, lf.getCol());
    assertEquals(2, lf3.getRow());
    assertEquals(5, lf3.getCol());
    aic.setCurrentState(aic.getHasWeaponState());
    aic.execute();
    assertEquals(40, lf3.getCurrentLifePoints());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testNWStateNoWeaponInCell() throws EnvironmentException {
    NoWeaponState test = new NoWeaponState(aic);
    env.clearBoard();
    env.addLifeForm(lf, 3, 3);
    aic.setCurrentState(aic.getNoWeaponState());
    assertNull(lf.getCurrentWeapon());
    c = env.getCell(lf.getRow(), lf.getCol());
    aic.execute();
    assertNull(lf.getCurrentWeapon());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
    assertNull(c.getLifeForm());
  }
  
  @Test
  public void testNWStateWeaponInCell() throws EnvironmentException {
    HasWeaponState test = new HasWeaponState(aic);
    env.clearBoard();
    env.addLifeForm(lf, 3, 3);
    aic.setCurrentState(aic.getNoWeaponState());
    assertNull(lf.getCurrentWeapon());
    c = env.getCell(3, 3);
    c.addWeapon(p);
    aic.execute();
    assertEquals(p, lf.getCurrentWeapon());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testNWStateDead() throws EnvironmentException {
    DeadState test = new DeadState(aic);
    env.clearBoard();
    env.addLifeForm(lf, 3, 3);
    aic.setCurrentState(aic.getNoWeaponState());
    assertNull(lf.getCurrentWeapon());
    c = env.getCell(3, 3);
    lf.takeHit(lf.getCurrentLifePoints());
    aic.execute();
    assertEquals(0, lf.getCurrentLifePoints());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  
  @Test
  public void testOOAInit() {
    env.clearBoard();
    OutOfAmmoState test = new OutOfAmmoState(aic);
    aic.setCurrentState(aic.getOutOfAmmoState());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testOOAReload() throws WeaponException {
    env.clearBoard();
    
    env.addLifeForm(lf, 3, 3);
    assertNull(lf.getCurrentWeapon());
    
    lf.pickUpWeapon(p);
    aic.setCurrentState(aic.getOutOfAmmoState());
    for (int i = 0; i < p.getMaxAmmo(); i++) {
      p.fire(0);
      p.updateTime(0);
    }
    assertEquals(0, p.getCurrentAmmo());
    aic.execute();
    assertEquals(p, lf.getCurrentWeapon());
    assertEquals(p.getMaxAmmo(), p.getCurrentAmmo());
  }
  
  @Test
  public void testOOAMovesToState() throws EnvironmentException {
    env.clearBoard();
    HasWeaponState test = new HasWeaponState(aic);
    
    env.addLifeForm(lf, 3, 3);
    assertNull(lf.getCurrentWeapon());
    c = env.getCell(3, 3);
    lf.pickUpWeapon(p);
    aic.setCurrentState(aic.getOutOfAmmoState());
    
    aic.execute();
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testOOADead() {
    env.clearBoard();
    DeadState test = new DeadState(aic);
    
    env.addLifeForm(lf, 3, 3);
    aic.setCurrentState(aic.getOutOfAmmoState());
    lf.takeHit(lf.getMaxLifePoints());
    assertEquals(0, lf.getCurrentLifePoints());
    aic.execute();
    
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
    
  }
}
