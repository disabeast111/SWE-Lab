package commands;

import javax.swing.JFrame;

import environment.Cell;
import environment.Environment;
import gameplay.GameBoard;


public class InvokerBuilder extends JFrame {
  Cell focusedCell;
  Command command;
  public Environment e = Environment.getEnvironment(10, 10);
  GameBoard gb = GameBoard.getInstance();
 
  
  public static void main (String[] args) {
    Invoker gui = new Invoker();
    int x = 500;
    int y = 400;
    gui.setBounds(1000, 200, x, y); 
  }
  
  public void setCommand(Command c) {
    command = c;
  }
}
