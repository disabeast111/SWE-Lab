package lab1;

import static org.junit.Assert.*;

import org.junit.Test;
import environment.Cell;
import exceptions.RecoveryRateException;
import lifeform.Alien;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

/**
 * @author David W, Spencer H
 */
public class TestCell {

  @Test
  public void testInitialization5() throws RecoveryRateException {
    Cell c = new Cell();
    LifeForm l = new Alien("Bob", 1);
    
    assertTrue(c.addLifeForm(l));
    assertEquals(l, c.getLifeForm());
    assertEquals(0, c.getWeaponsCount());
    assertNull(c.getWeapon1());
    assertNull(c.getWeapon2());
  }
  
  @Test
  public void testAddWeapons() throws RecoveryRateException {
    Cell c = new Cell();
    Weapon p = new Pistol();
    Weapon pc = new PlasmaCannon();
    
    assertTrue(c.addWeapon(p));
    assertTrue(c.addWeapon(pc));
    assertEquals(2, c.getWeaponsCount());
    assertFalse(c.addWeapon(p));
    assertEquals(2, c.getWeaponsCount());
    assertEquals(p, c.getWeapon1());
    assertEquals(pc, c.getWeapon2());
  }
  
  @Test 
  public void removeWeapons() {
    Cell c = new Cell();
    Weapon p = new Pistol();
    Weapon pc = new PlasmaCannon();
    
    c.addWeapon(p);
    c.addWeapon(pc);
    assertEquals(2, c.getWeaponsCount());
    
    assertEquals(p, c.removeWeapon(p));
    assertEquals(pc, c.removeWeapon(pc));
    assertEquals(null, c.removeWeapon(p));
    assertEquals(0, c.getWeaponsCount());
  }
   
  @Test
  public void testFullCellNoNewWeap() throws RecoveryRateException {
    Cell c = new Cell();
    Weapon p = new Pistol();
    Weapon pc = new PlasmaCannon();
    
    assertTrue(c.addWeapon(p));
    assertTrue(c.addWeapon(pc));
    assertFalse(c.addWeapon(p));
  }
  
  @Test 
  public void remove1AndAdd() {
    Cell c = new Cell();
    Weapon p = new Pistol();
    Weapon pc = new PlasmaCannon();
    
    c.addWeapon(p);
    c.addWeapon(pc);
    assertEquals(2, c.getWeaponsCount());
    //remove one
    assertEquals(p, c.removeWeapon(p));
    assertEquals(null, c.removeWeapon(p));
    assertEquals(1, c.getWeaponsCount());
    //then add
    assertTrue(c.addWeapon(p));
    
  }
  
  
  
  //Begin Previous Tests
  @Test
  public void testInitialization() {
    Cell cell = new Cell();
    assertNull(cell.getLifeForm());
  }
  
  @Test
  public void testAddLifeForm1() {
    MockLifeForm bob = new MockLifeForm("Bob", 40);
    MockLifeForm fred = new MockLifeForm("Fred", 40);
    Cell cell = new Cell();
    boolean success = cell.addLifeForm(bob);
    assertTrue(success);
    success = cell.addLifeForm(fred);
    assertFalse(success);
    assertEquals(bob,cell.getLifeForm());
  }
  
  @Test
  public void testAddLifeForm2() {
    MockLifeForm jim = new MockLifeForm("Jim", 35);
    MockLifeForm fred = new MockLifeForm("Fred", 35);
    Cell cell = new Cell();
    boolean success = cell.addLifeForm(jim);
    assertTrue(success);
    success = cell.addLifeForm(fred);
    assertFalse(success);
    assertEquals(jim,cell.getLifeForm());
  }
  
  @Test
  public void testCantAdd() {
    Cell c = new Cell();
    MockLifeForm entity1 = new MockLifeForm("Jim", 34);
    MockLifeForm entity2 = new MockLifeForm("Bob", 16);
    
    c.addLifeForm(entity1);
    assertFalse(c.addLifeForm(entity2));
    assertEquals("Jim", c.getLifeForm().getName());
  }
  
  @Test
  public void testCanRemove() {
    Cell c = new Cell();
    MockLifeForm entity1 = new MockLifeForm("Jim", 34);
    c.addLifeForm(entity1);
    c.removeLifeForm();
    assertNull(c.getLifeForm());
  }
}
