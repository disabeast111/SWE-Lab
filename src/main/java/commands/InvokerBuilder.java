package commands;

import javax.swing.JFrame;

import environment.Cell;
import exceptions.WeaponException;

public class InvokerBuilder extends JFrame {
  Cell focusedCell;
  Command command;
  
  public InvokerBuilder() {
    
  }
  
  public static void main (String[] args) {
    Invoker gui = new Invoker();
    int x = 500;
    int y = 400;
    gui.setBounds(1000, 200, x, y); 
  }
  
  public void setCommand(Command c) throws WeaponException {
    
    command = c;
    
    
  }
  
  public void invoke() {
    
  }
  


}