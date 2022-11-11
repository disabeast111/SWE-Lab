package commands;

import environment.Cell;

public class InvokerBuilder {
  Cell focusedCell;
  Command command;
  
  public void setCommand(Command c) {
    command = c;
    command.execute();
    
  }
  
  public void invoke() {
    
  }

}
