package commands;

import org.junit.Test;

import environment.Cell;
import environment.Environment;
import exceptions.RecoveryRateException;
import exceptions.WeaponException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.MockLifeForm;
import weapon.ChainGun;
import weapon.MockWeapon;
import weapon.MockWeaponLR;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

import static org.junit.Assert.*;

import java.awt.HeadlessException;


public class TestCommands {
  Environment env = Environment.getEnvironment(10, 10);
  Invoker inv = Invoker.invoker();
  
  /**
   * Test that sets each turn command and executes each
   */
  @Test
  public void testTurnCommands() {
    try {
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    inv.focusedCell = new Cell();
    inv.focusedCell.addLifeForm(entity1);
    assertEquals(0, entity1.getCurrentDirection());
    TurnEastCommand turnEast = new TurnEastCommand();
    turnEast.execute();
    assertEquals(1, entity1.getCurrentDirection());
    TurnNorthCommand turnNorth = new TurnNorthCommand();
    turnNorth.execute();
    assertEquals(0, entity1.getCurrentDirection());
    TurnSouthCommand turnSouth = new TurnSouthCommand();
    turnSouth.execute();
    assertEquals(2, entity1.getCurrentDirection());
    TurnWestCommand turnWest = new TurnWestCommand();
    turnWest.execute();
    assertEquals(3,entity1.getCurrentDirection());
  } catch (HeadlessException e) {
    e.printStackTrace();
  }
  }
  
  /**
   * Tests the getter for the LifeForm's weapon
   * Test that sets the reload command and executes it
   * @throws WeaponException if no weapon
   */
  @Test
  public void testReloadCommand() throws WeaponException {
    try {
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    inv.focusedCell = new Cell();
    inv.focusedCell.addLifeForm(entity1);
    Weapon pistol = new Pistol();
    entity1.pickUpWeapon(pistol);
    Weapon temporary = entity1.getCurrentWeapon();
    assertEquals(pistol, temporary);
    while (pistol.getCurrentAmmo() > 0) {
      pistol.fire(100);
      pistol.updateTime(0);
    }
    assertEquals(0, pistol.getCurrentAmmo());
    ReloadCommand reloadCommand = new ReloadCommand();
    reloadCommand.execute();
    assertEquals(10, pistol.getCurrentAmmo());
    
    entity1.dropWeapon();
    reloadCommand.execute();
  } catch (HeadlessException e) {
    e.printStackTrace();
  }
  }
  
  /**
   * Tests that movement command works and correctly moves a LifeForm
   */
  @Test
  public void testMoveCommand() {
    try {
    env.clearBoard();
    Human entity = new Human("Bob", 40, 0);
    inv.focusedCell = new Cell();
    inv.focusedCell.addLifeForm(entity);
    env.addLifeForm(entity, 2, 7);
    //entity.setLocation(2, 2);
    assertEquals(2, entity.getRow());
    assertEquals(7, entity.getCol());
    MoveCommand moveCommand = new MoveCommand();
    moveCommand.execute();
    entity.updateTime(0);
    assertEquals(0, entity.getRow());
    assertEquals(7, entity.getCol());

    TurnEastCommand turnEast = new TurnEastCommand();
    turnEast.execute();
    moveCommand.execute();
    entity.updateTime(0);
    assertEquals(0, entity.getRow());
    assertEquals(9, entity.getCol());
  } catch (HeadlessException e) {
    e.printStackTrace();
  }
  }
  
  /**
   * Tests that a LifeForm can drop a weapon in a cell correctly
   * Cannot drop if cell is already full
   */
  @Test
  public void testDropCommand() {
    try {
    env.clearBoard();
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    inv.focusedCell = new Cell();
    inv.focusedCell.addLifeForm(entity1);
    Weapon pistol = new Pistol();
    entity1.pickUpWeapon(pistol);
    env.addLifeForm(entity1, 2, 2);
    assertTrue(entity1.hasWeapon());
    DropCommand dropCommand = new DropCommand();
    dropCommand.execute();
    assertFalse(entity1.hasWeapon());
    Weapon[] temp = env.getWeapons(2, 2);
    assertEquals(pistol, temp[0]);
    Weapon chain = new ChainGun();
    env.addWeapon(chain, 2, 2);
    Weapon plasma = new PlasmaCannon();
    entity1.pickUpWeapon(plasma);
    dropCommand.execute();
    assertEquals(plasma, entity1.getCurrentWeapon());
  } catch (HeadlessException e) {
    e.printStackTrace();
  }
  }
  
  @Test
  public void testAcquireCommand() {
    try {
    env.clearBoard();
    MockLifeForm entity1 = new MockLifeForm("Jim", 40, 1);
    inv.focusedCell = new Cell();
    inv.focusedCell.addLifeForm(entity1);
    Weapon pistol = new Pistol();
    
    
    env.addLifeForm(entity1, 2, 2);
    env.addWeapon(pistol, 2, 2);
    Weapon[] temp = env.getWeapons(2, 2);
    assertEquals(pistol, temp[0]);
    
    AcquireCommand acquireCommand = new AcquireCommand();
    assertFalse(entity1.hasWeapon());
    acquireCommand.execute();
    assertTrue(entity1.hasWeapon());
    temp = env.getWeapons(2, 2);
    assertNull(temp[0]);
    
    Weapon chain = new ChainGun();
    env.addWeapon(chain, 2, 2);
    acquireCommand.execute();
    assertEquals(chain, entity1.getCurrentWeapon());
    temp = env.getWeapons(2, 2);
    assertEquals(pistol, temp[0]);
    
    Weapon plasma = new PlasmaCannon();
    env.addWeapon(plasma, 2, 2);
    env.removeWeapon(pistol, 2, 2);
    acquireCommand.execute();
    assertEquals(plasma, entity1.getCurrentWeapon());
    
    env.addWeapon(pistol, 2, 2);
    env.removeWeapon(chain, 2, 2);
    entity1.dropWeapon();
    acquireCommand.execute();
    assertEquals(pistol, entity1.getCurrentWeapon());

    env.removeWeapon(pistol, 2, 2);
    entity1.dropWeapon();
    acquireCommand.execute();
    assertNull(entity1.getCurrentWeapon());
  } catch (HeadlessException e) {
    e.printStackTrace();
  }
  }
  
  @Test
  public void testAttackCommandInRange() throws RecoveryRateException, WeaponException {
    try {
    env.clearBoard();
    Human entity1 = new Human("Jim", 40, 1);
    
    
    inv.focusedCell = new Cell();
    inv.focusedCell.addLifeForm(entity1);
    Pistol pistol = new Pistol();
    entity1.pickUpWeapon(pistol);
    
    env.addLifeForm(entity1, 2, 2);
    
    AttackCommand attackCommand = new AttackCommand();
  
    Alien target1 = new Alien("Zurg", 40);
    env.addLifeForm(target1, 0, 2);
    attackCommand.execute();
    assertEquals(30, target1.getCurrentLifePoints());
    pistol.updateTime(0);
  
    Alien target2 = new Alien("ET", 40);
    env.addLifeForm(target2, 2, 4);
    entity1.setDirection(1);
    attackCommand.execute();
    assertEquals(30, target2.getCurrentLifePoints());
    pistol.updateTime(0);
  
    Alien target3 = new Alien("Yoda", 40);
    env.addLifeForm(target3, 4, 2);
    entity1.setDirection(2);
    attackCommand.execute();
    assertEquals(30, target3.getCurrentLifePoints());
    pistol.updateTime(0);
  
    Alien target4 = new Alien("Grogu", 40);
    env.addLifeForm(target4, 2, 0);
    entity1.setDirection(3);
    attackCommand.execute();
    assertEquals(30, target4.getCurrentLifePoints());
    pistol.updateTime(0);
  } catch (HeadlessException e) {
    e.printStackTrace();
  }
  }
  
  @Test
  public void testAttackCommandOutOfRange() throws RecoveryRateException, WeaponException {
    try {
    env.clearBoard();
    Human entity1 = new Human("Jim", 40, 1);
   
    inv.focusedCell = new Cell();
    inv.focusedCell.addLifeForm(entity1);
    MockWeaponLR mockGun = new MockWeaponLR();
    entity1.pickUpWeapon(mockGun);
    
    env.addLifeForm(entity1, 2, 2);
    Alien target1 = new Alien("Zurg", 40);
    env.addLifeForm(target1, 0, 2);
    Alien target2 = new Alien("ET", 40);
    env.addLifeForm(target2, 2, 4);
    Alien target3 = new Alien("Yoda", 40);
    env.addLifeForm(target3, 4, 2);
    Alien target4 = new Alien("Grogu", 40);
    env.addLifeForm(target4, 2, 0);
    
    AttackCommand attackCommand = new AttackCommand();
    
    attackCommand.execute();
    assertEquals(40, target1.getCurrentLifePoints());
    mockGun.updateTime(0);
    
    entity1.setDirection(1);
    attackCommand.execute();
    assertEquals(40, target2.getCurrentLifePoints());
    mockGun.updateTime(0);
    
    entity1.setDirection(2);
    attackCommand.execute();
    assertEquals(40, target3.getCurrentLifePoints());
    mockGun.updateTime(0);
    
    entity1.setDirection(3);
    attackCommand.execute();
    assertEquals(40, target4.getCurrentLifePoints());
    mockGun.updateTime(0);
  } catch (HeadlessException e) {
    e.printStackTrace();
  }
  }
  
  @Test
  public void testAttackCommandNoTarget() throws WeaponException {
    try {
    env.clearBoard();
    Human entity1 = new Human("Jim", 40, 1);
    inv.focusedCell = new Cell();
    inv.focusedCell.addLifeForm(entity1);
    MockWeapon mockGun = new MockWeapon();
    entity1.pickUpWeapon(mockGun);
    
    env.addLifeForm(entity1, 2, 2);
    
    AttackCommand attackCommand = new AttackCommand();
    
    attackCommand.execute();
    assertEquals(9, mockGun.getCurrentAmmo());
    
    entity1.setDirection(1);
    attackCommand.execute();
    assertEquals(8, mockGun.getCurrentAmmo());
  
    entity1.setDirection(2);
    attackCommand.execute();
    assertEquals(7, mockGun.getCurrentAmmo());
    
    entity1.setDirection(3);
    attackCommand.execute();
    assertEquals(6, mockGun.getCurrentAmmo());
  } catch (HeadlessException e) {
    e.printStackTrace();
  }
  }
  
  @Test
  public void testAttackCommandIntoBorder() throws WeaponException {
    try {
    env.clearBoard();
    Human entity1 = new Human("Jim", 40, 1);
    inv.focusedCell = new Cell();
    inv.focusedCell.addLifeForm(entity1);
    
    MockWeapon mockGun = new MockWeapon();
    AttackCommand attackCommand = new AttackCommand();
    
    entity1.pickUpWeapon(mockGun);
    env.addLifeForm(entity1, 0, 2);
    attackCommand.execute();
    assertEquals(9, mockGun.getCurrentAmmo());
    
    env.addLifeForm(entity1, 2, 4);
    entity1.setDirection(1);
    attackCommand.execute();
    assertEquals(8, mockGun.getCurrentAmmo());
    
    env.addLifeForm(entity1, 4, 2);
    entity1.setDirection(2);
    attackCommand.execute();
    assertEquals(7, mockGun.getCurrentAmmo());
    
    env.addLifeForm(entity1, 2, 0);
    entity1.setDirection(3);
    attackCommand.execute();
    assertEquals(6, mockGun.getCurrentAmmo());
  } catch (HeadlessException e) {
    e.printStackTrace();
  }
  }
}