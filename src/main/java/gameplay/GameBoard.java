package gameplay;

import commands.*;
import environment.*;
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
  ImageIcon cell, alien;
  ImageIcon[] humans = new ImageIcon[3];
  private int cellDim = 70;

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
    ImageIcon icon = createImage();
    JPanel centerPanel = new JPanel(new GridLayout(10, 10));
    grid = new JRadioButton[10][10];
    for (int row = 0; row < 10; row++) {
      for (int col = 0; col < 10; col++) {
        updateCell(row, col);
        grid[row][col] = new JRadioButton(icon);
        grid[row][col].setSize(70, 70);
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
    cell = new ImageIcon(
        new ImageIcon("bin/cell.png").getImage().getScaledInstance(cellDim, cellDim, Image.SCALE_SMOOTH));
    for (int i = 0; i < 3; i++) {
      String fileName = "bin/human" + i + ".png";
      humans[i] = new ImageIcon(
          new ImageIcon(fileName).getImage().getScaledInstance(cellDim, cellDim, Image.SCALE_SMOOTH));
    }
  }

  public void updateCell(int row, int col) {
    // Construct Weapons in Cell
    if (environment.getWeapons(row, col)[0] instanceof Pistol) {
      // TODO pistol added
    } else if (environment.getWeapons(row, col)[0] instanceof ChainGun) {
      // TODO chain gun added
    } else if (environment.getWeapons(row, col)[0] instanceof PlasmaCannon) {
      // TODO plasma cannon added
    }

    // Construct LifeForm with Weapon and Direction
    LifeForm lifeForm = environment.getLifeForm(row, col);
    if (lifeForm instanceof Human) {
      // TODO human added

      // Human's weapon
      if (lifeForm.hasWeapon()) {
        Weapon weapon = lifeForm.getCurrentWeapon();
        if (weapon instanceof Pistol) {
          // TODO Human with pistol added
        } else {
          // TODO Human with rifle added
        }
      } else {
        // TODO Human with NO weapon added
      }

    } else if (lifeForm instanceof Alien) {
      // TODO alien added

      // Alien's weapon
      if (lifeForm.hasWeapon()) {
        Weapon weapon = lifeForm.getCurrentWeapon();
        if (weapon instanceof Pistol) {
          // TODO Alien with pistol added
        } else {
          // TODO Alien with rifle added
        }
      } else {
        // TODO Alien with NO weapon added
      }
    }

    // TODO LifeForm Rotation

  }

  @Override
  public void actionPerformed(ActionEvent event) {
    // TODO Auto-generated method stub
    for (int row = 0; row < 10; row++) {
      for (int col = 0; col < 10; col++) {
        if (event.getSource() == grid[row][col]) {
          textLabel.setText("(" + col + "," + row + ")");
        }
      }
    }
  }

  public static void main(String[] args) {
    new GameBoard();
  }

  public ImageIcon createImage() {
    BufferedImage img = new BufferedImage(cellDim, cellDim, BufferedImage.TYPE_3BYTE_BGR);
    Graphics2D g = (Graphics2D) img.getGraphics();
    g.drawImage(cell.getImage(), 0, 0, cellDim, cellDim, 0, 0, cellDim, cellDim, null);
    g.drawImage(humans[0].getImage(), 0, 0, cellDim, cellDim, 0, 0, cellDim, cellDim, null);
    g.rotate(Math.PI/2);
    ImageIcon icon = new ImageIcon(img);
    return icon;
  }

  /**
   * Converts a given Image into a BufferedImage
   *
   * @param img The Image to be converted
   * @return The converted BufferedImage
   */
  private BufferedImage toBufferedImage(Image img) {
    if (img instanceof BufferedImage) {
      return (BufferedImage) img;
    }

    // Create a buffered image with transparency
    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

    // Draw the image on to the buffered image
    Graphics2D bGr = bimage.createGraphics();
    bGr.drawImage(img, 0, 0, null);
    bGr.dispose();

    // Return the buffered image
    return bimage;
  }
}
