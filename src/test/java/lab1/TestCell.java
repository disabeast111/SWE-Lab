package lab1;

import static org.junit.Assert.*;

import org.junit.Test;
import environment.Cell;
import lifeform.MockLifeForm;

public class TestCell {

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
