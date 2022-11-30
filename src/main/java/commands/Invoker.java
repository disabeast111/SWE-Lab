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
    
    reloadButton = new JButton("Reload");
    reloadButton.setActionCommand("ReloadCommand");
    reloadButton.addActionListener(this);
    
    attackButton = new JButton("Fire");
    attackButton.setActionCommand("AttackCommand");
    attackButton.addActionListener(this);
    
    dropButton = new JButton("Drop Weapon");
    dropButton.setActionCommand("DropCommand");
    dropButton.addActionListener(this);
    
    acquireButton = new JButton("Acquire Weapon");
    acquireButton.setActionCommand("AcquireCommand");
    acquireButton.addActionListener(this);
    
    JPanel weaponPanel = new JPanel(new GridLayout(2, 2));
    weaponPanel.add(reloadButton);
    weaponPanel.add(attackButton);
    weaponPanel.add(dropButton);
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
    buttonArray[1][0].setActionCommand("TurnWestCommand");
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
  
  /**
   * The Singleton instance of Invoker
   */
  public static Invoker invoker() {
    if (theInv == null) {
      theInv = new Invoker();
    }
    return theInv;
  }
  
  @Override
  public void actionPerformed(ActionEvent event) {
    int prevRow = 0;
    int prevCol = 0;
    
    setCommand(event.getActionCommand());
    
    try {
      if (focusedCell != null && focusedCell.getLifeForm() != null) {
        if (event.getSource() == moveButton) {
          
          prevRow = focusedCell.getLifeForm().getRow();
          prevCol = focusedCell.getLifeForm().getCol();
          
        }
        command.execute();
        
      }
    } catch (WeaponException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    int row = focusedCell.getLifeForm().getRow();
    int col = focusedCell.getLifeForm().getCol();
    GameBoard.getInstance().updateCell(row, col);
    GameBoard.getInstance().updateStats();
    
    if (event.getSource() == moveButton) {
      GameBoard.getInstance().updateCell(prevRow, prevCol);
      
    }
  }
  
  /**
   * Sets each command based on the button pushed
   * @param s the string from action listener
   */
  public void setCommand(String s) {
    InvokerBuilder ib = new InvokerBuilder();
    if (s.equals(ib.acquire.toString())) {
      command = ib.acquire;
    } else if (s.equals(ib.attack.toString())) {
      command = ib.attack;
    } else if (s.equals(ib.drop.toString())) {
      command = ib.drop;
    } else if (s.equals(ib.move.toString())) {
      command = ib.move;
    } else if (s.equals(ib.reload.toString())) {
      command = ib.reload;
    } else if (s.equals(ib.east.toString())) {
      command = ib.east;
    } else if (s.equals(ib.north.toString())) {
      command = ib.north;
    } else if (s.equals(ib.south.toString())) {
      command = ib.south;
    } else if (s.equals(ib.west.toString())) {
      command = ib.west;
    }
    
  }
  
}