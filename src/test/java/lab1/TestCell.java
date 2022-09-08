package lab1;

import static org.junit.Assert.*;

import org.junit.Test;
import lifeform.LifeForm;
import environment.Cell;

public class TestCell {

  @Test
  public void testInitialization() {
    Cell cell = new Cell();
    assertNull(cell.getLifeForm());
  }
  
  @Test
  public void testAddLifeForm1() {
    LifeForm bob = new LifeForm ("Bob", 40);
    LifeForm fred = new LifeForm("Fred", 40);
    Cell cell = new Cell();
    boolean success = cell.addLifeForm(bob);
    assertTrue(success);
    success = cell.addLifeForm(fred);
    assertFalse(success);
    assertEquals(bob,cell.getLifeForm());
  }
  
  @Test
  public void testAddLifeForm2() {
    LifeForm jim = new LifeForm ("Jim", 35);
    LifeForm fred = new LifeForm("Fred", 35);
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
    LifeForm entity1 = new LifeForm("Jim", 34);
    LifeForm entity2 = new LifeForm("Bob", 16);
    
    c.addLifeForm(entity1);
    assertFalse(c.addLifeForm(entity2));
    assertEquals("Jim", c.getLifeForm().getName());
  }
  
  @Test
  public void testCanRemove() {
    Cell c = new Cell();
    LifeForm entity1 = new LifeForm("Jim", 34);
    c.addLifeForm(entity1);
    c.removeLifeForm();
    assertNull(c.getLifeForm());
  }
}
