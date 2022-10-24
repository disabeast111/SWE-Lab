package lab1;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import lifeform.*;
import weapon.*;

/**
 * @author David W
 * @author Kyle S
 */
public class TestEnvironment {
  /*
   * LAB 5
   */

  // Singleton Environment Instance
  Environment e = Environment.getEnvironment(5, 5);

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
    assertFalse(e.addWeapon(blaster, -5,-5));
    assertFalse(e.addWeapon(blaster, 7, 7));
    
    // Add one to empty cell
    assertTrue(e.addWeapon(blaster, 0, 0));
    assertEquals(blaster, e.getWeapons(0, 0)[0]);
    
    // Fail to add same existing weapon to same cell
    assertFalse(e.addWeapon(blaster, 0, 0));
    
    // Add one to cell with one weapon
    assertTrue(e.addWeapon(handgun, 0,0));
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
    // assertEquals(4, e.getDistance(player, enemy), 0.001);
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
//    assertEquals(4, e.getDistance(player, enemy), 0.001);
  }

  // Get Distance Diagonally
  @Test
  public void testDistanceDiagonal() throws EnvironmentException {
    e.clearBoard();
    MockLifeForm player = new MockLifeForm("Walter White", 10);
    MockLifeForm enemy = new MockLifeForm("Joe Mama", 10);
    e.addLifeForm(player, 0, 3);
    e.addLifeForm(enemy, 0, 4);
    
    // Return distance using literal coordinates
    assertEquals(25, e.getDistance(0, 0, 3, 4), 0.001);
    assertEquals(25, e.getDistance(0, 0, 4, 3), 0.001);
    assertEquals(25, e.getDistance(3, 4, 0, 0), 0.001);
    assertEquals(25, e.getDistance(4, 3, 0, 0), 0.001);
    // Return distance using LifeForms
//    assertEquals(5, e.getDistance(player, enemy), 0.001);
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
//
//  @Test
//  public void testAddLifeForm() {
//    MockLifeForm bob = new MockLifeForm("Bob", 40);
//    MockLifeForm fred = new MockLifeForm("Fred", 40);
//    Environment e1 = Environment.getEnvironment(3, 2);
//    boolean success = e1.addLifeForm(bob, 2, 1);
//    assertTrue(success);
//    success = e1.addLifeForm(fred, 2, 1);
//    assertFalse(success);
//    assertEquals(bob, e1.getLifeForm(2, 1));
//  }
//
//  @Test
//  public void testCheckBorder1() {
//    MockLifeForm bob = new MockLifeForm("Bob", 40);
//    Environment e1 = Environment.getEnvironment(3, 2);
//    assertFalse(e1.addLifeForm(bob, 5, 1));
//  }
//
//  @Test
//  public void testCheckBorder2() {
//    MockLifeForm bob = new MockLifeForm("Bob", 40);
//    Environment e1 = Environment.getEnvironment(3, 2);
//    assertFalse(e1.addLifeForm(bob, 1, 5));
//  }
//
//  @Test
//  public void testCanRemove() {
//    Environment e1 = Environment.getEnvironment(1, 1);
//    MockLifeForm entity1 = new MockLifeForm("Jim", 34);
//    e1.addLifeForm(entity1, 0, 0);
//    e1.removeLifeForm(0, 0);
//    assertNull(e1.getLifeForm(0, 0));
//  }
} // DO NOT REMOVE OR COMMENT OUT
