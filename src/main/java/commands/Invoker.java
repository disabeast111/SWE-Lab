package commands;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import environment.Environment;

public class Invoker extends JFrame implements ActionListener {
  JButton moveButton, northButton, eastButton, southButton, 
    westButton, acquireButton, dropButton, reloadButton, fireButton;
  Environment e;
  
  
  
  public Invoker() {
    setLayout(new BorderLayout());
    

    JPanel weaponPanel = new JPanel(new GridLayout(2, 2));
    
    reloadButton = new JButton("Reload");
    reloadButton.addActionListener(this);
    weaponPanel.add(reloadButton);
    
    fireButton = new JButton("Fire");
    fireButton.addActionListener(this);
    weaponPanel.add(fireButton);
    
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
    
    if (e.focusedCell.getLifeForm() != null) {
      if (event.getSource() == moveButton)
      {
          moveButton.setText("Pushed");//delete this
          Command move = new MoveCommand(e.focusedCell.getLifeForm(), e);
          inv.setCommand(move);
          
          
      }  else if (event.getSource() == reloadButton) {           
      
      }
    } else {
      /*
       * Still needed: each button should set command and execute, show
       * an error if there is no lifeform in the cell.
       */
    }
  }
}