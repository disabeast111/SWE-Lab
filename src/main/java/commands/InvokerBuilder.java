package commands;

import javax.swing.JFrame;

import environment.Cell;

public class InvokerBuilder extends JFrame {
  Cell focusedCell;
  Command command;
  
  public InvokerBuilder() {
    initialize();
  }
  
  public void initialize() {
    setTitle("Invoker");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(500, 400);
    setVisible(true);
    
  }
  
  public void setCommand(Command c) {
    command = c;
    command.execute();
    
  }
  
  public void invoke() {
    
  }
  


}
