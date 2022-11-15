package gameplay;

import commands.*;
import environment.*;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import lifeform.*;
import recovery.RecoveryLinear;
import weapon.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameBoard extends JFrame implements ActionListener {
  private static GameBoard singletonInstance;
  Environment environment = Environment.getEnvironment(10, 10);
  JPanel centerPanel, statsPanel;
  JLabel textLabel, legendLabel;
  JLabel[][] statsLabels = new JLabel[8][2];
  JRadioButton[][] grid;

  BufferedImage cellImage, alienImage;
  BufferedImage[] humanImages = new BufferedImage[3];
  ImageIcon cell, focus, legend;
  ImageIcon[][] humans = new ImageIcon[3][4], weapons = new ImageIcon[3][2];
  ImageIcon[] aliens = new ImageIcon[4];
  private int cellDim = 70, prevRow = 0, prevCol = 0;

  private GameBoard() {
    setLayout(new BorderLayout());
    setupImages();
    
    try {
    environment.focusedCell = environment.getCell(0, 0);
    } catch (EnvironmentException e) {
      System.out.println("You screwed it up somehow...");
    }
    
    setupInfo();

    add("North", textLabel);
    add("South", statsPanel);
    add("East", legendLabel);

    JPanel centerPanel = new JPanel(new GridLayout(10, 10));
    grid = new JRadioButton[10][10];
    for (int row = 0; row < 10; row++) {
      for (int col = 0; col < 10; col++) {
        grid[row][col] = new JRadioButton();
        grid[row][col].setSize(70, 70);
        updateCell(row, col);
        grid[row][col].addActionListener(this);
        centerPanel.add(grid[row][col]);
      }
    }
    add("Center", centerPanel);
    setResizable(false);
    pack();
    setVisible(true);
  }

  public static GameBoard getInstance() {
    if (singletonInstance == null) {
      singletonInstance = new GameBoard();
    }
    return singletonInstance;
  }

  private void setupImages() {
    // Load Legend Image
    legend = new ImageIcon("images/legend.png");

    // Load Cell Image
    cell = new ImageIcon(
        new ImageIcon("images/cell.png").getImage().getScaledInstance(cellDim, cellDim, Image.SCALE_SMOOTH));

    // Load Focused Cell Graphic
    focus = new ImageIcon(
        new ImageIcon("images/focus.png").getImage().getScaledInstance(cellDim, cellDim, Image.SCALE_SMOOTH));

    // Load Human Images
    for (int weapon = 0; weapon < 3; weapon++) {
      for (int direction = 0; direction < 4; direction++) {
        String fileName = "images/human" + weapon + direction + ".png";
        humans[weapon][direction] = new ImageIcon(
            new ImageIcon(fileName).getImage().getScaledInstance(cellDim, cellDim, Image.SCALE_SMOOTH));
      }
    }

    // Load Alien Images
    for (int direction = 0; direction < 4; direction++) {
      String filename = "images/alien" + 0 + direction + ".png";
      aliens[direction] = new ImageIcon(
          new ImageIcon(filename).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
    }

    // Load Weapon Images
    for (int weapon = 0; weapon < 3; weapon++) {
      for (int direction = 0; direction < 2; direction++) {
        String fileName = "images/weapon" + weapon + direction + ".png";
        weapons[weapon][direction] = new ImageIcon(
            new ImageIcon(fileName).getImage().getScaledInstance(cellDim, cellDim, Image.SCALE_SMOOTH));
      }
    }
  }

  private void setupInfo() {
    // Title
    textLabel = new JLabel("PoS: The Game");
    textLabel.setFont(new Font(null, Font.BOLD, 20));
    textLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Focused Cell Statistics Panel
    statsPanel = new JPanel(new GridLayout(8, 2));
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 2; col++) {
        statsLabels[row][col] = new JLabel();
      }
    }
    updateStats(0,0);

    // Legend
    legendLabel = new JLabel(legend);
    legendLabel.setBorder(BorderFactory.createLineBorder(Color.black));
  }

  public void updateStats(int r, int c) {
    Cell currentCell = environment.focusedCell;
    // Coordinates
    statsLabels[0][0].setText("Cell: (" + r + "," + c + ")");

    String text0 = "No LifeForm", text1 = "N/A", text2 = "N/A", text3 = "N/A", text4 = "N/A", text5 = "N/A";

    // LifeForm Type & Name
    if (currentCell.getLifeForm() != null) {
      LifeForm lifeForm = currentCell.getLifeForm();

      if (lifeForm instanceof Human) {
        text0 = "Human: ";
        Human h = (Human) lifeForm;
        text2 = "Armor Points: " + h.getArmorPoints();

      } else {
        text0 = "Alien: ";
      }
      text0 += lifeForm.getName();
      text1 = "Life Points: " + lifeForm.getCurrentLifePoints();
      text3 = "Attack Strength: " + lifeForm.getAttackStrength();

      if (lifeForm.hasWeapon()) {
        Weapon w = lifeForm.getCurrentWeapon();
        text4 = "Weapon: " + w.toString().split(" ")[0];
        text5 = "Ammo: " + w.getCurrentAmmo() + "/" + w.getMaxAmmo();
      }
    }

    // LifeForm Name & Type
    statsLabels[1][0].setText(text0);

    // Life Points
    statsLabels[2][0].setText(text1);

    // Armor Points
    statsLabels[3][0].setText(text2);

    // Attack Strength
    statsLabels[4][0].setText(text3);
    statsLabels[5][0].setText("---");

    // Weapon Held by LifeForm
    statsLabels[6][0].setText(text4);

    // Ammo Held by LifeForm
    statsLabels[7][0].setText(text5);
    
    statsLabels[0][1].setText("Cell Conatins:");
    
    text0 = "Weapon 1: ";
    text1 = "";
    text2 = "";
    text3 = "Weapon 2: ";
    text4 = "";
    text5 = "";
    //Weapon 1
    if (currentCell.getWeapon1() != null) {
      Weapon w = currentCell.getWeapon1();
      String[] wString = w.toString().split(" ");
      text0 += wString[0];
      switch(wString.length) {
      case 2:
        text1 = wString[1];
        break;
      case 3:
        text1 = wString[1];
        text2 = wString[2];
      }
    } else {
      text0 += "none";
    }
    
  //Weapon 2
    if (currentCell.getWeapon2() != null) {
      Weapon w = currentCell.getWeapon2();
      String[] wString = w.toString().split(" ");
      text3 += wString[0];
      switch(wString.length) {
      case 2:
        text4 = wString[1];
        break;
      case 3:
        text4 = wString[1];
        text5 = wString[2];
      }
    } else {
      text0 += "none";
    }
    
    // Weapon 1 Type
    statsLabels[1][1].setText(text0);
    
    //Weapon 1 First Attachment
    statsLabels[2][1].setText(text1);
    
    //Weapon 1 Second Attachment
    statsLabels[3][1].setText(text2);
    statsLabels[4][1].setText("---");
    //Weapon 2 Type
    statsLabels[5][1].setText(text3);
    
    //Weapon 2 First Attachment
    statsLabels[6][1].setText(text4);
    
    //Weapon 2 Second Attachment
    statsLabels[7][1].setText(text5);
  }

  public void updateCell(int row, int col) {
    BufferedImage image = new BufferedImage(cellDim, cellDim, BufferedImage.TYPE_3BYTE_BGR);
    Graphics cellGraphics = image.getGraphics();

    cellGraphics.drawImage(cell.getImage(), 0, 0, cellDim, cellDim, 0, 0, cellDim, cellDim, null);

    int weapon;

    for (int position = 0; position < 2; position++) {
      weapon = -1;
      if (environment.getWeapons(row, col)[position] instanceof Pistol) {
        weapon = 0;
      } else if (environment.getWeapons(row, col)[position] instanceof ChainGun) {
        weapon = 1;
      } else if (environment.getWeapons(row, col)[position] instanceof PlasmaCannon) {
        weapon = 2;
      }
      if (weapon != -1) {
        cellGraphics.drawImage(weapons[weapon][position].getImage(), 0, 0, cellDim, cellDim, 0, 0, cellDim, cellDim,
            null);
      }
    }

    // Construct LifeForm with Weapon and Direction
    LifeForm lifeForm = environment.getLifeForm(row, col);
    if (lifeForm instanceof Human) {

      // Human's weapon
      if (lifeForm.hasWeapon()) {
        Weapon currentWeapon = lifeForm.getCurrentWeapon();
        if (currentWeapon.toString().split(" ")[0].equals("Pistol")) {
          weapon = 1;
        } else {
          weapon = 2;
        }
      } else {
        weapon = 0;
      }
      cellGraphics.drawImage(humans[weapon][lifeForm.getCurrentDirection()].getImage(), 0, 0, cellDim, cellDim, 0, 0,
          cellDim, cellDim, null);
    } else if (lifeForm instanceof Alien) {
      cellGraphics.drawImage(aliens[lifeForm.getCurrentDirection()].getImage(), 0, 0, cellDim, cellDim, 0, 0, cellDim,
          cellDim, null);
    }

    // Add Focused Cell Graphic
    try {
      if (environment.getCell(row, col) == environment.focusedCell) {
        cellGraphics.drawImage(focus.getImage(), 0, 0, cellDim, cellDim, 0, 0, cellDim, cellDim, null);
        prevRow = row;
        prevCol = col;
      }
    } catch (Exception e) {
      System.out.println("Error occurred; caught environment exception in updateCell().");
    }
    grid[row][col].setIcon(new ImageIcon(image));
    cellGraphics.dispose();
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    for (int row = 0; row < 10; row++) {
      for (int col = 0; col < 10; col++) {
        if (event.getSource() == grid[row][col]) {
          textLabel.setText("(" + col + "," + row + ")");
          try {
            environment.focusedCell = environment.getCell(row, col);
            updateCell(prevRow, prevCol);
          } catch (EnvironmentException e) {
            System.out.println("Error occurred; caught environment exception in actionPerformed().");
          }
          updateCell(row, col);
          prevRow = row;
          prevCol = col;
        }
      }
    }
  }
}
