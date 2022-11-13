package commands;

import javax.swing.JFrame;

import environment.Cell;

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
  
  public void setCommand(Command c) {
    
    command = c;
    
    
  }
  
  public void invoke() {
    
  }
  


}
