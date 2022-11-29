package state;

import static org.junit.Assert.*;


import org.junit.Test;

import environment.Cell;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import environment.Environment;
import exceptions.RecoveryRateException;
import lifeform.*;
import weapon.Pistol;

public class TestStates {
  Environment e = Environment.getEnvironment(10,10);
  Human lf = new Human("Bob", 40, 0);
  Human lf2 = new Human("Bob", 40, 0);
  AIContext aic = new AIContext(lf, e);
  Pistol p = new Pistol();
  Cell c = new Cell();
  
  
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
    assertNull(lf.getCurrentWeapon());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testHWNoTarget() {
    e.clearBoard();
    DeadState test = aic.getDeadState();
    e.addLifeForm(lf, 5, 5);
    lf.pickUpWeapon(p);
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
    lf.pickUpWeapon(p);
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
  public void testHWDifferType() throws RecoveryRateException {
    e.clearBoard();
    Alien lf3 = new Alien("Bob", 40);
    HasWeaponState test = aic.getHasWeaponState();
    e.addLifeForm(lf, 5, 5);
    e.addLifeForm(lf3, 2, 5);
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
    e.clearBoard();
    Alien lf3 = new Alien("Bob", 40);
    OutOfAmmoState test = aic.getOutOfAmmoState();
    e.addLifeForm(lf, 5, 5);
    e.addLifeForm(lf3, 2, 5);
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
    e.clearBoard();
    Alien lf3 = new Alien("Bob", 40);
    DeadState test = aic.getDeadState();
    e.addLifeForm(lf, 9, 5);
    e.addLifeForm(lf3, 0, 5);
    lf.pickUpWeapon(p);
    assertEquals(0, lf.getCurrentDirection());
    assertEquals(40, lf.getCurrentLifePoints());
    assertEquals(40, lf3.getCurrentLifePoints());
    assertEquals(9, lf.getRow());
    assertEquals(5, lf.getCol());
    assertEquals(0, lf3.getRow());
    assertEquals(5, lf3.getCol());
    aic.setCurrentState(aic.getHasWeaponState());
    aic.execute();
    assertEquals(40, lf3.getCurrentLifePoints());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }

  @Test
  public void testHWIfDead() throws RecoveryRateException {
    e.clearBoard();
    lf.takeHit(40);
    Alien lf3 = new Alien("Bob", 40);
    DeadState test = aic.getDeadState();
    e.addLifeForm(lf, 5, 5);
    e.addLifeForm(lf3, 2, 5);
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
    e.clearBoard();
    e.addLifeForm(lf, 3, 3);
    aic.setCurrentState(aic.getNoWeaponState());
    assertNull(lf.getCurrentWeapon());
    c = e.getCell(lf.getRow(), lf.getCol());
    aic.execute();
    assertNull(lf.getCurrentWeapon());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  @Test
  public void testNWStateWeaponInCell() throws EnvironmentException {
    HasWeaponState test = new HasWeaponState(aic);
    e.clearBoard();
    e.addLifeForm(lf, 3, 3);
    aic.setCurrentState(aic.getNoWeaponState());
    assertNull(lf.getCurrentWeapon());
    c = e.getCell(lf.getRow(), lf.getCol());
    c.addWeapon(p);
    aic.execute();
    assertEquals(p, lf.getCurrentWeapon());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  @Test
  public void testNWStateDead() throws EnvironmentException {
    DeadState test = new DeadState(aic);
    e.clearBoard();
    e.addLifeForm(lf, 3, 3);
    aic.setCurrentState(aic.getNoWeaponState());
    assertNull(lf.getCurrentWeapon());
    c = e.getCell(lf.getRow(), lf.getCol());
    lf.takeHit(lf.getCurrentLifePoints());
    aic.execute();
    assertEquals(0, lf.getCurrentLifePoints());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  
  @Test
  public void testOOAInit() {
    e.clearBoard();
    OutOfAmmoState test = new OutOfAmmoState(aic);
    aic.setCurrentState(aic.getOutOfAmmoState());
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testOOAreload() throws WeaponException {
    e.clearBoard();
   
    e.addLifeForm(lf, 3, 3);
    assertNull(lf.getCurrentWeapon());

    lf.pickUpWeapon(p);
    aic.setCurrentState(aic.getOutOfAmmoState());
    for(int i = 0; i < p.getMaxAmmo(); i++){
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
    e.clearBoard();
    HasWeaponState test = new HasWeaponState(aic);
   
    e.addLifeForm(lf, 3, 3);
    assertNull(lf.getCurrentWeapon());
    c = e.getCell(lf.getRow(), lf.getCol());
    lf.pickUpWeapon(p);
    aic.setCurrentState(aic.getOutOfAmmoState());

    aic.execute();
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  }
  
  @Test
  public void testOOADead() {
    e.clearBoard();
    DeadState test = new DeadState(aic);
   
    e.addLifeForm(lf, 3, 3);
    aic.setCurrentState(aic.getOutOfAmmoState());
    lf.takeHit(lf.getMaxLifePoints());
    assertEquals(0, lf.getCurrentLifePoints());
    aic.execute();
 
    assertEquals(test.getClass(), aic.getCurrentState().getClass());
  
  }
}
