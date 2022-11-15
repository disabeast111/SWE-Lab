package environment;

import static org.junit.Assert.*;

import org.junit.Test;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import lifeform.*;
import weapon.*;

/**
 * Labs 4, 5, 6
 * 
 * @author David W Lab 5
 * @author Kyle S
 */
public class TestEnvironment {

  // Singleton Environment Instance
  Environment e = Environment.getEnvironment(5, 5);

  /*
   * LAB 6
   */

  // test move with boarders
  @Test
  public void moveTurnWBoarders() throws EnvironmentException {
    Human entity = new Human("Bob", 40, 0);
    entity.setLocation(2, 2);
    assertEquals(2, entity.getRow());
    assertEquals(2, entity.getCol());
    e.move(entity);
    entity.updateTime(0);
    assertEquals(0, entity.getRow());
    assertEquals(2, entity.getCol());
    e.move(entity);
    entity.updateTime(0);
    assertEquals(0, entity.getRow());
    assertEquals(2, entity.getCol());
    entity.setDirection(1);
    e.move(entity);
    entity.updateTime(0);
    assertEquals(0, entity.getRow());
    assertEquals(4, entity.getCol());
    entity.setDirection(2);
    e.move(entity);
    entity.updateTime(0);
    assertEquals(3, entity.getRow());
    assertEquals(4, entity.getCol());
    entity.setDirection(2);
    e.move(entity);
    entity.updateTime(0);
    assertEquals(4, entity.getRow());
    assertEquals(4, entity.getCol());
    entity.setDirection(3);
    e.move(entity);
    entity.updateTime(0);
    assertEquals(4, entity.getRow());
    assertEquals(1, entity.getCol());
    e.move(entity);
    entity.updateTime(0);
    assertEquals(4, entity.getRow());
    assertEquals(0, entity.getCol());
  }

  //test moveSpeed
  @Test
  public void testMovesPerRound() throws EnvironmentException {
    Human entity = new Human("Bob", 40, 0);
    e.addLifeForm(entity, 0, 0);
    assertEquals(0, entity.getRow());
    assertEquals(0, entity.getCol());
    e.move(entity);
    assertEquals(0, entity.getRow());
    assertEquals(0, entity.getCol());
    assertEquals(3, entity.getMovesLeft());
    entity.setDirection(3);
    e.move(entity);
    assertEquals(0, entity.getRow());
    assertEquals(0, entity.getCol());
    assertEquals(0, entity.getMovesLeft());
    entity.setDirection(1);
    e.move(entity);
    assertEquals(0, entity.getRow());
    assertEquals(1, entity.getCol());
    assertEquals(0, entity.getMovesLeft());
  }
  
  //testBoards
  @Test
  public void testMoveAtBoard() throws EnvironmentException {
    Human entity = new Human("Bob", 40, 0);
    entity.setLocation(1, 1);
    assertEquals(1, entity.getRow());
    assertEquals(1, entity.getCol());
    e.move(entity);
    assertEquals(0, entity.getRow());
    assertEquals(1, entity.getCol());
    assertEquals(2, entity.getMovesLeft());
    entity.setDirection(3);
    e.move(entity);
    assertEquals(0, entity.getRow());
    assertEquals(0, entity.getCol());
    assertEquals(1, entity.getMovesLeft());
    entity.setDirection(1);
    e.move(entity);
    assertEquals(0, entity.getRow());
    assertEquals(1, entity.getCol());
    assertEquals(0, entity.getMovesLeft());
  }

  /*
   * LAB 5
   */

  // Environment Singleton Initialization
  @Test
  public void testEnvironmentInit() {
    Environment e2 = Environment.getEnvironment(69, 420);
    assertEquals(e, e2);
    assertEquals(5, e2.getNumRows());
    assertEquals(5, e2.getNumCols());
  }

  // Add Weapon to a Location
  @Test
  public void testAddWeapon() throws AttachmentException {
    Weapon blaster = new PowerBooster(new Stabilizer(new PlasmaCannon()));
    Weapon handgun = new Stabilizer(new Pistol());

    // Fail to add out of bounds
    assertFalse(e.addWeapon(blaster, -5, -5));
    assertFalse(e.addWeapon(blaster, 7, 7));

    // Add one to empty cell
    assertTrue(e.addWeapon(blaster, 0, 0));
    assertEquals(blaster, e.getWeapons(0, 0)[0]);

    // Fail to add same existing weapon to same cell
    assertFalse(e.addWeapon(blaster, 0, 0));

    // Add one to cell with one weapon
    assertTrue(e.addWeapon(handgun, 0, 0));
    assertEquals(handgun, e.getWeapons(0, 0)[1]);

    // Fail to add to full cell
    assertFalse(e.addWeapon(new ChainGun(), 0, 0));
  }

  // Remove Weapon from a Location
  @Test
  public void testRemoveWeapon() {
    e.clearBoard();
    Weapon cannon = new PlasmaCannon();
    e.addWeapon(cannon, 0, 0);

    // Return null for out of range
    assertNull(e.removeWeapon(cannon, -1, -1));

    // Return null for weapon not in cell
    assertNull(e.removeWeapon(new Pistol(), 0, 0));

    // Return weapon removed
    assertEquals(cannon, e.removeWeapon(cannon, 0, 0));
    assertNull(e.getWeapons(0, 0)[0]);
  }

  // Get Distance Along Same Row
  @Test
  public void testDistanceHorizontal() throws EnvironmentException {
    e.clearBoard();
    MockLifeForm player = new MockLifeForm("Walter White", 10);
    MockLifeForm enemy = new MockLifeForm("Joe Mama", 10);
    e.addLifeForm(player, 0, 0);
    e.addLifeForm(enemy, 0, 4);

    // Return distance using literal coordinates
    assertEquals(20, e.getDistance(0, 0, 0, 4), 0.001);

    // Return distance using LifeForms
    assertEquals(20, e.getDistance(player, enemy), 0.001);
  }

  // Get Distance Along Same Column
  @Test
  public void testDistanceVertical() throws EnvironmentException {
    e.clearBoard();
    MockLifeForm player = new MockLifeForm("Walter White", 10);
    MockLifeForm enemy = new MockLifeForm("Joe Mama", 10);
    e.addLifeForm(player, 0, 0);
    e.addLifeForm(enemy, 4, 0);

    // Return distance using literal coordinates
    assertEquals(20, e.getDistance(0, 0, 4, 0), 0.001);

    // Return distance using LifeForms
    assertEquals(20, e.getDistance(player, enemy), 0.001);
  }

  // Get Distance Diagonally
  @Test
  public void testDistanceDiagonal() throws EnvironmentException {
    e.clearBoard();
    MockLifeForm player = new MockLifeForm("Walter White", 10);
    MockLifeForm enemy = new MockLifeForm("Joe Mama", 10);
    e.addLifeForm(player, 0, 3);
    e.addLifeForm(enemy, 4, 0);

    // Return distance using literal coordinates
    assertEquals(25, e.getDistance(0, 0, 3, 4), 0.001);
    assertEquals(25, e.getDistance(0, 0, 4, 3), 0.001);
    assertEquals(25, e.getDistance(3, 4, 0, 0), 0.001);
    assertEquals(25, e.getDistance(4, 3, 0, 0), 0.001);

    // Return distance using LifeForms
    assertEquals(25, e.getDistance(player, enemy), 0.001);
  }

  /*
   * LAB 1-4
   */

//  @Test
//  public void testInitialization1() {
//    Environment e1 = Environment.getEnvironment(1, 1);
//    MockLifeForm entity1 = new MockLifeForm("Jim", 34);
//    assertTrue(e1.addLifeForm(entity1, 0, 0));
//  }
//
//  @Test
//  public void testInitialization2() {
//    Environment e2 = Environment.getEnvironment(2, 3);
//    MockLifeForm entity1 = new MockLifeForm("Jim", 34);
//    assertTrue(e2.addLifeForm(entity1, 0, 0));
//  }

  @Test
  public void testAddLifeForm() {
    e.clearBoard();
    MockLifeForm bob = new MockLifeForm("Bob", 40);
    MockLifeForm fred = new MockLifeForm("Fred", 40);
    assertTrue(e.addLifeForm(bob, 2, 1));
    assertFalse(e.addLifeForm(fred, 2, 1));
    assertEquals(bob, e.getLifeForm(2, 1));
    assertEquals(2, bob.getRow());
    assertEquals(1, bob.getCol());
  }

  @Test
  public void testCheckBorder1() {
    MockLifeForm bob = new MockLifeForm("Bob", 40);
    assertFalse(e.addLifeForm(bob, 5, 1));
  }

  @Test
  public void testCheckBorder2() {
    MockLifeForm bob = new MockLifeForm("Bob", 40);
    Environment e = Environment.getEnvironment(3, 2);
    assertFalse(e.addLifeForm(bob, 1, 5));
  }

  @Test
  public void testCanRemove() {
    MockLifeForm entity1 = new MockLifeForm("Jim", 34);
    e.addLifeForm(entity1, 0, 0);
    e.removeLifeForm(0, 0);
    assertNull(e.getLifeForm(0, 0));
  }
}
