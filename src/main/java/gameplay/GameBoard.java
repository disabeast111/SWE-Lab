package gameplay;

import commands.*;
import environment.*;
import exceptions.EnvironmentException;
import lifeform.*;
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
  JPanel centerPanel, legendPanel, statsPanel;
  JLabel textLabel;
  JRadioButton[][] grid;

  BufferedImage cellImage, alienImage;
  BufferedImage[] humanImages = new BufferedImage[3];
  ImageIcon cell, focus;
  ImageIcon[][] humans = new ImageIcon[3][4], weapons = new ImageIcon[3][2];
  ImageIcon[] aliens = new ImageIcon[4];
  private int cellDim = 70, prevRow = 0, prevCol = 0;

  private GameBoard() {
    setLayout(new BorderLayout());

    setupImages();

    textLabel = new JLabel("(-,-)");
    textLabel.setHorizontalAlignment(SwingConstants.CENTER);
    add("North", textLabel);

    JPanel cellStats = new JPanel(new GridLayout(2, 3));
    for (int i = 0; i < 6; i++)
      cellStats.add(new JLabel("-", SwingConstants.CENTER));
    add("South", cellStats);

    legendPanel = new JPanel(new GridLayout(1, 0));
    legendPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    add("East", legendPanel);
    JPanel centerPanel = new JPanel(new GridLayout(10, 10));
    grid = new JRadioButton[10][10];
    for (int row = 0; row < 10; row++) {
      for (int col = 0; col < 10; col++) {
        grid[row][col] = new JRadioButton();
        grid[row][col].setSize(70, 70);
        updateCell(row,col);
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
    // Load Cell Image
    cell = new ImageIcon(
        new ImageIcon("bin/cell.png").getImage().getScaledInstance(cellDim, cellDim, Image.SCALE_SMOOTH));

    // Load Focused Cell Graphic
    focus = new ImageIcon(
        new ImageIcon("bin/focus.png").getImage().getScaledInstance(cellDim, cellDim, Image.SCALE_SMOOTH));

    // Load Human Images
    for (int weapon = 0; weapon < 3; weapon++) {
      for (int direction = 0; direction < 4; direction++) {
        String fileName = "bin/human" + weapon + direction + ".png";
        humans[weapon][direction] = new ImageIcon(
            new ImageIcon(fileName).getImage().getScaledInstance(cellDim, cellDim, Image.SCALE_SMOOTH));
      }
    }

    // Load Alien Images
    for (int direction = 0; direction < 4; direction++) {
      String filename = "bin/alien" + 0 + direction + ".png";
      aliens[direction] = new ImageIcon(
          new ImageIcon(filename).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
    }

    // Load Weapon Images
    for (int weapon = 0; weapon < 3; weapon++) {
      for (int direction = 0; direction < 2; direction++) {
        String fileName = "bin/weapon" + weapon + direction + ".png";
        weapons[weapon][direction] = new ImageIcon(
            new ImageIcon(fileName).getImage().getScaledInstance(cellDim, cellDim, Image.SCALE_SMOOTH));
      }
    }
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
        if (currentWeapon instanceof Pistol) {
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

    // TODO Add Focused Cell Graphic
    try {
      if (environment.getCell(row, col) == environment.focusedCell) {
        cellGraphics.drawImage(focus.getImage(), 0, 0, cellDim, cellDim, 0, 0, cellDim, cellDim, null);
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

  public static void main(String[] args) {
    new GameBoard();
  }
}
