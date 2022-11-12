package commands;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Invoker extends JFrame implements ActionListener {
  JButton moveButton, northButton, eastButton, southButton, 
    westButton, acquireButton, dropButton, reloadButton, fireButton;
  JLabel textLabel;
  
  
  public Invoker() {
    setLayout(new BorderLayout());
    Dimension button = new Dimension(10,10);
         
      
   
    
    dropButton = new JButton("Drop Weapon");
    dropButton.addActionListener(this);
    
    acquireButton = new JButton("Acquire Weapon");
    acquireButton.addActionListener(this);
    
    reloadButton = new JButton("Drop Weapon");
    reloadButton.addActionListener(this);
    
    JPanel weaponPanel = new JPanel(new GridLayout());
    JPanel weaponPanel2 = new JPanel(new GridLayout());
    JButton[] weaponArray = new JButton[4];
    weaponArray[0] = acquireButton;
    weaponArray[1] = dropButton;
    weaponArray[2] = reloadButton;
    weaponArray[3] = fireButton;
    
    for(int i = 0; i < 2; i++) {
      weaponPanel.add(weaponArray[i]);
    }
    add("North", weaponPanel);
    
//    for(int i = 2; i < 4; i++) {
//      weaponPanel2.add(weaponArray[i]);
//    }
//    add("South", weaponPanel2);
    
    
    
    
    JPanel turnPanel = new JPanel(new GridLayout(3, 3));
    JButton[][] buttonArray = new JButton[3][3];
    

  
    buttonArray[0][0] = new JButton(" ");
    
    buttonArray[0][1] = new JButton("North");
    buttonArray[0][1].setActionCommand("North");
    buttonArray[0][1].addActionListener(this);
    
    buttonArray[0][2] = new JButton(" ");
    
    buttonArray[1][0] = new JButton("West");
    buttonArray[1][0].setActionCommand("West");
    buttonArray[1][0].addActionListener(this);
    
    buttonArray[1][1] = new JButton("Move");
    buttonArray[1][1].addActionListener(this);
    
    buttonArray[1][2] = new JButton("East");
    buttonArray[1][2].setActionCommand("East");
    buttonArray[1][2].addActionListener(this);
    
    buttonArray[2][0] = new JButton(" ");

    
    buttonArray[2][1] = new JButton("South");
    buttonArray[2][1].setActionCommand("South");
    buttonArray[2][1].addActionListener(this);
    
    buttonArray[2][2] = new JButton(" ");
    
    
    for (int r=0;r<3;r++)
    {
        for (int c=0;c<3;c++)
        {
            turnPanel.add(buttonArray[r][c]);
        }
    }
    
    
    
    
        

    add("Center", turnPanel);
  
    setVisible(true);
  }
  
  public static void main (String[] args) {
    Invoker gui = new Invoker();
    int x = 400;
    int y = 400;
//    Dimension frame = new Dimension(x, y);
    gui.setBounds(1000, 200, x, y);
    
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    // TODO Auto-generated method stub
    if (event.getSource() == moveButton)
    {
        moveButton.setText("Pushed");
        //SampleGUI.this.pack(); 
    }  else {           
    }
  }
}