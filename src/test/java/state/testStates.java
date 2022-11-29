package state;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.RecoveryRateException;
import exceptions.WeaponException;
import lifeform.*;
import recovery.RecoveryNone;
import weapon.Pistol;

public class testStates {
  Environment e = Environment.getEnvironment(10,10);
  Human lf = new Human("Bob", 40, 0);
  Human lf2 = new Human("Bob", 40, 0);
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
}
