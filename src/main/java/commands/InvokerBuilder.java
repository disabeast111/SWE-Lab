package commands;

import javax.swing.JFrame;

import environment.Cell;
import environment.Environment;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import gameplay.GameBoard;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import recovery.RecoveryLinear;
import weapon.Pistol;
import weapon.Scope;
import weapon.Weapon;


public class InvokerBuilder extends JFrame {
  Cell focusedCell;
  Command command;
  
  
 
  /**
   * Main method to build everything
   * @param args
   * @throws AttachmentException 
   * @throws RecoveryRateException
   */
  public static void main(String[] args) throws AttachmentException, RecoveryRateException {
    Environment env = Environment.getEnvironment(10, 10);
    Invoker gui = new Invoker();
    int x = 500;
    int y = 400;
    gui.setBounds(1000, 200, x, y);
    LifeForm joe = new Human("Joe", 10, 10);
    joe.pickUpWeapon(new Scope(new Pistol()));
    LifeForm jane = new Alien("Jane", 10, new RecoveryLinear(5));
    env.addLifeForm(joe, 0,0);
    env.addLifeForm(jane, 3, 6);
    Weapon weap = new Scope(new Pistol());
        
    try {
      env.getCell(3, 5).addWeapon(weap);
    } catch (EnvironmentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    GameBoard.getInstance();
  }
  
  public void setCommand(Command c) {
    command = c;
  }
}
