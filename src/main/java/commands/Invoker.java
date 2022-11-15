package commands;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import environment.Environment;
import exceptions.WeaponException;
import gameplay.GameBoard;

public class Invoker extends JFrame implements ActionListener {
  JButton moveButton, northButton, eastButton, southButton, 
    westButton, acquireButton, dropButton, reloadButton, attackButton;
  JLabel errorLabel;
  
  
  Environment env = Environment.getEnvironment(10, 10);
  GameBoard gameb = GameBoard.getInstance();
  
  public Invoker() {
    setLayout(new BorderLayout());
    

    JPanel weaponPanel = new JPanel(new GridLayout(2, 2));
    
    reloadButton = new JButton("Reload");
    reloadButton.addActionListener(this);
    weaponPanel.add(reloadButton);
    
    attackButton = new JButton("Fire");
    attackButton.addActionListener(this);
    weaponPanel.add(attackButton);
    
    dropButton = new JButton("Drop Weapon");
    dropButton.addActionListener(this);
    weaponPanel.add(dropButton);
    
    acquireButton = new JButton("Acquire Weapon");
    acquireButton.addActionListener(this);
    weaponPanel.add(acquireButton);
    
    
    add("Center", weaponPanel);
    
    
    JPanel turnPanel = new JPanel(new GridLayout(3, 3));
    JButton[][] buttonArray = new JButton[3][3];
  
    buttonArray[0][0] = new JButton(" ");
    
    buttonArray[0][1] = new JButton("North");
    buttonArray[0][1].setActionCommand("North");
    buttonArray[0][1].addActionListener(this);
    northButton = buttonArray[0][1];
    
    buttonArray[0][2] = new JButton(" ");
    
    buttonArray[1][0] = new JButton("West");
    buttonArray[1][0].setActionCommand("West");
    buttonArray[1][0].addActionListener(this);
    westButton = buttonArray[1][0];
    
    buttonArray[1][1] = new JButton("Move");
    buttonArray[1][1].setActionCommand("Move");
    buttonArray[1][1].addActionListener(this);
    moveButton = buttonArray[1][1];
     
    buttonArray[1][2] = new JButton("East");
    buttonArray[1][2].setActionCommand("East");
    buttonArray[1][2].addActionListener(this);
    eastButton = buttonArray[1][2];
    
    buttonArray[2][0] = new JButton(" ");

    buttonArray[2][1] = new JButton("South");
    buttonArray[2][1].setActionCommand("South");
    buttonArray[2][1].addActionListener(this);
    southButton = buttonArray[2][1];
    
    buttonArray[2][2] = new JButton(" ");
    
    
    for (int r=0;r<3;r++)
    {
        for (int c=0;c<3;c++)
        {
            turnPanel.add(buttonArray[r][c]);
        }
    }
    add("East", turnPanel);
  
    setVisible(true);
    setAlwaysOnTop(true);
  }
  

  @Override
  public void actionPerformed(ActionEvent event) {
    InvokerBuilder inv = new InvokerBuilder();
    
    Command command = null;
    if (env.focusedCell != null && env.focusedCell.getLifeForm() != null) {
      if (event.getSource() == moveButton)
      {
        command = new MoveCommand(env.focusedCell.getLifeForm(), env);
      }  else if (event.getSource() == reloadButton) {           
        command = new ReloadCommand(env.focusedCell.getLifeForm());
      }  else if (event.getSource() == attackButton) {
        command = new AttackCommand(env.focusedCell.getLifeForm(), env);
      }  else if (event.getSource() == dropButton) {
        command = new DropCommand(env.focusedCell.getLifeForm(), env);
      }   else if (event.getSource() == acquireButton) {
        command = new AcquireCommand(env.focusedCell.getLifeForm(), env);
      }   else if (event.getSource() == northButton) {
        command = new TurnNorthCommand(env.focusedCell.getLifeForm());
      }   else if (event.getSource() == eastButton) {
        command = new TurnEastCommand(env.focusedCell.getLifeForm());
      }   else if (event.getSource() == southButton) {
        command = new TurnSouthCommand(env.focusedCell.getLifeForm());
      }   else if (event.getSource() == westButton) {
        command = new TurnWestCommand(env.focusedCell.getLifeForm());
      } 
      inv.setCommand(command);
      
      
      try {
        inv.command.execute();
      } catch (WeaponException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      int row = env.focusedCell.getLifeForm().getRow();
      int col = env.focusedCell.getLifeForm().getCol();
      GameBoard.getInstance().updateCell(row, col);  

      
    } else {
   
      errorLabel = new JLabel("No LifeForm in this Cell");
      if (env.focusedCell.getLifeForm() == null) {
        System.out.println("Error: No lifeform");
      } else { System.out.println("other error");
      }
    }
  }
}