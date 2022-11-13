package commands;

import static org.junit.Assert.*;

import org.junit.Test;

import lifeform.MockLifeForm;


public class TestCommands {

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

}
