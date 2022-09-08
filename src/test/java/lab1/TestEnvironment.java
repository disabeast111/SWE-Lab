package lab1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import environment.Environment;
import lifeform.LifeForm;

public class TestEnvironment {

  @Test
  public void testInitialization1(){
    Environment e1 = new Environment(1,1);
    LifeForm entity1 = new LifeForm("Jim", 34);
    assertTrue(e1.addLifeForm(entity1,0,0));
  }
  
  @Test
  public void testInitialization2(){
    Environment e2 = new Environment(2,3);
    LifeForm entity1 = new LifeForm("Jim", 34);
    assertTrue(e2.addLifeForm(entity1,0,0));
  }

  @Test
  public void testAddLifeForm() {
    LifeForm bob = new LifeForm ("Bob", 40);
    LifeForm fred = new LifeForm("Fred", 40);
    Environment e1 = new Environment(3,2);
    boolean success = e1.addLifeForm(bob,2,1);
    assertTrue(success);
    success = e1.addLifeForm(fred,2,1);
    assertFalse(success);
    assertEquals(bob,e1.getLifeForm(2,1));
  }

  @Test
  public void testCheckBorder1() {
    LifeForm bob = new LifeForm ("Bob", 40);
    Environment e1 = new Environment(3,2);
    assertFalse(e1.addLifeForm(bob,5,1));
  }
  
  @Test
  public void testCheckBorder2() {
    LifeForm bob = new LifeForm ("Bob", 40);
    Environment e1 = new Environment(3,2);
    assertFalse(e1.addLifeForm(bob,1,5));
  }
  
  @Test
  public void testCanRemove() {
    Environment e1 = new Environment(3,5);
    LifeForm entity1 = new LifeForm("Jim", 34);
    e1.addLifeForm(entity1,2,3);
    e1.removeLifeForm(2,3);
    assertNull(e1.getLifeForm(2,3));
  }
}
