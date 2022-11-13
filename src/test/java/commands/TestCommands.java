package commands;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.WeaponException;
import lifeform.Human;
import lifeform.MockLifeForm;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
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
  public void testNoWeaponReloadCommand() throws WeaponException {
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
  
  /**
   * Tests that movement command works and correctly moves a LifeForm
   */
  @Test
  public void testMoveCommand() {
    Environment e = Environment.getEnvironment(5, 5);
    Human entity = new Human("Bob", 40, 0);
    entity.setLocation(2, 2);
    assertEquals(2, entity.getRow());
    assertEquals(2, entity.getCol());
    MoveCommand moveCommand = new MoveCommand(entity, e);
    moveCommand.execute();
    entity.updateTime(0);
    assertEquals(0, entity.getRow());
    assertEquals(2, entity.getCol());

    TurnEastCommand turnEast = new TurnEastCommand(entity);
    turnEast.execute();
    moveCommand.execute();
    entity.updateTime(0);
    assertEquals(0, entity.getRow());
    assertEquals(4, entity.getCol());
    
  }
  
  @Test
  public void testDropCommand() throws WeaponException {
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    Weapon pistol = new Pistol();
    entity1.pickUpWeapon(pistol);
    Environment e = Environment.getEnvironment(5, 5);
    entity1.setLocation(2, 2);
    assertTrue(entity1.hasWeapon());
    DropCommand dropCommand = new DropCommand(entity1, e);
    dropCommand.execute();
    assertFalse(entity1.hasWeapon());
    Weapon[] temp = e.getWeapons(2, 2);
    assertEquals(pistol, temp[0]);
    
    Weapon pistol2 = new ChainGun();
    Weapon pistol3 = new PlasmaCannon();
    e.addWeapon(pistol2, 2, 2);
    temp = e.getWeapons(2, 2);
    //System.out.println(temp[0]);
    //System.out.println(temp[1]);
    entity1.pickUpWeapon(pistol3);
    boolean caught = false;
    try {
      dropCommand.execute();
    }
    catch(WeaponException f) {
      caught = true;
    }
    assertTrue(caught);
    
    
  }
}
