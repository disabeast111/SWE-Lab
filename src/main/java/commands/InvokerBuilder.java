package commands;

import javax.swing.JFrame;
import environment.Cell;

public class InvokerBuilder extends JFrame {
  Cell focusedCell;
  Command command;
  Command acquire = new AcquireCommand();
  Command attack = new AttackCommand();
  Command drop = new DropCommand();
  Command move = new MoveCommand();
  Command reload = new ReloadCommand();
  Command east = new TurnEastCommand();
  Command north = new TurnNorthCommand();
  Command south = new TurnSouthCommand();
  Command west = new TurnWestCommand();
}