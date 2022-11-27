package state;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.Pistol;

public class testStates {
  Environment e = Environment.getEnvironment(10,10);
  LifeForm lf = new MockLifeForm("Bob", 40);
  AIContext aic = new AIContext(lf, e);
  Pistol p = new Pistol();
  
//  Useless until kyles part
//  @Test
//  public void testDeadStateNoWeapon() {
//    NoWeaponState test = new NoWeaponState(aic);
//    e.addLifeForm(lf, 2, 4);
//    lf.takeHit(20);
//    assertEquals(20, lf.getCurrentLifePoints());
//    aic.setCurrentState(aic.getDeadState());
//    aic.execute();
//    assertEquals(40, lf.getCurrentLifePoints());
//    assertEquals(test.getClass(), aic.getCurrentState());
//  }
//  
//  @Test
//  public void testDeadStateWeapon() {
//    NoWeaponState test = new NoWeaponState(aic);
//    e.addLifeForm(lf, 2, 4);
//    lf.pickUpWeapon(p);
//    lf.takeHit(20);
//    assertEquals(20, lf.getCurrentLifePoints());
//    aic.setCurrentState(aic.getDeadState());
//    assertEquals(p, lf.getCurrentWeapon());
//    aic.execute();
//    assertEquals(40, lf.getCurrentLifePoints());
//    assertEquals(null, lf.getCurrentWeapon());
//    assertEquals(test.getClass(), aic.getCurrentState());
//  }
}
