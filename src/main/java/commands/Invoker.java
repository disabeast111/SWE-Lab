package commands;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import environment.Cell;
import environment.Environment;
import exceptions.WeaponException;
import gameplay.GameBoard;

public class Invoker extends JFrame implements ActionListener {
  JButton moveButton;
  JButton northButton;
  JButton eastButton;
  JButton southButton;
  JButton westButton;
  JButton acquireButton; 
  JButton dropButton;
  JButton reloadButton;
  JButton attackButton;
  static Invoker theInv;
  
  
  
  
  
  
  
  public Cell focusedCell = new Cell();
  
  Environment env = Environment.getEnvironment(10, 10);
  Command command;

  
  /**
   * The Invoker GUI
   */
  private Invoker() {
    setLayout(new BorderLayout());

    JPanel weaponPanel = new JPanel(new GridLayout(2, 2));

    reloadButton = new JButton("Reload");
    reloadButton.setActionCommand("ReloadCommand");
    reloadButton.addActionListener(this);
    weaponPanel.add(reloadButton);

    attackButton = new JButton("Fire");
    attackButton.setActionCommand("AttackCommand");
    attackButton.addActionListener(this);
    weaponPanel.add(attackButton);

    dropButton = new JButton("Drop Weapon");
    dropButton.setActionCommand("DropWeapon");
    dropButton.addActionListener(this);
    weaponPanel.add(dropButton);

    acquireButton = new JButton("Acquire Weapon");
    acquireButton.setActionCommand("AcquireCommand");
    acquireButton.addActionListener(this);
    weaponPanel.add(acquireButton);

    add("Center", weaponPanel);

    JPanel turnPanel = new JPanel(new GridLayout(3, 3));
    JButton[][] buttonArray = new JButton[3][3];

    buttonArray[0][0] = new JButton(" ");

    buttonArray[0][1] = new JButton("North");
    buttonArray[0][1].setActionCommand("TurnNorthCommand");
    buttonArray[0][1].addActionListener(this);
    northButton = buttonArray[0][1];

    buttonArray[0][2] = new JButton(" ");

    buttonArray[1][0] = new JButton("West");
    buttonArray[1][0].setActionCommand("TrunWestCommand");
    buttonArray[1][0].addActionListener(this);
    westButton = buttonArray[1][0];

    buttonArray[1][1] = new JButton("Move");
    buttonArray[1][1].setActionCommand("MoveCommand");
    buttonArray[1][1].addActionListener(this);
    moveButton = buttonArray[1][1];

    buttonArray[1][2] = new JButton("East");
    buttonArray[1][2].setActionCommand("TurnEastCommand");
    buttonArray[1][2].addActionListener(this);
    eastButton = buttonArray[1][2];

    buttonArray[2][0] = new JButton(" ");

    buttonArray[2][1] = new JButton("South");
    buttonArray[2][1].setActionCommand("TurnSouthCommand");
    buttonArray[2][1].addActionListener(this);
    southButton = buttonArray[2][1];

    buttonArray[2][2] = new JButton(" ");

    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        turnPanel.add(buttonArray[r][c]);
      }
    }
    add("East", turnPanel);

    setVisible(true);
    setAlwaysOnTop(true);
    
    
  }
  
  public static Invoker invoker() {
    if (theInv == null) {
      theInv = new Invoker();
    }
    return theInv;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    
//    String commandName = event.getActionCommand();
//    Class<?> commandC = null;
//    try {
//      commandC = Class.forName(commandName);
//    } catch (ClassNotFoundException e1) {
//      // TODO Auto-generated catch block
//      e1.printStackTrace();
//    }
//    Command convertedCommand = null;
//    try {
//      convertedCommand = (Command) commandC;
//    } catch (InstantiationException e1) {
//      // TODO Auto-generated catch block
//      e1.printStackTrace();
//    } catch (IllegalAccessException e1) {
//      // TODO Auto-generated catch block
//      e1.printStackTrace();
//    }
//    setCommand(convertedCommand);
   
   
    
    Command acquire = new AcquireCommand();
    Command attack = new AttackCommand();
    Command drop = new DropCommand();
    Command move = new MoveCommand();
    Command reload = new ReloadCommand();
    Command east = new TurnEastCommand();
    Command north = new TurnNorthCommand();
    Command south = new TurnSouthCommand();
    Command west = new TurnWestCommand();
    int prevRow = 0;
    int prevCol = 0;

    
    try {
      if (focusedCell != null && focusedCell.getLifeForm() != null) {
        if (event.getSource() == moveButton) {
          
          prevRow = focusedCell.getLifeForm().getRow();
          prevCol = focusedCell.getLifeForm().getCol();
          move.execute();
        } else if (event.getSource() == reloadButton) {
          reload.execute();
        } else if (event.getSource() == attackButton) {
          attack.execute();
        } else if (event.getSource() == dropButton) {
          drop.execute();
        } else if (event.getSource() == acquireButton) {
          acquire.execute();
        } else if (event.getSource() == northButton) {
          north.execute();
        } else if (event.getSource() == eastButton) {
          east.execute();
        } else if (event.getSource() == southButton) {
          south.execute();
        } else if (event.getSource() == westButton) {
          west.execute();
        }
      }
    } catch (WeaponException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    int row = focusedCell.getLifeForm().getRow();
    int col = focusedCell.getLifeForm().getCol();
    GameBoard.getInstance().updateCell(row, col);
    GameBoard.getInstance().updateStats(row, col);
    
    if (event.getSource() == moveButton) {
      GameBoard.getInstance().updateCell(prevRow, prevCol);
      
    }
  }
  
  public void setCommand(Command c) {
    command = c;
  }
}