package commands;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.WeaponException;
import lifeform.MockLifeForm;
import weapon.Pistol;
import weapon.Weapon;


public class TestCommands {

  /**
   * Test that sets each turn command and executes each
   */
  @Test
  public void testTurnCommands() {
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    assertEquals(0, entity1.getCurrentDirection());
    TurnEastCommand turnEast = new TurnEastCommand(entity1);
    TurnNorthCommand turnNorth = new TurnNorthCommand(entity1);
    TurnSouthCommand turnSouth = new TurnSouthCommand(entity1);
    TurnWestCommand turnWest = new TurnWestCommand(entity1);
    turnEast.execute();
    assertEquals(1, entity1.getCurrentDirection());
    turnNorth.execute();
    assertEquals(0, entity1.getCurrentDirection());
    turnSouth.execute();
    assertEquals(2, entity1.getCurrentDirection());
    turnWest.execute();
    assertEquals(3,entity1.getCurrentDirection());
    
  }
  /**
   * Tests the getter for the LifeForm's weapon
   * Test that sets the reload command and executes it
   * @throws WeaponException
   */
  @Test
  public void testReloadCommand() throws WeaponException {
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    Weapon pistol = new Pistol();
    entity1.pickUpWeapon(pistol);
    Weapon temporary = entity1.getCurrentWeapon();
    assertEquals(pistol, temporary);
    while (pistol.getCurrentAmmo() > 0) {
      pistol.fire(100);
      pistol.updateTime(0);
    }
    assertEquals(0, pistol.getCurrentAmmo());
    ReloadCommand reloadCommand = new ReloadCommand(entity1);
    reloadCommand.execute();
    assertEquals(10, pistol.getCurrentAmmo());
   
  }
  
  /**
   * Tests that nothing happens when you try reloading nothing
   * @throws WeaponException when attempted
   */
  @Test
  public void testNoWeaponReloaddCommand() throws WeaponException {
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    ReloadCommand reloadCommand = new ReloadCommand(entity1);
    boolean caught = false;
    try {
      reloadCommand.execute();
    }
    catch(WeaponException e) {
      caught = true;
    }
    assertTrue(caught);
    
   
  }
}
