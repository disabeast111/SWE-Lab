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
import weapon.PlasmaCannon;
import weapon.Scope;
import weapon.Weapon;


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
  
  
 
  /**
   * Main method to build everything
   * @param args
   * @throws AttachmentException 
   * @throws RecoveryRateException
   */
  public static void main(String[] args) throws AttachmentException, RecoveryRateException {
    Environment env = Environment.getEnvironment(10, 10);
    Invoker gui = Invoker.invoker();
    int x = 500;
    int y = 400;
    gui.setBounds(1000, 200, x, y);
    LifeForm joe = new Human("Joe", 10, 10);
    joe.pickUpWeapon(new Scope(new Pistol()));
    LifeForm jane = new Human("Jane", 10, 10);
    LifeForm Lrrr = new Alien("Lrrr", 50);
    env.addLifeForm(Lrrr, 7, 3);
    env.addLifeForm(joe, 0,0);
    env.addLifeForm(jane, 3, 6);
    Weapon weap = new Scope(new PlasmaCannon());
        
    try {
      env.getCell(3, 5).addWeapon(weap);
      env.getCell(2, 8).addWeapon(weap);
    } catch (EnvironmentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.out.println("Could not add weapon");
    }
    GameBoard gb = GameBoard.getInstance();
    for (int row = 0; row < 10; row++) {
      for (int col = 0; col < 10; col++) {
        gb.updateCell(row, col);
      }
    }
  
  }
  



}
