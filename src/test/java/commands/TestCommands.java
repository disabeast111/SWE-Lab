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
  Environment e = Environment.getEnvironment(5, 5);
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
    
    entity1.dropWeapon();
    reloadCommand.execute();
   
  }
  

  
  /**
   * Tests that movement command works and correctly moves a LifeForm
   */
  @Test
  public void testMoveCommand() {
    e.clearBoard();
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
  /**
   * Tests that a LifeForm can drop a weapon in a cell correctly
   * Cannot drop if cell is already full
   * @throws WeaponException if attempted
   */
  @Test
  public void testDropCommand() {
    e.clearBoard();
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    Weapon pistol = new Pistol();
    Weapon chain = new ChainGun();
    Weapon plasma = new PlasmaCannon();
    entity1.pickUpWeapon(pistol);
    
    entity1.setLocation(2, 2);
    assertTrue(entity1.hasWeapon());
    DropCommand dropCommand = new DropCommand(entity1, e);
    dropCommand.execute();
    assertFalse(entity1.hasWeapon());
    Weapon[] temp = e.getWeapons(2, 2);
    assertEquals(pistol, temp[0]);
    
    e.addWeapon(chain, 2, 2);
    temp = e.getWeapons(2, 2);
    //System.out.println(temp[0]);
    //System.out.println(temp[1]);
    entity1.pickUpWeapon(plasma);
    dropCommand.execute();
    assertEquals(plasma, entity1.getCurrentWeapon());
    

    
    
  }
  
  @Test
  public void testAcquireCommand() {
    e.clearBoard();
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    
    Weapon pistol = new Pistol();
    Weapon chain = new ChainGun();
    Weapon plasma = new PlasmaCannon();
    entity1.setLocation(2, 2);
    e.addWeapon(pistol, 2, 2);
    Weapon[] temp = e.getWeapons(2, 2);
    assertEquals(pistol, temp[0]);
    
    AcquireCommand acquireCommand = new AcquireCommand(entity1, e);
    assertFalse(entity1.hasWeapon());
    acquireCommand.execute();
    assertTrue(entity1.hasWeapon());
    temp = e.getWeapons(2, 2);
    assertEquals(null, temp[0]);
    
    e.addWeapon(chain, 2, 2);
    acquireCommand.execute();
    assertEquals(chain, entity1.getCurrentWeapon());
    temp = e.getWeapons(2, 2);
    assertEquals(pistol, temp[0]);
    
    e.addWeapon(plasma, 2, 2);
    e.removeWeapon(pistol, 2, 2);
    acquireCommand.execute();
    assertEquals(plasma, entity1.getCurrentWeapon());
    temp = e.getWeapons(2, 2);

    e.addWeapon(pistol, 2, 2);
    e.removeWeapon(chain, 2, 2);
    entity1.dropWeapon();
    acquireCommand.execute();
    assertEquals(pistol, entity1.getCurrentWeapon());


    e.removeWeapon(pistol, 2, 2);
    entity1.dropWeapon();
    acquireCommand.execute();
    assertEquals(null, entity1.getCurrentWeapon());

  }
  
  @Test
  public void testAttackCommand() {
    e.clearBoard();
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    Weapon
    
  }
  
  
}
